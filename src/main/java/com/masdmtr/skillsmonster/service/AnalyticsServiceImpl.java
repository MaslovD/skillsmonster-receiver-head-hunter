package com.masdmtr.skillsmonster.service;

import com.masdmtr.skillsmonster.dao.AnalyticsDao;
import com.masdmtr.skillsmonster.entity.analytics.TopProgLangRuHh;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by dmaslov on 10/1/17.
 */
@Component
public class AnalyticsServiceImpl implements AnalyticsService {
    @Autowired
    private AnalyticsDao dao;

    @Override
    public List getTopProgrammingLang() {

        String query = "SELECT * from top_prog_lang_ru_hh";

        return dao.executeQuery(query);

    }
}
