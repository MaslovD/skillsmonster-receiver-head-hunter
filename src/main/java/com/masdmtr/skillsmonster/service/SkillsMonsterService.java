package com.masdmtr.skillsmonster.service;

import com.masdmtr.skillsmonster.entity.*;

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

    void addVacancy(Vacancy vacancy);

    void addSearchRequest(SearchRequest searchRequest);
}
