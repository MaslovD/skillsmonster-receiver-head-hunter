package com.masdmtr.skillsmonster.dao;

import com.masdmtr.skillsmonster.entity.Country;

import java.util.List;

/**
 * Created by dmaslov on 09/07/17.
 */
public interface SkillsMonsterDao {
    public List<Country> getCountryList();

    void addCountry(Country country);
}
