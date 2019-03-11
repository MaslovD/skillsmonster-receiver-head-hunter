package com.masdmtr.skillsmonster.receiver.hh;

import com.google.gson.Gson;
import com.masdmtr.skillsmonster.dto.SearchRequestDto;
import com.masdmtr.skillsmonster.persistence.model.Area;
import com.masdmtr.skillsmonster.persistence.model.Skill;
import com.masdmtr.skillsmonster.persistence.model.Specialization;
import com.masdmtr.skillsmonster.persistence.model.Vacancy;
import com.masdmtr.skillsmonster.rabbitmq.Producer;
import com.masdmtr.skillsmonster.receiver.Receiver;
import com.masdmtr.skillsmonster.receiver.hh.vacancy.VacancyDto;
import com.masdmtr.skillsmonster.receiver.hh.vacancysearchresult.VacancySearchResultDto;
import com.masdmtr.skillsmonster.service.SkillsMonsterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.*;

/**
 * Created by dmaslov on 7/17/17.
 * Reinvented by dmaslov on 7/22/18.
 * ReReinvented by dmaslov on 01/05/19.
 */

@Component
@Qualifier("HeadHunter")
public class HeadHunterReceiverImpl implements Receiver {
    private static final int MAX_RESPOSE_SIZE = 2000;


    private SkillsMonsterService skillsMonsterService;
    private Producer producer;
    private RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(HeadHunterReceiverImpl.class);

    private Gson gson;


    @Value("${spring.skillsmonster.host}")
    String apiHost;

    @Autowired
    public HeadHunterReceiverImpl(
            SkillsMonsterService skillsMonsterService,
            Producer producer,
            RestTemplate restTemplate,
            Gson gson
    ) {
        this.skillsMonsterService = skillsMonsterService;
        this.producer = producer;
        this.restTemplate = restTemplate;
        this.gson = gson;
    }

    @Override
    public void searchVacancy() {

        logger.info("Head Hunter Receiver started");

        LocalDate dateFrom = LocalDate.now().minusDays(38);
        LocalDate dateTo = LocalDate.now().minusDays(1);
        LocalDate publDate = dateFrom;
        List<VacancySearchResultDto> vacancyList;
        Integer found;

        List<Integer> areaCountryList = skillsMonsterService.getAreaCountryList();
        ArrayList<Specialization> specializationList = skillsMonsterService.getSpecializationList();

        HashMap searchParams = new HashMap<String, Object>();
        List<VacancySearchResultDto> vacancyListToAdd = new ArrayList<>();

        while (publDate.compareTo(dateTo) <= 0) {

            logger.debug(String.valueOf(dateFrom.compareTo(dateTo)));

            for (Integer countryid : areaCountryList) {

                searchParams.put("AREA", countryid);

                vacancyList = searchVacancy(publDate, searchParams);

                found = vacancyList.get(0).getFound();

                Integer areaId = (Integer) searchParams.get("AREA");

                if (found > MAX_RESPOSE_SIZE) {
                    vacancyList.clear();

                    logger.info("Date: {}, Area: {}, Found: {}", publDate.toString(), areaId.toString(), found);

                    ArrayList<Area> areaList = skillsMonsterService.getAreaByCountryId(areaId);

                    for (Area area : areaList) {
                        searchParams.clear();
                        searchParams.put("AREA", area.getId());

                        vacancyList = searchVacancy(publDate, searchParams);

                        found = vacancyList.get(0).getFound();

                        if (found > MAX_RESPOSE_SIZE) {
                            vacancyList.clear();
                            logger.info("\t\t Date: {}, Area: {}, Found: {}", publDate.toString(), area.getId(), found);

                            for (Specialization specialization : specializationList) {

                                searchParams.put("SPEC", specialization.getSubId());

                                vacancyList = searchVacancy(publDate, searchParams);
                                found = vacancyList.get(0).getFound();
                                vacancyListToAdd.addAll(vacancyList);
                                vacancyList.clear();

                                logger.info("\t\t\t Date: {}, Specialization: {}, Area: {}, Found: {}", publDate.toString(), specialization.getSubId(), area.getId().toString(), found);
                            }
                        } else {

                            vacancyListToAdd.addAll(vacancyList);
                            vacancyList.clear();
                        }

                        logger.info("\t\t Date: {}, Area: {}, Found: {}", publDate.toString(), area.getId().toString(), found);

                    }


                } else {
                    logger.info("Date: {}, Area: {}, Found: {}", publDate.toString(), areaId.toString(), found);

                    vacancyListToAdd.addAll(vacancyList);
                    vacancyList.clear();
                }


            }

            sendToQueue(vacancyListToAdd);

            publDate = publDate.plusDays(1);
        }

        logger.info("Head Hunter Receiver Finished");
    }

    private void sendToQueue(List<VacancySearchResultDto> searchResults) {
        Set<SearchRequestDto> searchRequestDtoSet = new HashSet();

        searchResults.forEach((searchResult) ->

                searchResult.getItems().forEach(
                        item -> {
                            SearchRequestDto searchRequestDto = new SearchRequestDto();

                            searchRequestDto.setVacancyId(item.getId());
                            searchRequestDto.setName(item.getName());

                            searchRequestDtoSet.add(searchRequestDto);

                        }
                ));

        searchRequestDtoSet.forEach(searchRequestDto -> producer.sendMessage(searchRequestDto));

    }

