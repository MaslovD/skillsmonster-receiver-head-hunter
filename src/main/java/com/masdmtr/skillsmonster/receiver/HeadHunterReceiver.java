package com.masdmtr.skillsmonster.receiver;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.masdmtr.skillsmonster.dto.ProcessingQueueItem;
import com.masdmtr.skillsmonster.entity.*;
import com.masdmtr.skillsmonster.service.SkillsMonsterService;
import com.rabbitmq.client.Consumer;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import com.masdmtr.skillsmonster.rabbitmq.Producer;

/**
 * Created by dmaslov on 7/17/17.
 * Reinvented by dmaslov on 7/22/18.
 */

@Component
@Qualifier("HeadHunter")
public class HeadHunterReceiver extends ReceiverImpl {
    private static final int MAX_RESPOSE_SIZE = 2000;

    private EntityManager entityManager;
    private SkillsMonsterService skillsMonsterService;
    private Producer producer;
    private RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @Value("${spring.skillsmonster.host}")
    String apiHost;

    @Autowired
    public HeadHunterReceiver(EntityManager entityManager, SkillsMonsterService skillsMonsterService, Producer producer, RestTemplate restTemplate) {
        this.entityManager = entityManager;
        this.skillsMonsterService = skillsMonsterService;
        this.producer = producer;
        this.restTemplate = restTemplate;
    }

    @Override
    public void searchVacancy() {

        logger.info("Head Hunter Receiver Started");

        LocalDate dateFrom = LocalDate.now().minusDays(1);
        LocalDate dateTo = LocalDate.now().minusDays(1);
        LocalDate publDate = dateFrom;

        List<Integer> areaCountryList = skillsMonsterService.getAreaCountryList();
        ArrayList<Specialization> specializationList = skillsMonsterService.getSpecializationList();


        /*
            For testing purposes
        */
        //List<Integer> areaCountryList = new LinkedList<Integer>();
        //areaCountryList.add(113);

        ArrayList searchResultArray = new ArrayList<Map<String, Object>>();

        HashMap searchParams = new HashMap<String, Object>();
        ArrayList<Map<String, Object>> vacancyListToAdd = new ArrayList<>();
        while (publDate.compareTo(dateTo) <= 0) {

            logger.debug(String.valueOf(dateFrom.compareTo(dateTo)));

            for (Integer countryid : areaCountryList) {
                searchParams.put("AREA", countryid);

                Integer pageNum = 0;
                Integer found = 0;

                ArrayList resArray = new ArrayList<Map<String, Object>>();


                ArrayList<Map<String, Object>> vacancyList = getVacancyList(publDate, searchParams);

                found = ((Double) vacancyList.get(0).get("found")).intValue();
                Integer areaId = (Integer) searchParams.get("AREA");
                if (found > MAX_RESPOSE_SIZE) {
                    vacancyList.clear();
                    logger.info("Date: {}, Area: {}, Found: {}", publDate.toString(), areaId.toString(), found);
                    ArrayList<Area> areaList = skillsMonsterService.getAreaByCountryId(areaId);

                    for (Area ar : areaList) {
                        vacancyList.clear();
                        searchParams.clear();
                        searchParams.put("AREA", ar.getId());

                        vacancyList = getVacancyList(publDate, searchParams);

                        found = ((Double) vacancyList.get(0).get("found")).intValue();

                        if (found > MAX_RESPOSE_SIZE) {
                            vacancyList.clear();
                            logger.info("\t\t Date: {}, Area: {}, Found: {}", publDate.toString(), ar.getId(), found);
                            for (Specialization spec : specializationList) {

                                // String area = MessageFormat.format(areaTmp, ar.getId().toString());
                                // String specialization = MessageFormat.format(specializationTmp, spec.getSubId());

                                searchParams.put("SPEC", spec.getSubId());
                                vacancyList = getVacancyList(publDate, searchParams);
                                vacancyListToAdd.addAll(vacancyList);
                                found = ((Double) vacancyList.get(0).get("found")).intValue();
                                logger.info("\t\t\t Date: {}, Specialization: {}, Area: {}, Found: {}", publDate.toString(), spec.getSubId(), ar.getId().toString(), found);
                            }
                            addSearchResult(vacancyList);
                        }

                        vacancyListToAdd.addAll(vacancyList);
                        logger.info("\t\t Date: {}, Area: {}, Found: {}", publDate.toString(), ar.getId().toString(), found);
                        addSearchResult(vacancyList);
                    }

                } else {
                    logger.info("Date: {}, Area: {}, Found: {}", publDate.toString(), areaId.toString(), found);

                    vacancyListToAdd.addAll(vacancyList);
                }

                addSearchResult(vacancyListToAdd);
                vacancyListToAdd.clear();
            }

            publDate = publDate.plusDays(1);
        }


        logger.info("Head Hunter Receiver Finished");
    }

