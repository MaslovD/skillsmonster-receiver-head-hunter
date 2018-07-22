package com.masdmtr.skillsmonster.loader;


import com.masdmtr.skillsmonster.receiver.Receiver;
import com.masdmtr.skillsmonster.service.SkillsMonsterService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * Created by dmaslov on 7/25/17.
 */

@SuppressWarnings("ThrowablePrintedToSystemOut")
@Component
public class LoaderCore {

    private SkillsMonsterService skillsMonsterService;
    private LoaderController loaderController;
    private Logger logger;

    @Autowired
    public LoaderCore(SkillsMonsterService skillsMonsterService, LoaderController loaderController, Logger logger) {
        this.skillsMonsterService = skillsMonsterService;
        this.loaderController = loaderController;
        this.logger = logger;

    }

    @Scheduled(cron = "${spring.skillsmonster.schedule.vacancy_searcher}")
    //  @Scheduled(fixedDelay = 864000)
    public void search() {
        logger.info("vacancy searcher started");
        loaderController.getReceivers().forEach(Receiver::searchVacancy);
        logger.info("vacancy searcher finished");
    }

//    @Scheduled(cron = "${spring.skillsmonster.schedule.queue_updater}")
//    public void updateProcessingQueue() {
//        logger.info("processing queue updater started");
//        loaderController.getReceivers().forEach(Receiver::updateProcessingQueue);
//        logger.info("processing queue updater finished");
//    }

//    @Scheduled(cron = "${spring.skillsmonster.schedule.vacancy_loader}")
//    public void loadVacancyDetailes() {
//        logger.info("vacancy loader started");
//        loaderController.getReceivers().forEach(Receiver::loadVacancyDetailes);
//        logger.info("vacancy loader detailes finished");
//    }
}
