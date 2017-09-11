package com.masdmtr.skillsmonster.entity;

import javax.annotation.Generated;
import javax.persistence.*;

/**
 * Created by dmaslov on 7/26/17.
 */
@Entity
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String code;
    private String extCode;
    private String name;
    private String type;
    private String zip;
    private String countryId;
    private String countryName;
    private String areaName;


    @Column(name = "code", nullable = true, length = 100)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "ext_code", nullable = true, length = 100)
    public String getExtCode() {
        return extCode;
    }

    public void setExtCode(String extCode) {
        this.extCode = extCode;
    }

    @Column(name = "name", nullable = true, length = 500)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "type", nullable = true, length = 20)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "zip", nullable = true, length = 100)
    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}
