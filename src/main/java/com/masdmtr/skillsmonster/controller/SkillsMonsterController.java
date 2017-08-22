package com.masdmtr.skillsmonster.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.masdmtr.skillsmonster.entity.Country;
import com.masdmtr.skillsmonster.entity.SearchResult;
import com.masdmtr.skillsmonster.entity.ui.Menu;
import com.masdmtr.skillsmonster.service.SkillsMonsterService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by dmaslov on 13/07/17.
 */
@Controller
@CrossOrigin(origins = "http://localhost:4200")
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
        SearchResult searchResult = new SearchResult();
        searchResult.setId(2);
        searchResult.setPage(1);
        //searchResult.setSearchReauestId(1);

        Country country = new Country();
        country.setShortName(cnt.getShortName());
        country.setFullName(cnt.getFullName());
        country.setNumCode(cnt.getNumCode());
        country.setAlpha2(cnt.getAlpha2());
        country.setAlpha3(cnt.getAlpha3());

        map.put("test", "TEST");
        //country.setRawJson("{\"alpha2\":\"US\",\"shortName\":\"United States\",\"fullName\":\"Unites States of America\",\"alpha3\":\"USA\",\"numCode\":840}");

        String jsonString = restTemplate.getForObject("https://api.hh.ru/vacancies?area=1&text=java&inductries=7.540&date_from=2017-07-17&date_to=2017-07-17&per_page=500", String.class);


        Map<String, Object> retMap = new Gson().fromJson(jsonString, new TypeToken<HashMap<String, Object>>() {

        }.getType());

        searchResult.setRawResponse(retMap);
        //country.setRawJson(retMap);

        //skillsMonsterService.addCountry(country, searchResult);

        return "";
    }

    @RequestMapping(value = apiVer + "/menu", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<Menu> getMenu() {
        List<Menu> userDetails = skillsMonsterService.getMenu();
        return userDetails;
    }


    @RequestMapping(value = apiVer + "/app/menu", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String getSideMenu() {

        return "[\n" +
                "  {\n" +
                "    \"title\": \"Main\",\n" +
                "    \"groupTitle\" : true\n" +
                "  },\n" +
                "  {\n" +
                "    \"title\": \"Dashboard\",\n" +
                "    \"icon\": {\n" +
                "      \"class\": \"fa fa-home\",\n" +
                "      \"bg\": \"rgba(255,255,255,0.1)\",\n" +
                "      \"color\": \"#fff\"\n" +
                "    },\n" +
                "    \"routing\": \"/default-layout/dashboard\",\n" +
                "    \"badge\": {\n" +
                "      \"text\": \"New\",\n" +
                "      \"color\": \"#fff\",\n" +
                "      \"bg\": \"#E57373\"\n" +
                "    },\n" +
                "    \"sub\": [\n" +
                "      {\n" +
                "        \"title\": \"Skills\",\n" +
                "        \"routing\": \"/default-layout/dashboard\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"title\": \"Dashboard v2\",\n" +
                "        \"routing\": \"/default-layout/dashboard-2\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  {\n" +
                "    \"title\": \"Widgets\",\n" +
                "    \"icon\": {\n" +
                "      \"class\": \"fa fa-th\",\n" +
                "      \"bg\": \"rgba(255,255,255,0.1)\",\n" +
                "      \"color\": \"#fff\"\n" +
                "    },\n" +
                "    \"routing\": \"/default-layout/widgets\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"title\": \"Vacancy calendar\",\n" +
                "    \"icon\": {\n" +
                "      \"class\": \"fa fa-calendar\",\n" +
                "      \"bg\": \"rgba(255,255,255,0.1)\",\n" +
                "      \"color\": \"#fff\"\n" +
                "    },\n" +
                "    \"routing\": \"/default-layout/calendar\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"title\": \"Detailed info by\",\n" +
                "    \"groupTitle\" : true\n" +
                "  },\n" +
                "  {\n" +
                "    \"title\": \"Programming language\",\n" +
                "    \"icon\": {\n" +
                "      \"class\": \"fa fa-briefcase\",\n" +
                "      \"bg\": \"rgba(255,255,255,0.1)\",\n" +
                "      \"color\": \"#fff\"\n" +
                "    },\n" +
                "    \"sub\": [\n" +
                "      {\n" +
                "        \"title\": \"Java\",\n" +
                "        \"routing\": \"/default-layout/button\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"title\": \"JavaScript\",\n" +
                "        \"routing\": \"/default-layout/card\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"title\": \"Python\",\n" +
                "        \"routing\": \"/default-layout/checkbox\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"title\": \"Ruby\",\n" +
                "        \"routing\": \"/default-layout/chips\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "]\n";
    }

}
