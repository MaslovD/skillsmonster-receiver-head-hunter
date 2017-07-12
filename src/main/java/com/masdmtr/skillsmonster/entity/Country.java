package com.masdmtr.skillsmonster.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by dmaslov on 13/07/17.
 */
@Entity
public class Country {
    private String alpha2;
    private String shortName;
    private String fullName;
    private String alpha3;
    private Integer numCode;
    private Collection<Area> areasByAlpha3;

    @Basic
    @Column(name = "alpha_2", nullable = true, length = 2)
    public String getAlpha2() {
        return alpha2;
    }

    public void setAlpha2(String alpha2) {
        this.alpha2 = alpha2;
    }

    @Basic
    @Column(name = "short_name", nullable = true, length = 200)
    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    @Basic
    @Column(name = "full_name", nullable = true, length = 500)
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Id
    @Column(name = "alpha_3", nullable = false, length = 3)
    public String getAlpha3() {
        return alpha3;
    }

    public void setAlpha3(String alpha3) {
        this.alpha3 = alpha3;
    }

    @Basic
    @Column(name = "num_code", nullable = true)
    public Integer getNumCode() {
        return numCode;
    }

    public void setNumCode(Integer numCode) {
        this.numCode = numCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Country country = (Country) o;

        if (alpha2 != null ? !alpha2.equals(country.alpha2) : country.alpha2 != null) return false;
        if (shortName != null ? !shortName.equals(country.shortName) : country.shortName != null) return false;
        if (fullName != null ? !fullName.equals(country.fullName) : country.fullName != null) return false;
        if (alpha3 != null ? !alpha3.equals(country.alpha3) : country.alpha3 != null) return false;
        if (numCode != null ? !numCode.equals(country.numCode) : country.numCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = alpha2 != null ? alpha2.hashCode() : 0;
        result = 31 * result + (shortName != null ? shortName.hashCode() : 0);
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        result = 31 * result + (alpha3 != null ? alpha3.hashCode() : 0);
        result = 31 * result + (numCode != null ? numCode.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "countryByCountryCode")
    public Collection<Area> getAreasByAlpha3() {
        return areasByAlpha3;
    }

    public void setAreasByAlpha3(Collection<Area> areasByAlpha3) {
        this.areasByAlpha3 = areasByAlpha3;
    }
}
