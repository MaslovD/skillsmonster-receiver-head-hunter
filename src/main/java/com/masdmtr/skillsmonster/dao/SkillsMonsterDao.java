package com.masdmtr.skillsmonster.dao;

import com.masdmtr.skillsmonster.entity.*;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmaslov on 09/07/17.
 */
public interface SkillsMonsterDao {
    List<Country> getCountryList();

    ArrayList<Specialization> getSpecializationList();

    void addCountry(Country country);

    Area getAreaById(int id);

    SourceSite getSourceSiteById(int id);

    void addSearchResult(SearchResult searchResult);

    ArrayList getListToLoadFromHh();

    ArrayList getMenu();

    void addVacancy(Vacancy vacancy);

    void addSearchRequest(SearchRequest searchRequest);

    ArrayList<Area> getAreaList();

    void getVacancyDetailes();

    void updateProcessingQueueItem(ProcessingQueue processingQueueItem);

    void getAreaChildren(Area area);

    ArrayList<Integer> getAreaCountryList();

    ArrayList<Area> getAreaByCountryId(Integer countryId);
}
