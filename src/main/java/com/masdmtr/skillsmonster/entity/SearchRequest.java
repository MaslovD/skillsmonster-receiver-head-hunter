package com.masdmtr.skillsmonster.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by dmaslov on 7/26/17.
 */
@Entity
@Table(name = "search_request", schema = "public", catalog = "skillsmonster")
public class SearchRequest {


    private int id;
    private Timestamp dateTime;
    private Long found;
    private String industryId;
    private Long pages;
    private Integer perPage;
    private Timestamp periodFrom;
    private Timestamp periodTo;
    private String rawRequest;
    private Industry industryByIndustryId;
    private Area areaByAreaId;
    private Specialization specializationId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "date_time", nullable = true)
    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
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
    @Column(name = "pages", nullable = true)
    public Long getPages() {
        return pages;
    }

    public void setPages(Long pages) {
        this.pages = pages;
    }

    @Basic
    @Column(name = "per_page", nullable = true)
    public Integer getPerPage() {
        return perPage;
    }

    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }

    @Basic
    @Column(name = "period_from", nullable = true)
    public Timestamp getPeriodFrom() {
        return periodFrom;
    }

    public void setPeriodFrom(Timestamp periodFrom) {
        this.periodFrom = periodFrom;
    }

    @Basic
    @Column(name = "period_to", nullable = true)
    public Timestamp getPeriodTo() {
        return periodTo;
    }

    public void setPeriodTo(Timestamp periodTo) {
        this.periodTo = periodTo;
    }

    @Basic
    @Column(name = "raw_request", nullable = true, length = 500)
    public String getRawRequest() {
        return rawRequest;
    }

    public void setRawRequest(String rawRequest) {
        this.rawRequest = rawRequest;
    }


    @ManyToOne
    @JoinColumn(name = "industry_id", referencedColumnName = "code")
    public Industry getIndustryByIndustryId() {
        return industryByIndustryId;
    }

    public void setIndustryByIndustryId(Industry industryByIndustryId) {
        this.industryByIndustryId = industryByIndustryId;
    }

    @ManyToOne
    @JoinColumn(name = "area_id", referencedColumnName = "id")
    public Area getAreaByAreaId() {
        return areaByAreaId;
    }

    public void setAreaByAreaId(Area areaByAreaId) {
        this.areaByAreaId = areaByAreaId;
    }

    @ManyToOne
    @JoinColumn(name = "specialization_id", referencedColumnName = "sub_id")

    public Specialization getSpecializationId() {
        return specializationId;
    }
    public void setSpecializationId(Specialization specializationId) {
        this.specializationId = specializationId;
    }
}
