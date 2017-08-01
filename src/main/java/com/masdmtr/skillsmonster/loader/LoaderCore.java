package com.masdmtr.skillsmonster.loader;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.masdmtr.skillsmonster.entity.Area;
import com.masdmtr.skillsmonster.entity.SearchRequest;
import com.masdmtr.skillsmonster.entity.SearchResult;
import com.masdmtr.skillsmonster.receiver.Receiver;
import com.masdmtr.skillsmonster.service.SkillsMonsterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import sun.misc.Contended;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dmaslov on 7/25/17.
 */

@Component
public class LoaderCore {

    @Autowired
    private SkillsMonsterService skillsMonsterService;
    @Autowired
    private LoaderController loaderController;

    @Scheduled(fixedRate = 1000)
    public void reportCurrentTime() {
        RestTemplate restTemplate = new RestTemplate();

        loaderController.getReceivers().forEach(Receiver::load);


        // SearchRequest searchRequest = new SearchRequest();

        //searchRequest.setAreaByAreaId(skillsMonsterService.getAreaById(1));
        //searchRequest.setSourceSiteBySourceSiteId(1);

        //SearchResult searchResult = new SearchResult();
        //searchResult.setId(3);
        //searchResult.setPage(1);
        //searchResult.setSearchReauestId(1);

        //searchResult.setRawResponse(retMap);
        //country.setRawJson(retMap);


        String jsonString = restTemplate.getForObject("https://api.hh.ru/vacancies?area=1&text=java&inductries=7.540&date_from=2017-07-19&date_to=2017-07-19&per_page=500", String.class);


        Map<String, String> retMap = new Gson().fromJson(jsonString, new TypeToken<HashMap<String, Object>>() {
        }.getType());

        SearchRequest searchRequest = new SearchRequest();

        //searchRequest.setAreaByAreaId(skillsMonsterService.getAreaById(1));
        //searchRequest.setSourceSiteBySourceSiteId(1);

        SearchResult searchResult = new SearchResult();
        // searchResult.setId(3);
        searchResult.setPage(1);
        //searchResult.setSearchReauestId(1);

        searchResult.setRawResponse(retMap);

        skillsMonsterService.addSearchResult(searchResult);

        System.out.println("TEST");

    }

}
