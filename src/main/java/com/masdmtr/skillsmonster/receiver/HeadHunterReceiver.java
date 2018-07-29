package com.masdmtr.skillsmonster.receiver;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.masdmtr.skillsmonster.config.DateFormatter;
import com.masdmtr.skillsmonster.config.RabbitConfig;
import com.masdmtr.skillsmonster.dto.SearchResultDto;
import com.masdmtr.skillsmonster.persistence.model.Area;
import com.masdmtr.skillsmonster.persistence.model.Specialization;
import com.masdmtr.skillsmonster.persistence.model.Vacancy;
import com.masdmtr.skillsmonster.rabbitmq.Producer;
import com.masdmtr.skillsmonster.service.SkillsMonsterService;
import com.rabbitmq.client.Consumer;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.postgresql.util.PSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.*;

/**
 * Created by dmaslov on 7/17/17.
 * Reinvented by dmaslov on 7/22/18.
 */

@Component
@Qualifier("HeadHunter")
public class HeadHunterReceiver extends ReceiverImpl {
    private static final int MAX_RESPOSE_SIZE = 2000;

    private EntityManager entityManager;
    private SkillsMonsterService skillsMonsterService;
    private Producer producer;
    private RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);
    private RabbitConfig rabbitConfig;
    private DateFormatter dateFormatter;
    @Value("${spring.skillsmonster.host}")
    String apiHost;

    @Autowired
    public HeadHunterReceiver(EntityManager entityManager,
                              SkillsMonsterService skillsMonsterService,
                              Producer producer,
                              RestTemplate restTemplate,
                              RabbitConfig rabbitConfig,
                              DateFormatter dateFormatter
    ) {
        this.entityManager = entityManager;
        this.skillsMonsterService = skillsMonsterService;
        this.producer = producer;
        this.restTemplate = restTemplate;
        this.rabbitConfig = rabbitConfig;
        this.dateFormatter = dateFormatter;
    }

    @Override
    public void searchVacancy() {

        logger.info("Head Hunter Receiver Started");

        LocalDate dateFrom = LocalDate.now().minusDays(1);
        LocalDate dateTo = LocalDate.now().minusDays(1);
        LocalDate publDate = dateFrom;

        List<Integer> areaCountryList = skillsMonsterService.getAreaCountryList();
        ArrayList<Specialization> specializationList = skillsMonsterService.getSpecializationList();


        /*
            For testing purposes
        */
        //List<Integer> areaCountryList = new LinkedList<Integer>();
        //areaCountryList.add(113);

        ArrayList searchResultArray = new ArrayList<Map<String, Object>>();

        HashMap searchParams = new HashMap<String, Object>();
        ArrayList<Map<String, Object>> vacancyListToAdd = new ArrayList<>();
        while (publDate.compareTo(dateTo) <= 0) {

            logger.debug(String.valueOf(dateFrom.compareTo(dateTo)));

            for (Integer countryid : areaCountryList) {
                searchParams.put("AREA", countryid);

                Integer pageNum = 0;
                Integer found = 0;

                ArrayList resArray = new ArrayList<Map<String, Object>>();


                ArrayList<Map<String, Object>> vacancyList = getVacancyList(publDate, searchParams);

                found = ((Double) vacancyList.get(0).get("found")).intValue();
                Integer areaId = (Integer) searchParams.get("AREA");
                if (found > MAX_RESPOSE_SIZE) {
                    vacancyList.clear();
                    logger.info("Date: {}, Area: {}, Found: {}", publDate.toString(), areaId.toString(), found);
                    ArrayList<Area> areaList = skillsMonsterService.getAreaByCountryId(areaId);

                    for (Area ar : areaList) {
                        vacancyList.clear();
                        searchParams.clear();
                        searchParams.put("AREA", ar.getId());

                        vacancyList = getVacancyList(publDate, searchParams);

                        found = ((Double) vacancyList.get(0).get("found")).intValue();

                        if (found > MAX_RESPOSE_SIZE) {
                            vacancyList.clear();
                            logger.info("\t\t Date: {}, Area: {}, Found: {}", publDate.toString(), ar.getId(), found);
                            for (Specialization spec : specializationList) {

                                // String area = MessageFormat.format(areaTmp, ar.getId().toString());
                                // String specialization = MessageFormat.format(specializationTmp, spec.getSubId());

                                searchParams.put("SPEC", spec.getSubId());
                                vacancyList = getVacancyList(publDate, searchParams);
                                vacancyListToAdd.addAll(vacancyList);
                                found = ((Double) vacancyList.get(0).get("found")).intValue();
                                logger.info("\t\t\t Date: {}, Specialization: {}, Area: {}, Found: {}", publDate.toString(), spec.getSubId(), ar.getId().toString(), found);
                            }
                            addSearchResult(vacancyList);
                        }

                        vacancyListToAdd.addAll(vacancyList);
                        logger.info("\t\t Date: {}, Area: {}, Found: {}", publDate.toString(), ar.getId().toString(), found);
                        addSearchResult(vacancyList);
                    }

                } else {
                    logger.info("Date: {}, Area: {}, Found: {}", publDate.toString(), areaId.toString(), found);

                    vacancyListToAdd.addAll(vacancyList);
                }

                addSearchResult(vacancyListToAdd);
                vacancyListToAdd.clear();
            }

            publDate = publDate.plusDays(1);
        }


        logger.info("Head Hunter Receiver Finished");
    }

    public void addSearchResult(ArrayList<Map<String, Object>> searchResults) {

        //logger.info("Savind {} vacancies", searchResults.size());

        try {

            searchResults.forEach((res) -> {

                ((ArrayList) res.get("items")).forEach(
                        elem -> {
                            SearchResultDto searchResultDto = new SearchResultDto();
                            searchResultDto.setVacancyId((String) ((LinkedTreeMap) elem).get("id"));
                            searchResultDto.setName((String) ((LinkedTreeMap) elem).get("name"));

                            searchResultDto.setSalaryFrom(((LinkedTreeMap) elem).get("salary") != null ?
                                    (Double) ((LinkedTreeMap) elem).get("from") : null);

                            searchResultDto.setSalaryTo(((LinkedTreeMap) elem).get("salary") != null ?
                                    (Double) ((LinkedTreeMap) ((LinkedTreeMap) elem).get("salary")).get("to") : null);

                            searchResultDto.setSalaryCurrency(((LinkedTreeMap) elem).get("salary") != null ?
                                    (String) ((LinkedTreeMap) ((LinkedTreeMap) elem).get("salary")).get("currency") : null);

                            searchResultDto.setSalaryGross(((LinkedTreeMap) elem).get("salary") != null ?
                                    (Boolean) ((LinkedTreeMap) ((LinkedTreeMap) elem).get("salary")).get("gross") : null);

                            searchResultDto.setAreaId(((LinkedTreeMap) elem).get("area") != null ?
                                    (String) ((LinkedTreeMap) ((LinkedTreeMap) elem).get("area")).get("id") : null);

                            searchResultDto.setAreaName(((LinkedTreeMap) elem).get("area") != null ?
                                    (String) ((LinkedTreeMap) ((LinkedTreeMap) elem).get("area")).get("name") : null);

                            searchResultDto.setAreaUrl(((LinkedTreeMap) elem).get("area") != null ?
                                    (String) ((LinkedTreeMap) ((LinkedTreeMap) elem).get("area")).get("url") : null);

                            searchResultDto.setSnippetRequirement(((LinkedTreeMap) elem).get("snippet") != null ?
                                    (String) ((LinkedTreeMap) ((LinkedTreeMap) elem).get("snippet")).get("requirement") : null);

                            searchResultDto.setSnippetResponsibility(((LinkedTreeMap) elem).get("snippet") != null ?
                                    (String) ((LinkedTreeMap) ((LinkedTreeMap) elem).get("snippet")).get("responsibility") : null);

                            searchResultDto.setArchived((Boolean) ((LinkedTreeMap) elem).get("archived"));
                            searchResultDto.setPremium((Boolean) ((LinkedTreeMap) elem).get("premium"));
                            searchResultDto.setSource((String) ((LinkedTreeMap) elem).get("source"));
                            searchResultDto.setUrl((String) ((LinkedTreeMap) elem).get("url"));
                            searchResultDto.setAlternateUrl((String) ((LinkedTreeMap) elem).get("alternateUrl"));
                            searchResultDto.setApplyAlternateUrl((String) ((LinkedTreeMap) elem).get("applyAlternateUrl"));

                            //TODO Add "street": "lat": , "lng"
                            searchResultDto.setAddress(((LinkedTreeMap) elem).get("address") != null ?
                                    (String) ((LinkedTreeMap) ((LinkedTreeMap) elem).get("address")).get("id") : null);

                            searchResultDto.setDepartmentId(((LinkedTreeMap) elem).get("department") != null ?
                                    (String) ((LinkedTreeMap) ((LinkedTreeMap) elem).get("department")).get("id") : null);

                            searchResultDto.setDepartmentName(((LinkedTreeMap) elem).get("department") != null ?
                                    (String) ((LinkedTreeMap) ((LinkedTreeMap) elem).get("department")).get("name") : null);

                            //TODO
                            // processingQueueItem.setSortPointDistance((String) ((LinkedTreeMap) elem).get("sortPointDistance"));
                            searchResultDto.setCreatedAt(
                                    (String) ((LinkedTreeMap) elem).get("created_at"));
                            searchResultDto.setPublishedAt(
                                    (String) ((LinkedTreeMap) elem).get("published_at"));

                            searchResultDto.setEmpId(((LinkedTreeMap) elem).get("employer") != null ?
                                    (String) ((LinkedTreeMap) ((LinkedTreeMap) elem).get("employer")).get("id") : null);

                            searchResultDto.setEmpName(((LinkedTreeMap) elem).get("employer") != null ?
                                    (String) ((LinkedTreeMap) ((LinkedTreeMap) elem).get("employer")).get("name") : null);

                            searchResultDto.setEmpUrl(((LinkedTreeMap) elem).get("employer") != null ?
                                    (String) ((LinkedTreeMap) ((LinkedTreeMap) elem).get("employer")).get("url") : null);

                            searchResultDto.setEmpUrl(((LinkedTreeMap) elem).get("type") != null ?
                                    (String) ((LinkedTreeMap) ((LinkedTreeMap) elem).get("type")).get("id") : null);

                            producer.sendMessage(searchResultDto);

                        });
            });

        } catch (Exception e) {
            logger.error(ExceptionUtils.getFullStackTrace(e));
        }
    }

    public ArrayList<Map<String, Object>> getVacancyList(LocalDate publDate, HashMap<String, Object> searchParams) {
        String reqString;
        String jsonString;
        Integer pageNum = 0;
        Integer perPage = 100;
        Integer totalPages = 0;
        Integer found = 0;
        Map<String, Object> resposeMap;
        RestTemplate restTemplate = new RestTemplate();
        ArrayList<Map<String, Object>> retArray = new ArrayList<Map<String, Object>>();
        String areaTmp = "area={0}&";
        String specializationTmp = "specialization={0}&";
        Integer areaId = (Integer) searchParams.get("AREA");
        String specId = (String) searchParams.get("SPEC");

        while (pageNum == 0 || totalPages > pageNum) {

            reqString = String.format(apiHost.concat("vacancies?&date_from=%s&date_to=%s&per_page=100&page=%s&area=%s%s"), publDate.toString(), publDate.toString(), pageNum, areaId, specId != null ? "&specialization=".concat(specId) : "");

            jsonString = restTemplate.getForObject(reqString, String.class);

            resposeMap = new Gson().fromJson(jsonString, new TypeToken<HashMap<String, Object>>() {
            }.getType());

            retArray.add(resposeMap);

            totalPages = ((Double) resposeMap.get("pages")).intValue();
            found = ((Double) resposeMap.get("found")).intValue();

            pageNum++;

            if (found > MAX_RESPOSE_SIZE) {
                break;
            }
        }
        return retArray;
    }

