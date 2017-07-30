package com.masdmtr.skillsmonster.entity;

import com.masdmtr.skillsmonster.json.JsonDataUserType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Map;

/**
 * Created by dmaslov on 7/26/17.
 */

@Entity
@Table(name = "search_result", schema = "public", catalog = "skillsmonster")
@TypeDef(name = "JsonDataUserType", typeClass = JsonDataUserType.class)
public class SearchResult {
    private int id;
    private Integer page;
    private Map<String, String> rawResponse;
    private Integer searchRequestId;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "page", nullable = true)
    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    @Basic
    @Type(type = "JsonDataUserType")
    @Column(name = "raw_response", nullable = true)
    public Map<String, String> getRawResponse() {
        return rawResponse;
    }

    public void setRawResponse(Map<String, String> rawResponse) {
        this.rawResponse = rawResponse;
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

        SearchResult that = (SearchResult) o;

        if (id != that.id) return false;
        if (page != null ? !page.equals(that.page) : that.page != null) return false;
        if (rawResponse != null ? !rawResponse.equals(that.rawResponse) : that.rawResponse != null) return false;
        if (searchRequestId != null ? !searchRequestId.equals(that.searchRequestId) : that.searchRequestId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (page != null ? page.hashCode() : 0);
        result = 31 * result + (rawResponse != null ? rawResponse.hashCode() : 0);
        result = 31 * result + (searchRequestId != null ? searchRequestId.hashCode() : 0);
        return result;
    }
}
