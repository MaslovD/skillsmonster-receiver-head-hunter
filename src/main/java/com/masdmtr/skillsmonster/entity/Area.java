package com.masdmtr.skillsmonster.entity;

import javax.annotation.Generated;
import javax.persistence.*;

/**
 * Created by dmaslov on 7/26/17.
 */
@Entity
public class Area {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private String code;
    private String extCode;
    private String name;
    private String type;
    private String zip;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Area area = (Area) o;

        if (id != area.id) return false;
        if (code != null ? !code.equals(area.code) : area.code != null) return false;
        if (extCode != null ? !extCode.equals(area.extCode) : area.extCode != null) return false;
        if (name != null ? !name.equals(area.name) : area.name != null) return false;
        if (type != null ? !type.equals(area.type) : area.type != null) return false;
        if (zip != null ? !zip.equals(area.zip) : area.zip != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (extCode != null ? extCode.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (zip != null ? zip.hashCode() : 0);
        return result;
    }
}
