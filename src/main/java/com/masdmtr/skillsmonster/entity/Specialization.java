package com.masdmtr.skillsmonster.entity;

import org.hibernate.annotations.Generated;

import javax.persistence.*;

/**
 * Created by dmaslov on 9/10/17.
 */
@Entity

public class Specialization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "main_id", nullable = true, length = 255)
    private String main_id;

    @Column(name = "main_name", nullable = true, length = 255)
    private String name;

    @Column(name = "sub_id", nullable = true, length = 255)
    private String subId;

    @Column(name = "sub_name", nullable = true, length = 255)
    private String subName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubId() {
        return subId;
    }

    public void setSubId(String subId) {
        this.subId = subId;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }
}
