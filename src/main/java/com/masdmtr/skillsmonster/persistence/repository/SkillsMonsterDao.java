package com.masdmtr.skillsmonster.persistence.repository;

import com.masdmtr.skillsmonster.persistence.model.*;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmaslov on 09/07/17.
 */
public interface SkillsMonsterDao {
    List<Country> getCountryList();

    ArrayList<Specialization> getSpecializationList();

    ArrayList<VacancyArch> getVacancyArchList(Long idStart, Long idEnd);

    void addCountry(Country country);


    Area getAreaById(int id);

    SourceSite getSourceSiteById(int id);

    void addSearchResult(SearchResult searchResult);

    ArrayList getListToLoadFromHh();

    ArrayList getMenu();

    void addVacancy(Vacancy vacancy);

    ArrayList<Area> getAreaList();

    void getVacancyDetailes();

    void updateProcessingQueueItem(ProcessingQueue processingQueueItem);

    void getAreaChildren(Area area);

    ArrayList<Integer> getAreaCountryList();

    ArrayList<Area> getAreaByCountryId(Integer countryId);
}
