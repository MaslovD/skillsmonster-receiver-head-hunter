package com.masdmtr.skillsmonster.service;

import com.google.gson.Gson;
import com.masdmtr.skillsmonster.dto.SearchRequestDto;
import com.masdmtr.skillsmonster.persistence.model.*;
import com.masdmtr.skillsmonster.persistence.model.ui.Menu;
import com.masdmtr.skillsmonster.persistence.repository.SkillsMonsterDao;
import com.masdmtr.skillsmonster.rabbitmq.Producer;
import com.masdmtr.skillsmonster.receiver.Receiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * Created by dmaslov on 13/07/17.
 */

@Service
@Transactional
public class SkillsMonsterServiceImpl implements SkillsMonsterService {

    private SkillsMonsterDao skillsMonsterDao;

    private Gson gson;
    private Receiver receiver;
    private static final Logger logger = LoggerFactory.getLogger(SkillsMonsterServiceImpl.class);
    private Producer producer;

    @Autowired
    public SkillsMonsterServiceImpl(SkillsMonsterDao skillsMonsterDao,
                                    Gson gson,
                                    Producer producer
    ) {
        this.skillsMonsterDao = skillsMonsterDao;
        this.gson = gson;
        this.producer = producer;
    }

    @Autowired
    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public List<Country> getCountryList() {
        return skillsMonsterDao.getCountryList();
    }

    @Override
    public void addSearchResult(SearchResult searchResult) {
        skillsMonsterDao.addSearchResult(searchResult);
    }

    @Override
    public Area getAreaById(int id) {
        return skillsMonsterDao.getAreaById(id);
    }

    @Override
    public SourceSite getSourceSite(int id) {
        return skillsMonsterDao.getSourceSiteById(id);
    }

    @Override
    public ArrayList<ProcessingQueue> getProcessingQueue() {
        return skillsMonsterDao.getListToLoadFromHh();
    }

    @Override
    public ArrayList<Menu> getMenu() {
        ArrayList tmp = skillsMonsterDao.getMenu();
        return tmp;
    }

    @Override
    public void addVacancy(Vacancy vacancy) throws ConstraintViolationException, JpaSystemException {
        skillsMonsterDao.addVacancy(vacancy);
    }


    @Override
    public ArrayList<Specialization> getSpecializationList() {
        ArrayList tmp = skillsMonsterDao.getSpecializationList();
        return tmp;
    }

    @Override
    public void saveVacancyFromArchToDatabase() {

        // min id 180847
        // max id 4708761

        long index = 180847;


        while (index <= 4708761) {

            try {
                ArrayList<VacancyArch> vacancyArchList = skillsMonsterDao.getVacancyArchList(index, index);

                vacancyArchList.forEach(vacancyArch -> {

                    Vacancy vacancy = receiver.vacancyJsonToVacancy(gson.toJson(vacancyArch.getRawData()));

                    this.addVacancy(vacancy);

                    logger.info("vacancy {} added from archive, skills added {}", vacancy.getVacancyId(), vacancy.getSkills().size());

                });


                index++;

            } catch (Exception ex) {
                logger.error("vacancy processing {} error!", ex.getMessage());
            }
        }
        logger.info("vacancy processing finished!");
    }

    @Override
    public ArrayList<Area> getAreaList() {
        ArrayList tmp = skillsMonsterDao.getAreaList();
        return tmp;
    }

    @Override
    public void getVacancyDetailes() {
        skillsMonsterDao.getVacancyDetails();
    }

    @Override
    public void addCountry(Country country) {
        skillsMonsterDao.addCountry(country);
    }

    @Override
    public ArrayList<Area> getAreaByCountryId(Integer countryId) {
        return skillsMonsterDao.getAreaByCountryId(countryId);

    }

    @Override
    public void loadFromHhByVacancyId() {

        //long[] range = LongStream.iterate(1L, n -> n + 1L).limit(23471214L).toArray();

        List<Long> vacancyIdList = LongStream
                .iterate(1, n -> n + 1)
                .limit(23471214)
                .boxed().collect(Collectors.toList());

        Collections.shuffle(vacancyIdList);

        vacancyIdList.forEach(vacancyId -> {

            SearchRequestDto searchRequestDto = new SearchRequestDto((vacancyId));

            producer.sendMessage(searchRequestDto);

        });


    }

    public ArrayList<Integer> getAreaCountryList() {
        return skillsMonsterDao.getAreaCountryList();

    }
}