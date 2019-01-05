package com.masdmtr.skillsmonster.receiver;

import com.masdmtr.skillsmonster.dto.SearchRequestDto;
import com.masdmtr.skillsmonster.dto.SearchResultDto;
import org.springframework.stereotype.Component;

/**
 * Created by dmaslov on 7/31/17.
 */
@Component
public interface Receiver {
    void searchVacancy();

    //void loadVacancyDetailes();

    void loadVacancyDetails(SearchResultDto processingQueueItem);

    void loadVacancyDetails(SearchRequestDto searchRequestDto);

    //  void updateProcessingQueue();

}
