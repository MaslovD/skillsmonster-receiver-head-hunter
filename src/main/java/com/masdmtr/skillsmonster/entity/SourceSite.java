package com.masdmtr.skillsmonster.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by dmaslov on 13/07/17.
 */
@Entity
@Table(name = "source_site", schema = "public", catalog = "skillsmonster")
public class SourceSite {
    private Integer id;
    private String name;
    private String url;
    private String apiUrl;
    private Collection<SearchRequest> searchRequestsById;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "url", nullable = true, length = 500)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "api_url", nullable = true, length = 500)
    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SourceSite that = (SourceSite) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        if (apiUrl != null ? !apiUrl.equals(that.apiUrl) : that.apiUrl != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (apiUrl != null ? apiUrl.hashCode() : 0);
        return result;
    }

 /*   @OneToMany(mappedBy = "sourceSiteBySourceSiteId")
    public Collection<SearchRequest> getSearchRequestsById() {
        return searchRequestsById;
    }

    public void setSearchRequestsById(Collection<SearchRequest> searchRequestsById) {
        this.searchRequestsById = searchRequestsById;
    }*/
}
