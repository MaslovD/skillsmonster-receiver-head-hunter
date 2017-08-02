package com.masdmtr.skillsmonster.receiver;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.masdmtr.skillsmonster.entity.SearchRequest;
import com.masdmtr.skillsmonster.entity.SearchResult;
import com.masdmtr.skillsmonster.loader.LoaderController;
import com.masdmtr.skillsmonster.service.SkillsMonsterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
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

        System.out.println("Head Hunter Load.");

        LocalDateTime timePoint = LocalDateTime.now();
        LocalDate theDate = timePoint.toLocalDate();
        String industry = "";//"&industry=7.540";

        for (long x = 0; x < 31; x++) {
            LocalDate publDate = LocalDate.of(2017, Month.JULY, 04).plusDays(x);

            System.out.println("date: " + publDate.toString());

            RestTemplate restTemplate = new RestTemplate();
            Integer pageNum = 0;
            Long totalPages = 100L;

            while (totalPages > pageNum) {
                System.out.print("page: " + pageNum);
                String reqString = "https://api.hh.ru/vacancies?area=1" + industry + "&date_from=" + publDate.toString() + "&date_to=" + publDate.toString() + "&per_page=500&page=" + pageNum.toString();

                String jsonString = restTemplate.getForObject(reqString, String.class);

                Map<String, Object> retMap = new Gson().fromJson(jsonString, new TypeToken<HashMap<String, Object>>() {
                }.getType());

                totalPages = Math.round((Double) retMap.get("pages"));

                SearchRequest searchRequest = new SearchRequest();
                SearchResult searchResult = new SearchResult();
                searchResult.setPage(pageNum);
                searchResult.setRawResponse(retMap);
                skillsMonsterService.addSearchResult(searchResult);


                pageNum++;

            }

        }

    }
}
