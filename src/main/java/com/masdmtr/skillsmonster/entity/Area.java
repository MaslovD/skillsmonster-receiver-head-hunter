package com.masdmtr.skillsmonster.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by dmaslov on 11/07/17.
 */
@Entity
public class Area {
    private Integer id;
    private String country;
    private String name;
    private String type;
    private String code;
    private String zip;
    private String extCode;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "country", nullable = true, length = 3)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 500)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "type", nullable = true, length = 20)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "code", nullable = true, length = 100)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "zip", nullable = true, length = 100)
    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Basic
    @Column(name = "ext_code", nullable = true, length = 100)
    public String getExtCode() {
        return extCode;
    }

    public void setExtCode(String extCode) {
        this.extCode = extCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Area area = (Area) o;

        if (id != null ? !id.equals(area.id) : area.id != null) return false;
        if (country != null ? !country.equals(area.country) : area.country != null) return false;
        if (name != null ? !name.equals(area.name) : area.name != null) return false;
        if (type != null ? !type.equals(area.type) : area.type != null) return false;
        if (code != null ? !code.equals(area.code) : area.code != null) return false;
        if (zip != null ? !zip.equals(area.zip) : area.zip != null) return false;
        if (extCode != null ? !extCode.equals(area.extCode) : area.extCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (zip != null ? zip.hashCode() : 0);
        result = 31 * result + (extCode != null ? extCode.hashCode() : 0);
        return result;
    }
}
