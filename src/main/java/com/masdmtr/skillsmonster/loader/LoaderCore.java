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


    @Scheduled(cron ="${skillsmonster.demon.cron}")
    //@Scheduled(fixedDelay = 864000)
    public void load() {
        loaderController.getReceivers().forEach(Receiver::load);
    }

    @Scheduled(cron ="${skillsmonster.receiver.hh.queue_update}")
    public void updateProcessingQueue() {
        loaderController.getReceivers().forEach(Receiver::updateProcessingQueue);
    }


    //@Scheduled(cron ="${skillsmonster.receiver.hh}")
    //@Scheduled(fixedDelay = 86400)
    public void loadVacancyDetailes() {
        loaderController.getReceivers().forEach(Receiver::loadVacancyDetailes);
    }
}