    private List<VacancySearchResultDto> searchVacancy(LocalDate publDate, HashMap<String, Object> searchParams) {
        String reqString;
        String jsonString;
        Integer pageNum = 0;
        Integer totalPages = 0;
        Integer found;
        RestTemplate restTemplate = new RestTemplate();
        List<VacancySearchResultDto> vacancySearchResultDtoList = new ArrayList<>();
        Integer areaId = (Integer) searchParams.get("AREA");
        String specId = (String) searchParams.get("SPEC");


        while (pageNum == 0 || totalPages > pageNum) {

            reqString = String.format(
                    apiHost.concat(
                            "vacancies?&date_from=%s&date_to=%s&per_page=100&page=%s&area=%s%s"),
                    publDate.toString(),
                    publDate.toString(),
                    pageNum,
                    areaId,
                    specId != null ? "&specialization=".concat(specId) : ""
            );

            jsonString = restTemplate.getForObject(reqString, String.class);

            VacancySearchResultDto vacancySearchResultDto = gson.fromJson(jsonString, VacancySearchResultDto.class);

            vacancySearchResultDtoList.add(vacancySearchResultDto);

            totalPages = vacancySearchResultDto.getPages();

            found = vacancySearchResultDto.getFound();

            pageNum++;

            if (found > MAX_RESPOSE_SIZE) {
                break;
            }
        }
        return vacancySearchResultDtoList;
    }

    @Override
    public void loadVacancyDetails(SearchRequestDto searchRequestDto) {

        String vacancyId = searchRequestDto.getVacancyId();

        logger.debug("Vacancy ID: {} Created: {}", vacancyId);
        try {
            String reqString = apiHost.concat("vacancies/").concat(vacancyId);

            skillsMonsterService.addVacancy(
                    vacancyJsonToVacancy(
                            restTemplate.getForObject(
                                    reqString,
                                    String.class
                            )
                    )
            );

            logger.debug("Vacancy with ID {} saved", vacancyId);


        } catch (HttpClientErrorException exception) {
            logger.error(
                    "Exception occurred during processing vacancy. Id: {}, Reason: {}",
                    vacancyId, exception.toString()
            );
            //throw exception;
        } catch (JpaSystemException exception) {
            logger.error(
                    "Exception occurred during processing vacancy. Id: {}, Reason: {}",
                    vacancyId, exception.toString()
            );
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {

//            logger.error(
//                    "Exception occurred during processing vacancy. Id: {}, Reason: {}",
//                    vacancyId, dataIntegrityViolationException.getMessage()
//            );

        }


    }

    @Override
    public Vacancy vacancyJsonToVacancy(String vacancyJson) {
        VacancyDto vacancyDto = gson.fromJson(
                vacancyJson,
                VacancyDto.class
        );

        Vacancy vacancy = new Vacancy();
        vacancy.setVacancyId(vacancyDto.getId());
        vacancy.setRawData(vacancyJson);
        vacancy.setName(vacancyDto.getName());
        vacancy.setAddress(vacancyDto.getAddress() != null ? vacancyDto.getAddress().getRaw() : null);
        vacancy.setAlternateUrl(vacancyDto.getAlternateUrl());
        vacancy.setApplyAlternateUrl(vacancyDto.getApplyAlternateUrl());
        vacancy.setArchived(vacancyDto.getArchived());
        vacancy.setPremium(vacancyDto.getPremium());
        vacancy.setAlternateUrl(vacancyDto.getAlternateUrl());
        vacancy.setCreatedAt(vacancyDto.getCreatedAt());
        vacancy.setPublishedAt(vacancyDto.getPublishedAt());

        //Area
        if (vacancyDto.getArea() != null) {
            vacancy.setAreaUrl(vacancyDto.getArea().getUrl());
            vacancy.setAreaId(vacancyDto.getArea().getId());
            vacancy.setAreaName(vacancyDto.getArea().getName());

        }


        //Employer
        if (vacancyDto.getEmployer() != null) {
            vacancy.setEmpId(vacancyDto.getEmployer().getId());
            vacancy.setEmpName(vacancyDto.getEmployer().getName());
            vacancy.setEmpUrl(vacancyDto.getEmployer().getUrl());
        }


        //Salary
        if (vacancyDto.getSalary() != null) {
            Integer salaryFrom = vacancyDto.getSalary().getFrom();
            Integer salaryTo = vacancyDto.getSalary().getTo();

            vacancy.setSalaryFrom(salaryFrom != null ? salaryFrom.doubleValue() : null);
            vacancy.setSalaryTo(salaryTo != null ? salaryTo.doubleValue() : null);
            vacancy.setSalaryGross(vacancyDto.getSalary().getGross());
            vacancy.setSalaryCurrency(vacancyDto.getSalary().getCurrency());
        }

        //Department
        if (vacancyDto.getDepartment() != null) {
            vacancy.setDepartmentId(vacancyDto.getDepartment().getId());
            vacancy.setDepartmentName(vacancyDto.getDepartment().getName());
        }

        // Type
        if (vacancyDto.getType() != null) {
            vacancy.setTypeId(vacancyDto.getType().getId());
            vacancy.setTypeName(vacancyDto.getType().getName());
        }


        vacancyDto.getKeySkills().forEach(keySkill -> {

            String keySkillName = (keySkill.getName());
            vacancy.getSkills().add(new Skill(keySkillName, vacancy));
        });

        vacancy.setLoadDateTime(new Date());

        return vacancy;
    }

}

