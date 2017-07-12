package com.masdmtr.skillsmonster.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by dmaslov on 13/07/17.
 */
@Entity
@Table(name = "search_request", schema = "public", catalog = "skillsmonster")
public class SearchRequest {
    private Integer id;
    private Timestamp periodFrom;
    private String industryId;
    private Integer sourceSiteId;
    private Integer areaId;
    private Integer perPage;
    private Integer found;
    private String rawRequest;
    private Timestamp periodTo;
    private Timestamp dateTime;
    private Integer pages;
    private SourceSite sourceSiteBySourceSiteId;
    private Area areaByAreaId;
    private Collection<SearchResult> searchResultsById;
    private Collection<Vacancy> vacanciesById;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
    @Column(name = "industry_id", nullable = true, length = 100)
    public String getIndustryId() {
        return industryId;
    }

    public void setIndustryId(String industryId) {
        this.industryId = industryId;
    }

    @Basic
    @Column(name = "source_site_id", nullable = true, insertable = false, updatable = false)
    public Integer getSourceSiteId() {
        return sourceSiteId;
    }

    public void setSourceSiteId(Integer sourceSiteId) {
        this.sourceSiteId = sourceSiteId;
    }

    @Basic
    @Column(name = "area_id", nullable = true, insertable = false, updatable = false)
    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
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
    @Column(name = "found", nullable = true)
    public Integer getFound() {
        return found;
    }

    public void setFound(Integer found) {
        this.found = found;
    }

    @Basic
    @Column(name = "raw_request", nullable = true, length = 500)
    public String getRawRequest() {
        return rawRequest;
    }

    public void setRawRequest(String rawRequest) {
        this.rawRequest = rawRequest;
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
    @Column(name = "date_time", nullable = true)
    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    @Basic
    @Column(name = "pages", nullable = true)
    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SearchRequest that = (SearchRequest) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (periodFrom != null ? !periodFrom.equals(that.periodFrom) : that.periodFrom != null) return false;
        if (industryId != null ? !industryId.equals(that.industryId) : that.industryId != null) return false;
        if (sourceSiteId != null ? !sourceSiteId.equals(that.sourceSiteId) : that.sourceSiteId != null) return false;
        if (areaId != null ? !areaId.equals(that.areaId) : that.areaId != null) return false;
        if (perPage != null ? !perPage.equals(that.perPage) : that.perPage != null) return false;
        if (found != null ? !found.equals(that.found) : that.found != null) return false;
        if (rawRequest != null ? !rawRequest.equals(that.rawRequest) : that.rawRequest != null) return false;
        if (periodTo != null ? !periodTo.equals(that.periodTo) : that.periodTo != null) return false;
        if (dateTime != null ? !dateTime.equals(that.dateTime) : that.dateTime != null) return false;
        if (pages != null ? !pages.equals(that.pages) : that.pages != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (periodFrom != null ? periodFrom.hashCode() : 0);
        result = 31 * result + (industryId != null ? industryId.hashCode() : 0);
        result = 31 * result + (sourceSiteId != null ? sourceSiteId.hashCode() : 0);
        result = 31 * result + (areaId != null ? areaId.hashCode() : 0);
        result = 31 * result + (perPage != null ? perPage.hashCode() : 0);
        result = 31 * result + (found != null ? found.hashCode() : 0);
        result = 31 * result + (rawRequest != null ? rawRequest.hashCode() : 0);
        result = 31 * result + (periodTo != null ? periodTo.hashCode() : 0);
        result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
        result = 31 * result + (pages != null ? pages.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "source_site_id", referencedColumnName = "id")
    public SourceSite getSourceSiteBySourceSiteId() {
        return sourceSiteBySourceSiteId;
    }

    public void setSourceSiteBySourceSiteId(SourceSite sourceSiteBySourceSiteId) {
        this.sourceSiteBySourceSiteId = sourceSiteBySourceSiteId;
    }

    @ManyToOne
    @JoinColumn(name = "area_id", referencedColumnName = "id")
    public Area getAreaByAreaId() {
        return areaByAreaId;
    }

    public void setAreaByAreaId(Area areaByAreaId) {
        this.areaByAreaId = areaByAreaId;
    }

    @OneToMany(mappedBy = "searchRequestBySearchReauestId")
    public Collection<SearchResult> getSearchResultsById() {
        return searchResultsById;
    }

    public void setSearchResultsById(Collection<SearchResult> searchResultsById) {
        this.searchResultsById = searchResultsById;
    }

    @OneToMany(mappedBy = "searchRequestBySearchRequestId")
    public Collection<Vacancy> getVacanciesById() {
        return vacanciesById;
    }

    public void setVacanciesById(Collection<Vacancy> vacanciesById) {
        this.vacanciesById = vacanciesById;
    }
}
