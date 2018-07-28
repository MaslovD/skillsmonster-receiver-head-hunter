package com.masdmtr.skillsmonster.persistence.repository;

import com.masdmtr.skillsmonster.dto.SkillRankingDto;

import java.util.List;
import java.util.Map;

/**
 * Created by dmaslov on 10/1/17.
 */
public interface AnalyticsDao {


    List executeQuery(String query);
}
