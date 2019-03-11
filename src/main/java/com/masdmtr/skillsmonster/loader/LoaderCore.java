package com.masdmtr.skillsmonster.loader;

import com.masdmtr.skillsmonster.receiver.Receiver;
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

    private LoaderController loaderController;
    private Logger logger;

    @Autowired
    public LoaderCore(LoaderController loaderController, Logger logger) {
        this.loaderController = loaderController;
        this.logger = logger;

    }

    // @Scheduled(cron = "${spring.skillsmonster.schedule.vacancy_searcher}")
    @Scheduled(fixedDelay = 86400000)
    public void search() {
        logger.info("Vacancy searcher started");
        loaderController.getReceivers().forEach(Receiver::searchVacancy);
        logger.info("Vacancy searcher finished");
    }
}
