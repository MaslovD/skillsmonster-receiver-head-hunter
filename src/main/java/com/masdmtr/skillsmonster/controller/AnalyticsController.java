package com.masdmtr.skillsmonster.controller;

import com.masdmtr.skillsmonster.entity.analytics.TopProgLangRuHh;
import com.masdmtr.skillsmonster.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping(value = "/top-programming-lang", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<TopProgLangRuHh> getTopProgrammingLang() {
        List<TopProgLangRuHh> response = analyticsService.getTopProgrammingLang();
        return response;

    }
}
