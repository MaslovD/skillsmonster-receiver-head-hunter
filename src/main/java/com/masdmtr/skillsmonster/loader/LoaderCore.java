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


    @Scheduled(fixedRate = 86400000)
    public void reportCurrentTime() {
        RestTemplate restTemplate = new RestTemplate();

        loaderController.getReceivers().forEach(Receiver::load);

    }

}
