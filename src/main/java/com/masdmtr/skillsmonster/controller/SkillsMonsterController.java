package com.masdmtr.skillsmonster.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.masdmtr.skillsmonster.entity.Country;
import com.masdmtr.skillsmonster.service.SkillsMonsterService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by dmaslov on 13/07/17.
 */
@Controller
@RequestMapping(value = "/api/")
public class SkillsMonsterController {
    private final String apiVer = "v1";

    @Autowired
    private SkillsMonsterService skillsMonsterService;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = apiVer + "/country/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<Country> listCountries() {
        List<Country> userDetails = skillsMonsterService.getCountryList();
        return userDetails;
    }

    @RequestMapping(value = apiVer + "/country/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String create(@RequestBody Country cnt) {


        Map<String, String> map = new HashMap<>();

        Country country = new Country();
        country.setShortName(cnt.getShortName());
        country.setFullName(cnt.getFullName());
        country.setNumCode(cnt.getNumCode());
        country.setAlpha2(cnt.getAlpha2());
        country.setAlpha3(cnt.getAlpha3());

        map.put("test", "TEST");
        //country.setRawJson("{\"alpha2\":\"US\",\"shortName\":\"United States\",\"fullName\":\"Unites States of America\",\"alpha3\":\"USA\",\"numCode\":840}");

        String jsonString = restTemplate.getForObject("https://api.hh.ru/vacancies?area=1&text=java&inductries=7.540&date_from=2017-07-17&date_to=2017-07-17", String.class);


        Map<String, String> retMap = new Gson().fromJson(jsonString, new TypeToken<HashMap<String, Object>>() {

        }.getType());


        country.setRawJson(retMap);

        skillsMonsterService.addCountry(country);

        return "";
    }


}
