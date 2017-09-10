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

    ArrayList<VacancyToLoadHh> getListToLoadFromHh();

    ArrayList<Menu> getMenu();


    void addVacancy(Vacancy vacancy);

    void addSearchRequest(SearchRequest searchRequest);

    ArrayList<Specialization> getSpecializationList();
}
