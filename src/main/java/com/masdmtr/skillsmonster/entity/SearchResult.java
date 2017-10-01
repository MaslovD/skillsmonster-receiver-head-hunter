package com.masdmtr.skillsmonster.entity;

import com.masdmtr.skillsmonster.entity.analytics.ThreeColumnRanking;
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
    private Map<String, Object> rawResponse;
    private SearchRequest searchRequest;
    private String status;
    private Long found;

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
    @Column(name = "page", nullable = true)
    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    @Basic
    @Column(name = "status", nullable = true)
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "found", nullable = true)
    public long getFound() {return found;}
    public void setFound(Long found) {this.found = found;}

    @Basic
    @Type(type = "JsonDataUserType")
    @Column(name = "raw_response", nullable = true)
    public Map<String, Object> getRawResponse() {
        return rawResponse;
    }

    public void setRawResponse(Map<String, Object> rawResponse) {
        this.rawResponse = rawResponse;
    }


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "search_request_id")
    public SearchRequest getSearchRequest() {
        return searchRequest;
    }

    public void setSearchRequest(SearchRequest searchRequest) {
        this.searchRequest = searchRequest;
    }


}
