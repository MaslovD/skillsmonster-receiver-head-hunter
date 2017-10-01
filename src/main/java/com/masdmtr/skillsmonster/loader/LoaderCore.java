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


    @Scheduled(cron = "${skillsmonster.demon.cron}")
    //@Scheduled(fixedDelay = 864000)
    public void load() {
        logger.info("search vacancy started");
        loaderController.getReceivers().forEach(Receiver::searchVacancy);
        logger.info("search vacancy finished");
    }

    @Scheduled(cron = "${skillsmonster.receiver.hh.queue_update}")
    public void updateProcessingQueue() {
        logger.info("processing Queue updater started");
        loaderController.getReceivers().forEach(Receiver::updateProcessingQueue);
        logger.info("processing Queue updater finished");
    }

    @Scheduled(cron = "${skillsmonster.receiver.hh}")
    public void loadVacancyDetailes() {
        logger.info("load vacancy detailes started");
        loaderController.getReceivers().forEach(Receiver::loadVacancyDetailes);
        logger.info("load vacancy detailes finished");
    }
}
