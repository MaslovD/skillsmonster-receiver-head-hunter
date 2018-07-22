package com.masdmtr.skillsmonster.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Map;

/**
 * Created by dmaslov on 7/26/17.
 */
@Entity
public class Vacancy {
    private long id;
    private OffsetDateTime loadDateTime;
    private Map<String, Object> rawData;
    private String vacancyId;
    private String name;
    private String areaUrl;
    private String areaId;
    private String areaName;
    private Double salaryFrom;
    private Double salaryTo;
    private Boolean salaryGross;
    private String salaryCurrency;
    private String snippetRequirement;
    private String snippetResponsibility;
    private Boolean archived;
    private Boolean premium;
    private String source;
    private String createdAt;
    private String publishedAt;
    private String url;
    private String alternateUrl;
    private String applyAlternateUrl;
    private String address;
    private String departmentId;
    private String departmentName;
    private String sortPointDistance;
    private String empId;
    private String empName;
    private String empUrl;
    private String status;
    private String typeId;
    private String typeName;

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
    @Column(name = "load_date_time", columnDefinition = "DATE")
    public OffsetDateTime getLoadDateTime() {
        return loadDateTime;
    }

    public void setLoadDateTime(OffsetDateTime loadDateTime) {
        this.loadDateTime = loadDateTime;
    }

}
