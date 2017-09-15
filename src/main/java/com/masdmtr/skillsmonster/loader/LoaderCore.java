package com.masdmtr.skillsmonster.loader;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.masdmtr.skillsmonster.entity.*;
import com.masdmtr.skillsmonster.receiver.Receiver;
import com.masdmtr.skillsmonster.service.SkillsMonsterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * Created by dmaslov on 7/25/17.
 */

@SuppressWarnings("ThrowablePrintedToSystemOut")
@Component
public class LoaderCore {

    @Autowired
    private SkillsMonsterService skillsMonsterService;
    @Autowired
    private LoaderController loaderController;

    @Scheduled(cron ="${skillsmonster.demon.cron}") //there was an issue with first invocation (I want it to start after main module start)
    //@Scheduled(fixedDelay = 864000)
    public void load() {
        loaderController.getReceivers().forEach(Receiver::load);
    }

    @Scheduled(fixedDelay = 86400)
    public void getsome() {
        loaderController.getReceivers().forEach(Receiver::loadVacancyDetailes);
    }
}
