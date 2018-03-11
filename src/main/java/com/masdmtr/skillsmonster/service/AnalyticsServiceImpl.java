package com.masdmtr.skillsmonster.service;

import com.masdmtr.skillsmonster.dao.AnalyticsDao;
import com.masdmtr.skillsmonster.dto.SkillRankingItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by dmaslov on 10/1/17.
 */

@Component
public class AnalyticsServiceImpl implements AnalyticsService {

    private AnalyticsDao dao;

    @Autowired
    public AnalyticsServiceImpl(AnalyticsDao dao) {
        this.dao = dao;
    }

    @Override
    public List getTopProgrammingLang() {

        String query = "SELECT * from mv_top_prog_lang_ru_hh";

        return dao.executeQuery(query);

    }

    @Override
    public List<SkillRankingItem> getSkillByMonthByNameRuHh(String skillName) {
        String query = String.format("SELECT * from mv_top_skills_by_month_ru_hh WHERE skill like '%s'", skillName);

        return dao.executeQuery(query);
    }
}
