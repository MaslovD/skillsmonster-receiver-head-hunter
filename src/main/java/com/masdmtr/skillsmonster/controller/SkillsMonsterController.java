package com.masdmtr.skillsmonster.controller;

import com.masdmtr.skillsmonster.entity.Country;
import com.masdmtr.skillsmonster.service.SkillsMonsterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by dmaslov on 13/07/17.
 */
@Controller
@RequestMapping(value = "/api/")
public class SkillsMonsterController {
    private final String apiVer = "v1";
    @Autowired
    private SkillsMonsterService skillsMonsterService;

    @RequestMapping(value = apiVer + "/country/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<Country> listCountries() {
        List<Country> userDetails = skillsMonsterService.getCountryList();
        return userDetails;
    }

    @RequestMapping(value = apiVer + "/country/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String create(@RequestBody Country cnt) {

        Country country = new Country();
        country.setShortName(cnt.getShortName());
        country.setFullName(cnt.getFullName());
        country.setNumCode(cnt.getNumCode());
        country.setAlpha2(cnt.getAlpha2());
        country.setAlpha3(cnt.getAlpha3());
        skillsMonsterService.addCountry(country);

        return "";
    }


}
