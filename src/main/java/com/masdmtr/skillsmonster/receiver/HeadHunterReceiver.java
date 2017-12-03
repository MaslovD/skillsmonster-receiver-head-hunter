package com.masdmtr.skillsmonster.receiver;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.masdmtr.skillsmonster.entity.*;
import com.masdmtr.skillsmonster.service.SkillsMonsterService;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.time.*;
import java.util.*;

import java.util.concurrent.TimeUnit;

/**
 * Created by dmaslov on 7/17/17.
 */

@Component
@Qualifier("HeadHunter")
public class HeadHunterReceiver extends ReceiverImpl {
    public static final int MAX_RESPOSE_SIZE = 2000;

    @Autowired
    private SkillsMonsterService skillsMonsterService;
    private int id;

    @Autowired
    Logger logger;

    @Autowired
    EntityManager entityManager;

    //@Value("${skillsmonster.reciever.hh.host}")
    String apiHost;

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

                Integer totalPages = ((Double) res.get("pages")).intValue();
                String rawRequest = (String) res.get("alternate_url");
                Double found = (Double) res.get("found");
                Integer pageNum = ((Double) res.get("page")).intValue();

                SearchResult searchResult = new SearchResult();
                searchResult.setPage(pageNum);
                searchResult.setTotalPages(totalPages);
                searchResult.setRawResponse(res);
                searchResult.setFound(found);
                searchResult.setStatus("NEW");
                searchResult.setRawRequest(rawRequest);

                skillsMonsterService.addSearchResult(searchResult);

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

    @Override
    public void loadVacancyDetailes() {

        RestTemplate restTemplate = new RestTemplate();

        skillsMonsterService.getProcessingQueue()
                //.stream()
                .parallelStream()
                .forEach(processingQueueItem -> {
                    String vacId = processingQueueItem.getVacancyId();

                    try {
                        logger.debug("Vacancy ID: {} Created: {}", vacId, processingQueueItem.getCreatedAt());
                        String reqString = apiHost.concat("vacancies/").concat(vacId);
                        String jsonString = restTemplate.getForObject(reqString, String.class);
                        Map<String, Object> retMap = new Gson().fromJson(jsonString, new TypeToken<HashMap<String, Object>>() {
                        }.getType());
                        Vacancy vacancy = new Vacancy();
                        vacancy.setVacancyId(vacId);
                        vacancy.setRawData(retMap);

                        vacancy.setLoadDateTime(new Timestamp(System.currentTimeMillis()));
                        skillsMonsterService.addVacancy(vacancy);
                        processingQueueItem.setProcessedAt(new Timestamp(System.currentTimeMillis()));
                        processingQueueItem.setStatus("LOADED");
                        skillsMonsterService.updateProcessingQueue(processingQueueItem);

                    } catch (HttpClientErrorException ex) {
                        logger.error("Error loading info from hh.ru ID: {} Created: {}", vacId, processingQueueItem.getCreatedAt());
                        processingQueueItem.setProcessedAt(new Timestamp(System.currentTimeMillis()));
                        processingQueueItem.setStatus("ERROR");
                        skillsMonsterService.updateProcessingQueue(processingQueueItem);

                        logger.error(ExceptionUtils.getMessage(ex));
                    }
                });
    }

    @Override
    @Transactional
    public void updateProcessingQueue() {
     //   System.out.println("update_queue");
        StoredProcedureQuery sp = entityManager.createStoredProcedureQuery("public.add_vacancy_to_queue");
        List resultList = sp.getResultList();


        logger.info("added {} vacancies to the processing queue", resultList.get(0).toString());
    }
}
