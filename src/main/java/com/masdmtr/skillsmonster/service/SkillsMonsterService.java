package com.masdmtr.skillsmonster.service;

import com.masdmtr.skillsmonster.entity.Country;

import java.util.List;

/**
 * Created by dmaslov on 13/07/17.
 */
public interface SkillsMonsterService {
    List<Country> getCountryList();
    void addCountry(Country country);
}
