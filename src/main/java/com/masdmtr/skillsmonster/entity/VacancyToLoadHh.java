package com.masdmtr.skillsmonster.entity;

import jdk.nashorn.internal.ir.annotations.Immutable;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by dmaslov on 8/5/17.
 */
@Entity
@Immutable
public class VacancyToLoadHh {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "emp_name")
    private String empName;

    @Column(name = "emp_city")
    private String empCity;

    @Column(name = "salary_to")
    private String salaryTo;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public String getEmpName() {
        return empName;
    }

    public String getEmpCity() {
        return empCity;
    }

    public String getSalaryTo() {
        return salaryTo;
    }
}
