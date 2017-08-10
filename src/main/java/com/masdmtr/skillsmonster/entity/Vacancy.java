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

    private String id;
    private Map<String, Object> rawData;
    private Integer searchRequestId;
    private Timestamp loadDateTime;

    @Id
    @Column(name = "id", nullable = false, length = 100)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