//    @Override
//    public void loadVacancyDetailes() {
//
//        RestTemplate restTemplate = new RestTemplate();
//
//        skillsMonsterService.getProcessingQueue()
//                //.stream()
//                .parallelStream()
//                .forEach(processingQueueItem -> {
//                    String vacId = processingQueueItem.getVacancyId();
//
//                    try {
//                        logger.debug("Vacancy ID: {} Created: {}", vacId, processingQueueItem.getCreatedAt());
//                        String reqString = apiHost.concat("vacancies/").concat(vacId);
//                        String jsonString = restTemplate.getForObject(reqString, String.class);
//                        Map<String, Object> retMap = new Gson().fromJson(jsonString, new TypeToken<HashMap<String, Object>>() {
//                        }.getType());
//                        Vacancy vacancy = new Vacancy();
//                        vacancy.setVacancyId(vacId);
//                        vacancy.setRawData(retMap);
//
//                        vacancy.setLoadDateTime(new Timestamp(System.currentTimeMillis()));
//                        skillsMonsterService.addVacancy(vacancy);
//                        processingQueueItem.setProcessedAt(new Timestamp(System.currentTimeMillis()));
//                        processingQueueItem.setStatus("LOADED");
//                        skillsMonsterService.updateProcessingQueue(processingQueueItem);
//
//                    } catch (HttpClientErrorException ex) {
//                        logger.error("Error loading info from hh.ru ID: {} Created: {}", vacId, processingQueueItem.getCreatedAt());
//                        processingQueueItem.setProcessedAt(new Timestamp(System.currentTimeMillis()));
//                        processingQueueItem.setStatus("ERROR");
//                        skillsMonsterService.updateProcessingQueue(processingQueueItem);
//
//                        logger.error(ExceptionUtils.getMessage(ex));
//                    }
//                });
//    }

    @Override
    public void loadVacancyDetails(SearchResultDto searchResultDto) {


        //String vacId = searchResultDto.getVacancyId();
        String vacId = "26843888";

        try {
            logger.debug("Vacancy ID: {} Created: {}", vacId, searchResultDto.getPublishedAt());
            //String reqString = apiHost.concat("vacancies/").concat(vacId);
            String reqString = apiHost.concat("vacancies/").concat(vacId);
            String jsonString = restTemplate.getForObject(reqString, String.class);

            Map<String, Object> retMap = new Gson().fromJson(
                    jsonString,
                    new TypeToken<HashMap<String, Object>>() {
                    }.getType()
            );

            Vacancy vacancy = new Vacancy();
            vacancy.setVacancyId(vacId);
            vacancy.setRawData(retMap);
            vacancy.setAddress(searchResultDto.getAddress());
            vacancy.setAlternateUrl(searchResultDto.getAlternateUrl());
            vacancy.setApplyAlternateUrl(searchResultDto.getApplyAlternateUrl());
            vacancy.setArchived(searchResultDto.getArchived());


            vacancy.setName(searchResultDto.getName());
            vacancy.setAreaUrl(searchResultDto.getAreaUrl());
            vacancy.setAreaId(searchResultDto.getAreaId());
            vacancy.setAreaName(searchResultDto.getAreaName());
            vacancy.setSalaryFrom(searchResultDto.getSalaryFrom());
            vacancy.setSalaryTo(searchResultDto.getSalaryTo());
            vacancy.setSalaryGross(searchResultDto.getSalaryGross());
            vacancy.setSalaryCurrency(searchResultDto.getSalaryCurrency());
            vacancy.setSnippetRequirement(searchResultDto.getSnippetRequirement());
            vacancy.setSnippetResponsibility(searchResultDto.getSnippetResponsibility());
            vacancy.setArchived(searchResultDto.getArchived());
            vacancy.setPremium(searchResultDto.getPremium());
            vacancy.setSource(searchResultDto.getSource());
            //vacancy.setCreatedAt(OffsetDateTime.parse(searchResultDto.getCreatedAt(), dateFormatter.getFormatter()));
            vacancy.setCreatedAt(OffsetDateTime.parse((String) (retMap).get("created_at"), dateFormatter.getFormatter()));
            //vacancy.setPublishedAt(OffsetDateTime.parse(searchResultDto.getPublishedAt(), dateFormatter.getFormatter()));
            vacancy.setPublishedAt(OffsetDateTime.parse((String) (retMap).get("published_at"), dateFormatter.getFormatter()));
            vacancy.setUrl(searchResultDto.getUrl());
            vacancy.setAlternateUrl(searchResultDto.getAlternateUrl());
            vacancy.setApplyAlternateUrl(searchResultDto.getApplyAlternateUrl());
            vacancy.setAddress(searchResultDto.getAddress());
            vacancy.setDepartmentId(searchResultDto.getDepartmentId());
            vacancy.setDepartmentName(searchResultDto.getDepartmentName());
            vacancy.setSortPointDistance(searchResultDto.getSortPointDistance());
            vacancy.setEmpId(searchResultDto.getEmpId());
            vacancy.setEmpName(searchResultDto.getEmpName());
            vacancy.setEmpUrl(searchResultDto.getEmpUrl());
            vacancy.setStatus(searchResultDto.getStatus());
            vacancy.setTypeId(searchResultDto.getTypeId());
            vacancy.setTypeName(searchResultDto.getTypeName());

            vacancy.setLoadDateTime(new Date());
            skillsMonsterService.addVacancy(vacancy);

        } catch (HttpClientErrorException ex) {
            logger.error("Error loading info from hh.ru ID: {}", vacId);
            //    processingQueueItem.setProcessedAt(new Timestamp(System.currentTimeMillis()));
            searchResultDto.setStatus("ERROR");
            //    skillsMonsterService.updateProcessingQueue(processingQueueItem);

            logger.error(ExceptionUtils.getMessage(ex));
        } catch (JpaSystemException jpaSystemException) {
            logger.error("Duplicate key value violates unique constraint", vacId);
        }
    }

//    @Override
//    @Transactional
//    public void updateProcessingQueue() {
//        //   System.out.println("update_queue");
//        StoredProcedureQuery sp = entityManager.createStoredProcedureQuery("public.add_vacancy_to_queue");
//        List resultList = sp.getResultList();
//
//
//        logger.info("added {} vacancies to the processing queue", resultList.get(0).toString());
//    }
}
