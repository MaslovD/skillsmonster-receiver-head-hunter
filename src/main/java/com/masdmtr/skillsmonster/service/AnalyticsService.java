package com.masdmtr.skillsmonster.service;


import com.masdmtr.skillsmonster.dto.SkillRankingItem;

import java.util.List;

/**
 * Created by dmaslov on 10/1/17.
 */
public interface AnalyticsService {
    List getTopProgrammingLang();

    List<SkillRankingItem> getSkillByMonthByNameRuHh(String skillName);
}
