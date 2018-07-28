package com.masdmtr.skillsmonster.receiver;

import com.masdmtr.skillsmonster.dto.VacancyDto;
import org.springframework.stereotype.Component;

/**
 * Created by dmaslov on 7/31/17.
 */
@Component
public interface Receiver {
    void searchVacancy();

    //void loadVacancyDetailes();

    void loadVacancyDetailsMq(VacancyDto processingQueueItem);

    //  void updateProcessingQueue();

}
