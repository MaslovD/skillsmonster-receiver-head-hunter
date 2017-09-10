package com.masdmtr.skillsmonster.receiver;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.masdmtr.skillsmonster.entity.SearchRequest;
import com.masdmtr.skillsmonster.entity.SearchResult;
import com.masdmtr.skillsmonster.entity.Specialization;
import com.masdmtr.skillsmonster.entity.Vacancy;
import com.masdmtr.skillsmonster.loader.LoaderController;
import com.masdmtr.skillsmonster.service.SkillsMonsterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.management.Query;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.time.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by dmaslov on 7/17/17.
 */

@Component
@Qualifier("HeadHunter")
public class HeadHunterReceiver extends ReceiverImpl {
    @Autowired
    private SkillsMonsterService skillsMonsterService;
    private int id;

    @Override
    public void load() {


        System.out.println("Head Hunter Receiver Started");

        LocalDateTime timePoint = LocalDateTime.now();
        String industry = "";//"industry=7.540&";
        String area = "";//"area=1&";
        String specializationTmp = "specialization={0}&";
        Integer perPage = 500;
        ArrayList<Specialization> specializationList = skillsMonsterService.getSpecializationList();

        LocalDate dateFrom = LocalDate.of(2017, Month.AUGUST, 12);

        LocalDate dateTo = LocalDate.now();

        //LocalDate dateTo = LocalDate.of(2017, Month.AUGUST, 06);

        LocalDate publDate = dateFrom;

        while (dateFrom.compareTo(dateTo) <= 0) {

            for (Specialization spec : specializationList) {

                RestTemplate restTemplate = new RestTemplate();
                Integer pageNum = 0;
                Long totalPages = 100L;

                Object[] params = new Object[]{spec.getSubId()};
                String specialization = MessageFormat.format(specializationTmp, params);
                System.out.printf("Date: %s , Specialization: %s %n", publDate.toString(), spec.getSubId());

                while (totalPages > pageNum) {

                    SearchRequest searchRequest = new SearchRequest();

                    String reqString = "https://api.hh.ru/vacancies?" + specialization + area + industry + "date_from=" + publDate.toString() + "&date_to=" + publDate.toString() + "&per_page=" + perPage.toString() + "&page=" + pageNum.toString();

                    String jsonString = restTemplate.getForObject(reqString, String.class);

                    searchRequest.setRawRequest(reqString);
                    searchRequest.setDateTime(new Timestamp(System.currentTimeMillis()));
                    //skillsMonsterService.addSearchRequest(searchRequest);

                    Map<String, Object> retMap = new Gson().fromJson(jsonString, new TypeToken<HashMap<String, Object>>() {
                    }.getType());

                    totalPages = Math.round((Double) retMap.get("pages"));
                    Long totalFound = Math.round((Double) retMap.get("found"));
                    System.out.println("Total found: " + totalFound + " Page: " + pageNum);
                    searchRequest.setPages(totalPages);
                    searchRequest.setFound(totalFound);
                    searchRequest.setPerPage(perPage);
                    //Timestamp.valueOf(publDate.atStartOfDay());
                    //Timestamp timestamp = Timestamp.valueOf(publDate.atStartOfDay());
                    searchRequest.setPeriodFrom(Timestamp.valueOf(publDate.atStartOfDay()));
                    searchRequest.setPeriodTo(Timestamp.valueOf(publDate.atStartOfDay()));
                    searchRequest.setSpecializationId(spec);
                    SearchResult searchResult = new SearchResult();
                    searchResult.setSearchRequest(searchRequest);
                    searchResult.setPage(pageNum);
                    searchResult.setRawResponse(retMap);
                    skillsMonsterService.addSearchResult(searchResult);

                    pageNum++;

                }
            }
            publDate = publDate.plusDays(1);
        }

        System.out.println("Head Hunter Receiver Finished");
    }

    @Override
    public void loadVacancyDetailes() {

        RestTemplate restTemplate = new RestTemplate();

        skillsMonsterService.getListToLoadFromHh()
                //.stream()
                .parallelStream()
                .forEach(vac -> {
                    try {
                        String vacId = vac.getId();
                        System.out.println("ID: " + vacId);
                        String reqString = "https://api.hh.ru/vacancies/" + vacId;
                        String jsonString = restTemplate.getForObject(reqString, String.class);
                        Map<String, Object> retMap = new Gson().fromJson(jsonString, new TypeToken<HashMap<String, Object>>() {
                        }.getType());
                        Vacancy vacancy = new Vacancy();
                        vacancy.setId(vac.getId());
                        vacancy.setRawData(retMap);
                        vacancy.setLoadDateTime(new Timestamp(System.currentTimeMillis()));
                        skillsMonsterService.addVacancy(vacancy);
                    } catch (HttpClientErrorException ex) {
                        System.out.println("Error loading info from hh.ru");
                    }
                });
        skillsMonsterService.getListToLoadFromHh();

    }
}
