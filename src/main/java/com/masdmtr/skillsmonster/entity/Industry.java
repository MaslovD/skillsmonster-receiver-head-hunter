package com.masdmtr.skillsmonster.entity;

import javax.persistence.*;

/**
 * Created by dmaslov on 13/07/17.
 */
@Entity
@Table(name="industry")

public class Industry {
    private Integer id;
    private String code;
    private SearchRequest searchRequestByCode;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "code", nullable = false,  insertable = false, updatable = false, length = 100)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Industry industry = (Industry) o;

        if (id != null ? !id.equals(industry.id) : industry.id != null) return false;
        if (code != null ? !code.equals(industry.code) : industry.code != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "code", referencedColumnName = "industry_id", nullable = false)
    public SearchRequest getSearchRequestByCode() {
        return searchRequestByCode;
    }

    public void setSearchRequestByCode(SearchRequest searchRequestByCode) {
        this.searchRequestByCode = searchRequestByCode;
    }
}