    public void addSearchResult(ArrayList<Map<String, Object>> searchResults) {

        //logger.info("Savind {} vacancies", searchResults.size());

        try {

            searchResults.forEach((res) -> {

                ((ArrayList) res.get("items")).forEach(
                        elem -> {
                            ProcessingQueueItem processingQueueItem = new ProcessingQueueItem();
                            processingQueueItem.setVacancyId((String) ((LinkedTreeMap) elem).get("id"));
                            processingQueueItem.setName((String) ((LinkedTreeMap) elem).get("name"));

                            processingQueueItem.setSalaryFrom(((LinkedTreeMap) elem).get("salary") != null ?
                                    (Double) ((LinkedTreeMap) elem).get("from") : null);

                            processingQueueItem.setSalaryTo(((LinkedTreeMap) elem).get("salary") != null ?
                                    (Double) ((LinkedTreeMap) ((LinkedTreeMap) elem).get("salary")).get("to") : null);

                            processingQueueItem.setSalaryCurrency(((LinkedTreeMap) elem).get("salary") != null ?
                                    (String) ((LinkedTreeMap) ((LinkedTreeMap) elem).get("salary")).get("currency") : null);

                            processingQueueItem.setSalaryGross(((LinkedTreeMap) elem).get("salary") != null ?
                                    (Boolean) ((LinkedTreeMap) ((LinkedTreeMap) elem).get("salary")).get("gross") : null);

                            processingQueueItem.setAreaId(((LinkedTreeMap) elem).get("area") != null ?
                                    (String) ((LinkedTreeMap) ((LinkedTreeMap) elem).get("area")).get("id") : null);

                            processingQueueItem.setAreaName(((LinkedTreeMap) elem).get("area") != null ?
                                    (String) ((LinkedTreeMap) ((LinkedTreeMap) elem).get("area")).get("name") : null);

                            processingQueueItem.setAreaUrl(((LinkedTreeMap) elem).get("area") != null ?
                                    (String) ((LinkedTreeMap) ((LinkedTreeMap) elem).get("area")).get("url") : null);

                            processingQueueItem.setSnippetRequirement(((LinkedTreeMap) elem).get("snippet") != null ?
                                    (String) ((LinkedTreeMap) ((LinkedTreeMap) elem).get("snippet")).get("requirement") : null);

                            processingQueueItem.setSnippetResponsibility(((LinkedTreeMap) elem).get("snippet") != null ?
                                    (String) ((LinkedTreeMap) ((LinkedTreeMap) elem).get("snippet")).get("responsibility") : null);

                            processingQueueItem.setArchived((Boolean) ((LinkedTreeMap) elem).get("archived"));
                            processingQueueItem.setPremium((Boolean) ((LinkedTreeMap) elem).get("premium"));
                            processingQueueItem.setSource((String) ((LinkedTreeMap) elem).get("source"));
                            processingQueueItem.setUrl((String) ((LinkedTreeMap) elem).get("url"));
                            processingQueueItem.setAlternateUrl((String) ((LinkedTreeMap) elem).get("alternateUrl"));
                            processingQueueItem.setApplyAlternateUrl((String) ((LinkedTreeMap) elem).get("applyAlternateUrl"));

                            //TODO Add "street": "lat": , "lng"
                            processingQueueItem.setAddress(((LinkedTreeMap) elem).get("address") != null ?
                                    (String) ((LinkedTreeMap) ((LinkedTreeMap) elem).get("address")).get("id") : null);

                            processingQueueItem.setDepartmentId(((LinkedTreeMap) elem).get("department") != null ?
                                    (String) ((LinkedTreeMap) ((LinkedTreeMap) elem).get("department")).get("id") : null);

                            processingQueueItem.setDepartmentName(((LinkedTreeMap) elem).get("department") != null ?
                                    (String) ((LinkedTreeMap) ((LinkedTreeMap) elem).get("department")).get("name") : null);

                            //TODO
                            // processingQueueItem.setSortPointDistance((String) ((LinkedTreeMap) elem).get("sortPointDistance"));
                            processingQueueItem.setCreatedAt((LocalDateTime) ((LinkedTreeMap) elem).get("created_at"));
                            processingQueueItem.setPublishedAt((LocalDateTime) ((LinkedTreeMap) elem).get("published_at"));

                            processingQueueItem.setEmpId(((LinkedTreeMap) elem).get("employer") != null ?
                                    (String) ((LinkedTreeMap) ((LinkedTreeMap) elem).get("employer")).get("id") : null);

                            processingQueueItem.setEmpName(((LinkedTreeMap) elem).get("employer") != null ?
                                    (String) ((LinkedTreeMap) ((LinkedTreeMap) elem).get("employer")).get("name") : null);

                            processingQueueItem.setEmpUrl(((LinkedTreeMap) elem).get("employer") != null ?
                                    (String) ((LinkedTreeMap) ((LinkedTreeMap) elem).get("employer")).get("url") : null);

                            processingQueueItem.setEmpUrl(((LinkedTreeMap) elem).get("type") != null ?
                                    (String) ((LinkedTreeMap) ((LinkedTreeMap) elem).get("type")).get("id") : null);

                            producer.sendMessage(processingQueueItem);

                        });

            });

        } catch (Exception e) {
            logger.error(ExceptionUtils.getFullStackTrace(e));
        }
    }

