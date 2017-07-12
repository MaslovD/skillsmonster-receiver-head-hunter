package com.masdmtr.skillsmonster.service;

import com.masdmtr.skillsmonster.dao.Dao;
import com.masdmtr.skillsmonster.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dmaslov on 13/07/17.
 */

@Service
public class SkillsMonsterServiceImpl implements SkillsMonsterService {

    @Autowired
    private Dao dao;

    public List<Country> getCountryList() {

        return dao.getCountryList();

    }
}
