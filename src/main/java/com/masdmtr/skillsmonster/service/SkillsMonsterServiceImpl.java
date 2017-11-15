package com.masdmtr.skillsmonster.service;

import com.masdmtr.skillsmonster.dao.SkillsMonsterDao;
import com.masdmtr.skillsmonster.entity.*;
import com.masdmtr.skillsmonster.entity.ui.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public ArrayList<ProcessingQueue> getProcessingQueue() {
        return dao.getListToLoadFromHh();
    }

    @Override
    public ArrayList<Menu> getMenu() {
        ArrayList tmp = dao.getMenu();
        return tmp;
    }

    @Override
    public void addVacancy(Vacancy vacancy) {
        dao.addVacancy(vacancy);
    }



    @Override
    public ArrayList<Specialization> getSpecializationList() {
        ArrayList tmp = dao.getSpecializationList();
        return tmp;
    }

    @Override
    public ArrayList<Area> getAreaList() {
        ArrayList tmp = dao.getAreaList();
        return tmp;
    }

    @Override
    public void getVacancyDetailes() {
        dao.getVacancyDetailes();
    }

    @Override
    public void updateProcessingQueue(ProcessingQueue processingQueueItem) {
        dao.updateProcessingQueueItem(processingQueueItem);
    }

    @Override
    public void addCountry(Country country) {
        dao.addCountry(country);
    }

    public void getAreaChildren() {
        //   dao.getAreaChildren();
    }

    public ArrayList<Integer> getAreaCountryList() {
        return dao.getAreaCountryList();

    }

    @Override
    public ArrayList<Area> getAreaByCountryId(Integer countryId) {
        return dao.getAreaByCountryId(countryId);

    }
}