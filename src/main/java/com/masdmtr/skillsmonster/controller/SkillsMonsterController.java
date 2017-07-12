package com.masdmtr.skillsmonster.controller;

import com.masdmtr.skillsmonster.entity.Country;
import com.masdmtr.skillsmonster.service.SkillsMonsterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by dmaslov on 13/07/17.
 */
@Controller
public class SkillsMonsterController {
    @Autowired
    private SkillsMonsterService skillsMonsterService;

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<Country> countries() {
        List<Country> userDetails = skillsMonsterService.getCountryList();
        return userDetails;

    }
    /*ResponseEntity<List<Country>> userDetails() {

        List<Country> userDetails = skillsMonsterService.getCountryList();

        return new ResponseEntity<List<Country>>(userDetails, HttpStatus.OK);
    }*/
}
