package com.masdmtr.skillsmonster.service;

import com.masdmtr.skillsmonster.entity.Area;
import com.masdmtr.skillsmonster.entity.Country;
import com.masdmtr.skillsmonster.entity.SearchResult;
import com.masdmtr.skillsmonster.entity.SourceSite;

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
}
