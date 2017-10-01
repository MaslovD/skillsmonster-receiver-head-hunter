package com.masdmtr.skillsmonster.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Map;
import java.util.Objects;

/**
 * Created by dmaslov on 10/1/17.
 */
@Entity
@Table(name = "processing_queue", schema = "public", catalog = "skillsmonster")
public class ProcessingQueue {
    private long id;
    private String status;
    private Timestamp queuedAt;
    private Timestamp processedAt;
    private Integer resultId;
    private Long found;
    private String vacancyId;
    private String name;
    private Timestamp createdAt;
    private String empName;
    private String empCity;
    private String areaId;
    private String areaName;
    //private Map<String, Object> salaryTo;
    private String source;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "status", nullable = true, length = 20)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "queued_at", nullable = true)
    public Timestamp getQueuedAt() {
        return queuedAt;
    }

    public void setQueuedAt(Timestamp queuedAt) {
        this.queuedAt = queuedAt;
    }

    @Basic
    @Column(name = "processed_at", nullable = true)
    public Timestamp getProcessedAt() {
        return processedAt;
    }

    public void setProcessedAt(Timestamp processedAt) {
        this.processedAt = processedAt;
    }

    @Basic
    @Column(name = "result_id", nullable = true)
    public Integer getResultId() {
        return resultId;
    }

    public void setResultId(Integer resultId) {
        this.resultId = resultId;
    }

    @Basic
    @Column(name = "found", nullable = true)
    public Long getFound() {
        return found;
    }

    public void setFound(Long found) {
        this.found = found;
    }

    @Basic
    @Column(name = "vacancy_id", nullable = true, length = -1)
    public String getVacancyId() {
        return vacancyId;
    }

    public void setVacancyId(String vacancyId) {
        this.vacancyId = vacancyId;
    }

    @Basic
    @Column(name = "name", nullable = true, length = -1)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "created_at", nullable = true)
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Basic
    @Column(name = "emp_name", nullable = true, length = -1)
    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    @Basic
    @Column(name = "emp_city", nullable = true, length = -1)
    public String getEmpCity() {
        return empCity;
    }

    public void setEmpCity(String empCity) {
        this.empCity = empCity;
    }

    @Basic
    @Column(name = "area_id", nullable = true, length = -1)
    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    @Basic
    @Column(name = "area_name", nullable = true, length = -1)
    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

//    @Basic
//    @Type(type = "JsonDataUserType")
//    @Column(name = "salary_to", nullable = true)
//    public Map<String, Object>  getSalaryTo() {
//        return salaryTo;
//    }
//
//    public void setSalaryTo(Map<String, Object>  salaryTo) {
//        this.salaryTo = salaryTo;
//    }

    @Basic
    @Column(name = "source", nullable = true, length = 20)
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

}
