package com.masdmtr.skillsmonster.dao;

import com.masdmtr.skillsmonster.entity.analytics.TopProgLangRuHh;

import java.util.List;
import java.util.Map;

/**
 * Created by dmaslov on 10/1/17.
 */
public interface AnalyticsDao {

    
    List<TopProgLangRuHh> executeQuery(String query);
}
