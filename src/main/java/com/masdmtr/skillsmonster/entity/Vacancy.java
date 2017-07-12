package com.masdmtr.skillsmonster.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by dmaslov on 13/07/17.
 */
@Entity
public class Vacancy {
    private Serializable rawData;
    private Integer searchRequestId;
    private String id;
    private SearchRequest searchRequestBySearchRequestId;

    @Basic
    @Column(name = "raw_data", nullable = true)
    public Serializable getRawData() {
        return rawData;
    }

    public void setRawData(Serializable rawData) {
        this.rawData = rawData;
    }

    @Basic
    @Column(name = "search_request_id", insertable = false, updatable = false, nullable = true)
    public Integer getSearchRequestId() {
        return searchRequestId;
    }

    public void setSearchRequestId(Integer searchRequestId) {
        this.searchRequestId = searchRequestId;
    }

    @Id
    @Column(name = "id", nullable = false, length = 100)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vacancy vacancy = (Vacancy) o;

        if (rawData != null ? !rawData.equals(vacancy.rawData) : vacancy.rawData != null) return false;
        if (searchRequestId != null ? !searchRequestId.equals(vacancy.searchRequestId) : vacancy.searchRequestId != null)
            return false;
        if (id != null ? !id.equals(vacancy.id) : vacancy.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = rawData != null ? rawData.hashCode() : 0;
        result = 31 * result + (searchRequestId != null ? searchRequestId.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "search_request_id", referencedColumnName = "id")
    public SearchRequest getSearchRequestBySearchRequestId() {
        return searchRequestBySearchRequestId;
    }

    public void setSearchRequestBySearchRequestId(SearchRequest searchRequestBySearchRequestId) {
        this.searchRequestBySearchRequestId = searchRequestBySearchRequestId;
    }
}
