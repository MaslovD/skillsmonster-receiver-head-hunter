package com.masdmtr.skillsmonster.receiver;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.masdmtr.skillsmonster.entity.*;
import com.masdmtr.skillsmonster.service.SkillsMonsterService;
import org.apache.commons.lang.exception.ExceptionUtils;
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
        Integer perPage = 500;
        ArrayList<Specialization> specializationList = skillsMonsterService.getSpecializationList();

        ArrayList<Area> areaList = skillsMonsterService.getAreaList();

        //LocalDate dateFrom = LocalDate.of(2017, Month.SEPTEMBER, 05);
        LocalDate dateFrom = LocalDate.now().minusDays(1);
        LocalDate dateTo = LocalDate.now().minusDays(1);

        //LocalDate dateTo = LocalDate.of(2017, Month.AUGUST, 06);

        LocalDate publDate = dateFrom;

        while (publDate.compareTo(dateTo) <= 0) {

            logger.debug(String.valueOf(dateFrom.compareTo(dateTo)));

            for (Area ar : areaList) {

                for (Specialization spec : specializationList) {

                    RestTemplate restTemplate = new RestTemplate();

                    Integer pageNum = 0;

                    Long totalPages = 100L;

                    Object[] params = new Object[]{spec.getSubId()};

                    String specialization = MessageFormat.format(specializationTmp, params);

                    params = new Object[]{ar.getId().toString()};
                    String area = MessageFormat.format(areaTmp, params);

                    logger.info("Date: {}, Specialization: {}, Area: {}", publDate.toString(), spec.getSubId(), ar.getId().toString());

                    while (totalPages > pageNum) {
                        try {

                            SearchRequest searchRequest = new SearchRequest();

                            String reqString = "https://api.hh.ru/vacancies?" + specialization + area + industry + "date_from=" + publDate.toString() + "&date_to=" + publDate.toString() + "&per_page=" + perPage.toString() + "&page=" + pageNum.toString();
                            logger.debug(reqString);
                            String jsonString = restTemplate.getForObject(reqString, String.class);

                            searchRequest.setRawRequest(reqString);
                            searchRequest.setDateTime(new Timestamp(System.currentTimeMillis()));
                            //skillsMonsterService.addSearchRequest(searchRequest);

                            Map<String, Object> retMap = new Gson().fromJson(jsonString, new TypeToken<HashMap<String, Object>>() {
                            }.getType());

                            totalPages = Math.round((Double) retMap.get("pages"));
                            Long totalFound = Math.round((Double) retMap.get("found"));
                            logger.info("Total found: {}  Page: {}", totalFound, pageNum);

                            searchRequest.setPages(totalPages);
                            searchRequest.setFound(totalFound);
                            searchRequest.setPerPage(perPage);
                            searchRequest.setAreaByAreaId(ar);
                            //Timestamp.valueOf(publDate.atStartOfDay());
                            //Timestamp timestamp = Timestamp.valueOf(publDate.atStartOfDay());
                            searchRequest.setPeriodFrom(Timestamp.valueOf(publDate.atStartOfDay()));
                            searchRequest.setPeriodTo(Timestamp.valueOf(publDate.atStartOfDay()));
                            searchRequest.setSpecializationId(spec);

                            SearchResult searchResult = new SearchResult();
                            searchResult.setSearchRequest(searchRequest);
                            searchResult.setPage(pageNum);
                            searchResult.setRawResponse(retMap);
                            searchResult.setFound(totalFound);
                            searchResult.setStatus("NEW");
                            if (totalFound > 0) {
                                skillsMonsterService.addSearchResult(searchResult);
                            }
                            pageNum++;

                        } catch (Exception e) {
                            logger.error(ExceptionUtils.getFullStackTrace(e));
                        }
                    }
                }
            }
            publDate = publDate.plusDays(1);
        }
        logger.info("Head Hunter Receiver Finished");
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
                        logger.info("Vacancy ID: {} Created: {}", vacId, processingQueueItem.getCreatedAt());
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
                        logger.error("Error loading info from hh.ru ID: {}", vacId);
                        logger.error(ExceptionUtils.getFullStackTrace(ex));
                    }
                });
    }

    @Override
    @Transactional
    public void updateProcessingQueue() {

        StoredProcedureQuery sp = entityManager.createStoredProcedureQuery("public.add_vacancy_to_queue");
        List resultList = sp.getResultList();
        logger.info("added {} vacancies to the processing queue", resultList.get(0).toString());
    }
}
