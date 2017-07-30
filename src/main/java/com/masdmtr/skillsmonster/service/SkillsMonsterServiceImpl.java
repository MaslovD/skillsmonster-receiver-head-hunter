package com.masdmtr.skillsmonster.service;

import com.masdmtr.skillsmonster.dao.SkillsMonsterDao;
import com.masdmtr.skillsmonster.entity.Area;
import com.masdmtr.skillsmonster.entity.Country;
import com.masdmtr.skillsmonster.entity.SearchResult;
import com.masdmtr.skillsmonster.entity.SourceSite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dmaslov on 13/07/17.
 */

@Service
public class SkillsMonsterServiceImpl implements SkillsMonsterService {

    @Autowired
    private SkillsMonsterDao dao;

    @Override
    public List<Country> getCountryList() {
        return dao.getCountryList();
    }

    @Override
    public void addSearchResult(SearchResult searchResult) {
        dao.addSearchResult(searchResult);
    }

    @Override
    public Area getAreaById(int id) {
        return dao.getAreaById(id);
    }

    @Override
    public SourceSite getSourceSite(int id) {
        return dao.getSourceSiteById(id);
    }

    @Override
    public void addCountry(Country country) {
        dao.addCountry(country);
    }
}