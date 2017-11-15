package com.masdmtr.skillsmonster.service;

import com.masdmtr.skillsmonster.entity.*;
import com.masdmtr.skillsmonster.entity.ui.Menu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmaslov on 13/07/17.
 */
public interface SkillsMonsterService {
    List<Country> getCountryList();

    void addCountry(Country country);

    void addSearchResult(SearchResult searchResult);

    Area getAreaById(int id);

    SourceSite getSourceSite(int id);

    ArrayList<ProcessingQueue> getProcessingQueue();

    ArrayList<Menu> getMenu();


    void addVacancy(Vacancy vacancy);

    ArrayList<Specialization> getSpecializationList();

    ArrayList<Area> getAreaList();

    void getVacancyDetailes();

    void updateProcessingQueue(ProcessingQueue processingQueueItem);

    List<Integer> getAreaCountryList();

    ArrayList<Area> getAreaByCountryId(Integer countryId);

}