    public ArrayList<Map<String, Object>> getVacancyList(LocalDate publDate, HashMap<String, Object> searchParams) {
        String reqString;
        String jsonString;
        Integer pageNum = 0;
        Integer perPage = 100;
        Integer totalPages = 0;
        Integer found = 0;
        Map<String, Object> resposeMap;
        RestTemplate restTemplate = new RestTemplate();
        ArrayList<Map<String, Object>> retArray = new ArrayList<Map<String, Object>>();
        String areaTmp = "area={0}&";
        String specializationTmp = "specialization={0}&";
        Integer areaId = (Integer) searchParams.get("AREA");
        String specId = (String) searchParams.get("SPEC");

        while (pageNum == 0 || totalPages > pageNum) {

            reqString = String.format(apiHost.concat("vacancies?&date_from=%s&date_to=%s&per_page=100&page=%s&area=%s%s"), publDate.toString(), publDate.toString(), pageNum, areaId, specId != null ? "&specialization=".concat(specId) : "");

            jsonString = restTemplate.getForObject(reqString, String.class);

            resposeMap = new Gson().fromJson(jsonString, new TypeToken<HashMap<String, Object>>() {
            }.getType());

            retArray.add(resposeMap);

            totalPages = ((Double) resposeMap.get("pages")).intValue();
            found = ((Double) resposeMap.get("found")).intValue();

            pageNum++;

            if (found > MAX_RESPOSE_SIZE) {
                break;
            }

        }
        return retArray;
    }

//    @Override
//    public void loadVacancyDetailes() {
//
//        RestTemplate restTemplate = new RestTemplate();
//
//        skillsMonsterService.getProcessingQueue()
//                //.stream()
//                .parallelStream()
//                .forEach(processingQueueItem -> {
//                    String vacId = processingQueueItem.getVacancyId();
//
//                    try {
//                        logger.debug("Vacancy ID: {} Created: {}", vacId, processingQueueItem.getCreatedAt());
//                        String reqString = apiHost.concat("vacancies/").concat(vacId);
//                        String jsonString = restTemplate.getForObject(reqString, String.class);
//                        Map<String, Object> retMap = new Gson().fromJson(jsonString, new TypeToken<HashMap<String, Object>>() {
//                        }.getType());
//                        Vacancy vacancy = new Vacancy();
//                        vacancy.setVacancyId(vacId);
//                        vacancy.setRawData(retMap);
//
//                        vacancy.setLoadDateTime(new Timestamp(System.currentTimeMillis()));
//                        skillsMonsterService.addVacancy(vacancy);
//                        processingQueueItem.setProcessedAt(new Timestamp(System.currentTimeMillis()));
//                        processingQueueItem.setStatus("LOADED");
//                        skillsMonsterService.updateProcessingQueue(processingQueueItem);
//
//                    } catch (HttpClientErrorException ex) {
//                        logger.error("Error loading info from hh.ru ID: {} Created: {}", vacId, processingQueueItem.getCreatedAt());
//                        processingQueueItem.setProcessedAt(new Timestamp(System.currentTimeMillis()));
//                        processingQueueItem.setStatus("ERROR");
//                        skillsMonsterService.updateProcessingQueue(processingQueueItem);
//
//                        logger.error(ExceptionUtils.getMessage(ex));
//                    }
//                });
//    }

