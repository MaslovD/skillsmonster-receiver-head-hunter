package com.masdmtr.skillsmonster.entity;

import com.masdmtr.skillsmonster.json.JsonDataUserType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.Map;

/**
 * Created by dmaslov on 13/07/17.
 */

@Entity
@Table(name = "search_result", schema = "public", catalog = "skillsmonster")
@TypeDef(name = "JsonDataUserType", typeClass = JsonDataUserType.class)

public class SearchResult {
    private Integer id;
    private Integer searchReauestId;
    private Map<String, String> rawResponse;
    private Integer page;
    // private SearchRequest searchRequestBySearchReauestId;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "search_request_id", insertable = false, updatable = false, nullable = true)
    public Integer getSearchReauestId() {
        return searchReauestId;
    }

    public void setSearchReauestId(Integer searchReauestId) {
        this.searchReauestId = searchReauestId;
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
    @Column(name = "page", nullable = true)
    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SearchResult that = (SearchResult) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (searchReauestId != null ? !searchReauestId.equals(that.searchReauestId) : that.searchReauestId != null)
            return false;
        if (rawResponse != null ? !rawResponse.equals(that.rawResponse) : that.rawResponse != null) return false;
        if (page != null ? !page.equals(that.page) : that.page != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (searchReauestId != null ? searchReauestId.hashCode() : 0);
        result = 31 * result + (rawResponse != null ? rawResponse.hashCode() : 0);
        result = 31 * result + (page != null ? page.hashCode() : 0);
        return result;
    }

   /* @ManyToOne
    @JoinColumn(name = "search_request_id", referencedColumnName = "id")
    public SearchRequest getSearchRequestBySearchReauestId() {
        return searchRequestBySearchReauestId;
    }

    public void setSearchRequestBySearchReauestId(SearchRequest searchRequestBySearchReauestId) {
        this.searchRequestBySearchReauestId = searchRequestBySearchReauestId;
    }*/
}
