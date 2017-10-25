package com.masdmtr.skillsmonster.receiver;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.masdmtr.skillsmonster.Utils;
import com.masdmtr.skillsmonster.entity.*;
import com.masdmtr.skillsmonster.service.SkillsMonsterService;
import com.sun.tools.corba.se.idl.InterfaceGen;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.omg.CORBA.INTERNAL;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.time.*;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
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

    @Override
    public void searchVacancy() {

        logger.info("Head Hunter Receiver Started");

        LocalDateTime timePoint = LocalDateTime.now();
        String industry = "";//"industry=7.540&";
        String areaTmp = "area={0}&";
        String specializationTmp = "specialization={0}&";

        ArrayList<Specialization> specializationList = skillsMonsterService.getSpecializationList();

        LocalDate dateFrom = LocalDate.now().minusDays(1);
        LocalDate dateTo = LocalDate.now().minusDays(1);
        LocalDate publDate = dateFrom;

        List<Integer> areaCountryList = skillsMonsterService.getAreaCountryList();
        ArrayList searchResultArray = new ArrayList<Map<String, Object>>();

        HashMap searchParams = new HashMap<String, Object>();

        while (publDate.compareTo(dateTo) <= 0) {

            logger.debug(String.valueOf(dateFrom.compareTo(dateTo)));

            for (Integer countryid : areaCountryList) {
                searchParams.put("AREA", countryid);

                searchResultArray = searchForVacancy(publDate, searchParams);

                if (searchResultArray == null) {
                    System.out.println("search in more detailes");
                    ArrayList<Area> areaList = skillsMonsterService.getAreaByCountryId(countryid);
                    searchResultArray = new ArrayList<Map<String, Object>>();

                    for (Area ar : areaList) {

                        for (Specialization spec : specializationList) {

                            // String area = MessageFormat.format(areaTmp, ar.getId().toString());
                            String specialization = MessageFormat.format(specializationTmp, spec.getSubId());

                            searchParams.put("AREA", ar.getId());
                            searchParams.put("SPEC", spec.getSubId());


                            Utils.addAllIfNotNull(searchResultArray, searchForVacancy(publDate, searchParams));

                            logger.info("Date: {}, Specialization: {}, Area: {}", publDate.toString(), spec.getSubId(), ar.getId().toString());

                        }

                    }

                } else {
                    System.out.println("no need more detailes");

//                    if (res.size() > 0) {
//                        System.out.println("fd");
//                    }
                }
                System.out.println("processing complete");
                System.out.println(searchResultArray.size());
                searchResultArray.clear();

//
//                if (resposeMap.get("found") > MAX_RESPOSE_SIZE) {
//
//                    ArrayList<Area> areaList = skillsMonsterService.getAreaByCountryId(countryid);
//
//                    for (Area ar : areaList) {
//
//                        for (Specialization spec : specializationList) {
//
//                            Integer pageNum = 0;
//
//                            String specialization = MessageFormat.format(specializationTmp, spec.getSubId());
//
//                            String area = MessageFormat.format(areaTmp, ar.getId().toString());
//
//                            logger.info("Date: {}, Specialization: {}, Area: {}", publDate.toString(), spec.getSubId(), ar.getId().toString());
//
//                            while (totalPages > pageNum) {
//                                try {
//
//                                    reqString = "https://api.hh.ru/vacancies?" + "date_from=" + publDate.toString() + "&date_to=" + publDate.toString() + "&per_page=" + perPage.toString() + "&page=" + pageNum.toString() + specialization + area + industry;
//
//                                    logger.debug(reqString);
//                                    jsonString = restTemplate.getForObject(reqString, String.class);
//
//                                    searchRequest.setRawRequest(reqString);
//                                    searchRequest.setDateTime(new Timestamp(System.currentTimeMillis()));
//                                    //skillsMonsterService.addSearchRequest(searchRequest);
//
//                                    resposeMap = new Gson().fromJson(jsonString, new TypeToken<HashMap<String, Object>>() {
//                                    }.getType());
//
//                                    totalPages = ((Double) resposeMap.get("pages")).intValue();
//
//                                    totalFound = Math.round((Double) resposeMap.get("found"));
//
//                                    logger.debug("Total found: {}  Page: {}", totalFound, pageNum);
//
//
//                                    searchRequest.setPages(totalPages);
//                                    searchRequest.setFound(totalFound);
//                                    searchRequest.setPerPage(perPage);
//                                    searchRequest.setAreaByAreaId(ar);
//                                    //Timestamp.valueOf(publDate.atStartOfDay());
//                                    //Timestamp timestamp = Timestamp.valueOf(publDate.atStartOfDay());
//                                    searchRequest.setPeriodFrom(Timestamp.valueOf(publDate.atStartOfDay()));
//                                    searchRequest.setPeriodTo(Timestamp.valueOf(publDate.atStartOfDay()));
//                                    searchRequest.setSpecializationId(spec);
//
//                                    SearchResult searchResult = new SearchResult();
//                                    searchResult.setSearchRequest(searchRequest);
//                                    searchResult.setPage(pageNum);
//                                    searchResult.setRawResponse(resposeMap);
//                                    searchResult.setFound(totalFound);
//                                    searchResult.setStatus("NEW");
//
//                                    if (totalFound > 0) {
//                                        logger.info("Total found: {}  Page: {}", totalFound, pageNum);
//                                        skillsMonsterService.addSearchResult(searchResult);
//                                    }
//
//                                    pageNum++;
//
//                                } catch (Exception e) {
//                                    logger.error(ExceptionUtils.getFullStackTrace(e));
//                                }
//                            }
//                        }
//                    }


//                } else {
//
//
//
//
//
////                logger.info("Total found: {}  Pages: {}", totalFound, totalPages);
////
////                logger.info(reqString);
//
//
            }

            publDate = publDate.plusDays(1);
        }


        logger.info("Head Hunter Receiver Finished");
    }

    public ArrayList<Map<String, Object>> searchForVacancy(LocalDate publDate, HashMap<String, Object> searchParams) {
        Map<String, Object> resposeMap;
        String reqString;
        String jsonString;

        Integer perPage = 100;
        Integer totalPages = 0;
        Integer pageNum = 0;
        Integer found = 0;

        Integer areaId = (Integer) searchParams.get("AREA");

        String specId = (String) searchParams.get("SPEC");

        RestTemplate restTemplate = new RestTemplate();

        SearchRequest searchRequest = new SearchRequest();

        ArrayList resArray = new ArrayList<Map<String, Object>>();

        while (pageNum == 0 || totalPages > pageNum) {
            try {

                reqString = String.format("https://api.hh.ru/vacancies?&date_from=%s&date_to=%s&per_page=100&page=%s&area=%s%s", publDate.toString(), publDate.toString(), pageNum, areaId, specId != null ? "&specialization=".concat(specId) : "");

                jsonString = restTemplate.getForObject(reqString, String.class);

                resposeMap = new Gson().fromJson(jsonString, new TypeToken<HashMap<String, Object>>() {
                }.getType());

                found = ((Double) resposeMap.get("found")).intValue();
                if (found > MAX_RESPOSE_SIZE) {
                    resArray = null;
                    break;
                }

                if (found > 0) {
                    resArray.add(resposeMap);
                }

                totalPages = ((Double) resposeMap.get("pages")).intValue();

                pageNum++;


            } catch (Exception e) {
                logger.error(ExceptionUtils.getFullStackTrace(e));
            }
        }

        return resArray;
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
                        String reqString = "https://api.hh.ru/vacancies/" + vacId;
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
        System.out.println("update_queue");
        StoredProcedureQuery sp = entityManager.createStoredProcedureQuery("public.add_vacancy_to_queue");
        List resultList = sp.getResultList();
        logger.info("added {} vacancies to the processing queue", resultList.get(0).toString());
    }
}