    @Override
    public void loadVacancyDetailsMq(ProcessingQueueItem processingQueueItem) {


        String vacId = processingQueueItem.getVacancyId();

        try {
            logger.debug("Vacancy ID: {} Created: {}", vacId, processingQueueItem.getPublishedAt());
            String reqString = apiHost.concat("vacancies/").concat(vacId);
            String jsonString = restTemplate.getForObject(reqString, String.class);

            Map<String, Object> retMap = new Gson().fromJson(
                    jsonString,
                    new TypeToken<HashMap<String, Object>>() {
                    }.getType()
            );

            Vacancy vacancy = new Vacancy();
            vacancy.setVacancyId(vacId);
            vacancy.setRawData(retMap);
            vacancy.setAddress(processingQueueItem.getAddress());
            vacancy.setAlternateUrl(processingQueueItem.getAlternateUrl());
            vacancy.setApplyAlternateUrl(processingQueueItem.getApplyAlternateUrl());
            vacancy.setArchived(processingQueueItem.getArchived());


            vacancy.setName(processingQueueItem.getName());
            vacancy.setAreaUrl(processingQueueItem.getAreaUrl());
            vacancy.setAreaId(processingQueueItem.getAreaId());
            vacancy.setAreaName(processingQueueItem.getAreaName());
            vacancy.setSalaryFrom(processingQueueItem.getSalaryFrom());
            vacancy.setSalaryTo(processingQueueItem.getSalaryTo());
            vacancy.setSalaryGross(processingQueueItem.getSalaryGross());
            vacancy.setSalaryCurrency(processingQueueItem.getSalaryCurrency());
            vacancy.setSnippetRequirement(processingQueueItem.getSnippetRequirement());
            vacancy.setSnippetResponsibility(processingQueueItem.getSnippetResponsibility());
            vacancy.setArchived(processingQueueItem.getArchived());
            vacancy.setPremium(processingQueueItem.getPremium());
            vacancy.setSource(processingQueueItem.getSource());
            vacancy.setCreatedAt(processingQueueItem.getCreatedAt());
            vacancy.setPublishedAt(processingQueueItem.getPublishedAt());
            vacancy.setUrl(processingQueueItem.getUrl());
            vacancy.setAlternateUrl(processingQueueItem.getAlternateUrl());
            vacancy.setApplyAlternateUrl(processingQueueItem.getApplyAlternateUrl());
            vacancy.setAddress(processingQueueItem.getAddress());
            vacancy.setDepartmentId(processingQueueItem.getDepartmentId());
            vacancy.setDepartmentName(processingQueueItem.getDepartmentName());
            vacancy.setSortPointDistance(processingQueueItem.getSortPointDistance());
            vacancy.setEmpId(processingQueueItem.getEmpId());
            vacancy.setEmpName(processingQueueItem.getEmpName());
            vacancy.setEmpUrl(processingQueueItem.getEmpUrl());
            vacancy.setStatus(processingQueueItem.getStatus());
            vacancy.setTypeId(processingQueueItem.getTypeId());
            vacancy.setTypeName(processingQueueItem.getTypeName());

//            vacancy.setLoadDateTime(new Timestamp(System.currentTimeMillis()));
            skillsMonsterService.addVacancy(vacancy);

            System.out.println("");
        } catch
                (HttpClientErrorException
                        ex) {
            logger.error("Error loading info from hh.ru ID: {}", vacId);
            //    processingQueueItem.setProcessedAt(new Timestamp(System.currentTimeMillis()));
            processingQueueItem.setStatus("ERROR");
            //    skillsMonsterService.updateProcessingQueue(processingQueueItem);

            logger.error(ExceptionUtils.getMessage(ex));
        }


    }

//    @Override
//    @Transactional
//    public void updateProcessingQueue() {
//        //   System.out.println("update_queue");
//        StoredProcedureQuery sp = entityManager.createStoredProcedureQuery("public.add_vacancy_to_queue");
//        List resultList = sp.getResultList();
//
//
//        logger.info("added {} vacancies to the processing queue", resultList.get(0).toString());
//    }
}
