package com.masdmtr.skillsmonster.dao;

import com.masdmtr.skillsmonster.entity.Area;
import com.masdmtr.skillsmonster.entity.Country;
import com.masdmtr.skillsmonster.entity.SearchResult;
import com.masdmtr.skillsmonster.entity.SourceSite;

import java.util.List;

/**
 * Created by dmaslov on 09/07/17.
 */
public interface SkillsMonsterDao {
    List<Country> getCountryList();

    void addCountry(Country country);

    Area getAreaById(int id);

    SourceSite getSourceSiteById(int id);

    void addSearchResult(SearchResult searchResult);
}
