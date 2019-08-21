package com.masdmtr.skillsmonster.service;

import com.masdmtr.skillsmonster.persistence.model.ui.Menu;
import com.masdmtr.skillsmonster.persistence.model.*;

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

    void saveVacancyFromArchToDatabase();

    ArrayList<Area> getAreaList();

    void getVacancyDetailes();


    List<Integer> getAreaCountryList();

    ArrayList<Area> getAreaByCountryId(Integer countryId);

    void loadFromHhByVacancyId(Long startId, Long endId);
}
