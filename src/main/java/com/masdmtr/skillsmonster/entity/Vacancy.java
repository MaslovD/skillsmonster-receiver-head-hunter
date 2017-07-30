package com.masdmtr.skillsmonster.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by dmaslov on 7/26/17.
 */
@Entity
public class Vacancy {

    private String id;
    private Serializable rawData;
    private Integer searchRequestId;

    @Id
    @Column(name = "id", nullable = false, length = 100)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "raw_data", nullable = true)
    public Serializable getRawData() {
        return rawData;
    }

    public void setRawData(Serializable rawData) {
        this.rawData = rawData;
    }

    @Basic
    @Column(name = "search_request_id", nullable = true)
    public Integer getSearchRequestId() {
        return searchRequestId;
    }

    public void setSearchRequestId(Integer searchRequestId) {
        this.searchRequestId = searchRequestId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vacancy vacancy = (Vacancy) o;

        if (id != null ? !id.equals(vacancy.id) : vacancy.id != null) return false;
        if (rawData != null ? !rawData.equals(vacancy.rawData) : vacancy.rawData != null) return false;
        if (searchRequestId != null ? !searchRequestId.equals(vacancy.searchRequestId) : vacancy.searchRequestId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (rawData != null ? rawData.hashCode() : 0);
        result = 31 * result + (searchRequestId != null ? searchRequestId.hashCode() : 0);
        return result;
    }
}
