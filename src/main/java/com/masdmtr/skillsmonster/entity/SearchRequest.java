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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SearchRequest that = (SearchRequest) o;

        if (id != that.id) return false;
        if (dateTime != null ? !dateTime.equals(that.dateTime) : that.dateTime != null) return false;
        if (found != null ? !found.equals(that.found) : that.found != null) return false;
        if (industryId != null ? !industryId.equals(that.industryId) : that.industryId != null) return false;
        if (pages != null ? !pages.equals(that.pages) : that.pages != null) return false;
        if (perPage != null ? !perPage.equals(that.perPage) : that.perPage != null) return false;
        if (periodFrom != null ? !periodFrom.equals(that.periodFrom) : that.periodFrom != null) return false;
        if (periodTo != null ? !periodTo.equals(that.periodTo) : that.periodTo != null) return false;
        if (rawRequest != null ? !rawRequest.equals(that.rawRequest) : that.rawRequest != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
        result = 31 * result + (found != null ? found.hashCode() : 0);
        result = 31 * result + (industryId != null ? industryId.hashCode() : 0);
        result = 31 * result + (pages != null ? pages.hashCode() : 0);
        result = 31 * result + (perPage != null ? perPage.hashCode() : 0);
        result = 31 * result + (periodFrom != null ? periodFrom.hashCode() : 0);
        result = 31 * result + (periodTo != null ? periodTo.hashCode() : 0);
        result = 31 * result + (rawRequest != null ? rawRequest.hashCode() : 0);
        return result;
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
}
