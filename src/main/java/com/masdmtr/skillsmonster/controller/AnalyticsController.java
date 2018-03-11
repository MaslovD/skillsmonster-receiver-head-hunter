package com.masdmtr.skillsmonster.controller;

import com.masdmtr.skillsmonster.dto.SkillRankingItem;

import com.masdmtr.skillsmonster.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by dmaslov on 10/1/17.
 */

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/" + "${spring.skillsmonster.api.version}" + "/analytics")
public class AnalyticsController {

    @Autowired
    private AnalyticsService analyticsService;

    @RequestMapping(value = "/skill-by-month-by-name-ru-hh/{skillName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<SkillRankingItem> getSkillByMonthByNameRuHh(@PathVariable String skillName) {

        List<SkillRankingItem> response = analyticsService.getSkillByMonthByNameRuHh(skillName);
        return response;

    }
}
