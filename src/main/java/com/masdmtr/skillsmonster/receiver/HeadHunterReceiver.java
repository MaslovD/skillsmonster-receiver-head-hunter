package com.masdmtr.skillsmonster.receiver;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.masdmtr.skillsmonster.entity.SearchRequest;
import com.masdmtr.skillsmonster.entity.SearchResult;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.util.HashMap;
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
        String specialization = "specialization=1.221&";
        Integer perPage = 500;

        LocalDate dateFrom = LocalDate.of(2017, Month.AUGUST, 05);

        LocalDate dateTo = LocalDate.now();

        //LocalDate dateTo = LocalDate.of(2017, Month.AUGUST, 06);


        LocalDate publDate = dateFrom;

        while (dateFrom.compareTo(dateTo) <= 0) {

            System.out.println("Date: " + publDate.toString());

            RestTemplate restTemplate = new RestTemplate();
            Integer pageNum = 0;
            Long totalPages = 100L;

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
                searchRequest.setPeriodFrom(new Timestamp(publDate.toEpochDay()));
                searchRequest.setPeriodTo(new Timestamp(publDate.toEpochDay()));
                SearchResult searchResult = new SearchResult();
                searchResult.setSearchRequest(searchRequest);
                searchResult.setPage(pageNum);
                searchResult.setRawResponse(retMap);
                skillsMonsterService.addSearchResult(searchResult);

                pageNum++;

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
