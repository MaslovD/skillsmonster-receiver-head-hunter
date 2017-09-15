package com.masdmtr.skillsmonster.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Map;

/**
 * Created by dmaslov on 7/26/17.
 */
@Entity
public class Vacancy {
    private long id;
    private String vacancyId;
    private Map<String, Object> rawData;
    private Integer searchRequestId;
    private Timestamp loadDateTime;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "vacancy_id", nullable = false, length = 100)
    public String getVacancyId() {
        return vacancyId;
    }

    public void setVacancyId(String vacancyId) {
        this.vacancyId = vacancyId;
    }

    @Basic
    @Type(type = "JsonDataUserType")
    @Column(name = "raw_data", nullable = true)
    public Map<String, Object> getRawData() {
        return rawData;
    }

    public void setRawData(Map<String, Object> rawResponse) {
        this.rawData = rawResponse;
    }


    @Basic
    @Column(name = "search_request_id", nullable = true)
    public Integer getSearchRequestId() {
        return searchRequestId;
    }

    public void setSearchRequestId(Integer searchRequestId) {
        this.searchRequestId = searchRequestId;
    }


    @Basic
    @Column(name = "load_date_time")
    public Timestamp getLoadDateTime() {
        return loadDateTime;
    }

    public void setLoadDateTime(Timestamp loadDateTime) {
        this.loadDateTime = loadDateTime;
    }


}
