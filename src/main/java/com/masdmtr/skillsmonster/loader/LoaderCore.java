package com.masdmtr.skillsmonster.loader;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.masdmtr.skillsmonster.entity.*;
import com.masdmtr.skillsmonster.receiver.Receiver;
import com.masdmtr.skillsmonster.service.SkillsMonsterService;
import org.slf4j.Logger;
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

    @Autowired
    private Logger logger;


    //TODO
    //
    @Scheduled(cron = "${skillsmonster.demon.cron}")
    //@Scheduled(fixedDelay = 864000)
    public void load() {
        logger.info("vacancy searcher started");
        loaderController.getReceivers().forEach(Receiver::searchVacancy);
        logger.info("vacancy searcher finished");
    }

    //TODO
    @Scheduled(cron = "${skillsmonster.receiver.hh.queue_update}")
    public void updateProcessingQueue() {
        logger.info("processing queue updater started");
        loaderController.getReceivers().forEach(Receiver::updateProcessingQueue);
        logger.info("processing queue updater finished");
    }

    //TODO
    @Scheduled(cron = "${skillsmonster.receiver.hh}")
    public void loadVacancyDetailes() {
        logger.info("vacancy loader started");
        loaderController.getReceivers().forEach(Receiver::loadVacancyDetailes);
        logger.info("vacancy loader detailes finished");
    }
}
