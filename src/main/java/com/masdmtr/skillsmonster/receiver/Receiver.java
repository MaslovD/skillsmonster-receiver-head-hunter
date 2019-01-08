package com.masdmtr.skillsmonster.receiver;

import com.masdmtr.skillsmonster.dto.SearchRequestDto;
import com.masdmtr.skillsmonster.persistence.model.Vacancy;
import org.springframework.stereotype.Component;

/**
 * Created by dmaslov on 7/31/17.
 */

public interface Receiver {
    void searchVacancy();

    void loadVacancyDetails(SearchRequestDto searchRequestDto);

    Vacancy vacancyJsonToVacancy(String vacancyJson);
}
