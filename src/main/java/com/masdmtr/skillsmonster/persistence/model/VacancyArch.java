package com.masdmtr.skillsmonster.persistence.model;

import com.google.gson.internal.LinkedTreeMap;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "vacancy_arch", schema = "public", catalog = "skillsmonster")
public class VacancyArch {
    private Long id;
    //private Timestamp loadDateTime;
    private LinkedTreeMap rawData;
//    private String vacancyId;
//    private Long processingQueueId;
//    private String name;
//    private String areaUrl;
//    private String areaName;
//    private Double salaryFrom;
//    private Double salaryTo;
//    private Boolean salaryGross;
//    private String salaryCurrency;
//    private String snippetRequirement;
//    private String snippetResponsibility;
//    private Boolean archived;
//    private Boolean premium;
//    private String source;
//    private Timestamp createdAt;
//    private String url;
//    private String alternateUrl;
//    private String applyAlternateUrl;
//    private String address;
//    private String departmentId;
//    private String departmentName;
//    private String sortPointDistance;
//    private String empId;
//    private String empName;
//    private String empUrl;
//    private String status;
//    private String typeId;
//    private String typeName;
//    private String areaId;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    @Basic
//    @Column(name = "load_date_time", nullable = true)
//    public Timestamp getLoadDateTime() {
//        return loadDateTime;
//    }
//
//    public void setLoadDateTime(Timestamp loadDateTime) {
//        this.loadDateTime = loadDateTime;
//    }

    @Basic
    @Type(type = "JsonDataUserType")
    @Column(name = "raw_data", nullable = true)
    public LinkedTreeMap getRawData() {
        return rawData;
    }

    @Type(type = "JsonDataUserType")
    public void setRawData(LinkedTreeMap rawData) {
        this.rawData = rawData;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VacancyArch that = (VacancyArch) o;
        return Objects.equals(id, that.id) &&
//                Objects.equals(loadDateTime, that.loadDateTime) &&
                Objects.equals(rawData, that.rawData); // &&
//                Objects.equals(vacancyId, that.vacancyId) &&
//                Objects.equals(processingQueueId, that.processingQueueId) &&
//                Objects.equals(name, that.name) &&
//                Objects.equals(areaUrl, that.areaUrl) &&
//                Objects.equals(areaName, that.areaName) &&
//                Objects.equals(salaryFrom, that.salaryFrom) &&
//                Objects.equals(salaryTo, that.salaryTo) &&
//                Objects.equals(salaryGross, that.salaryGross) &&
//                Objects.equals(salaryCurrency, that.salaryCurrency) &&
//                Objects.equals(snippetRequirement, that.snippetRequirement) &&
//                Objects.equals(snippetResponsibility, that.snippetResponsibility) &&
//                Objects.equals(archived, that.archived) &&
//                Objects.equals(premium, that.premium) &&
//                Objects.equals(source, that.source) &&
//                Objects.equals(createdAt, that.createdAt) &&
//                Objects.equals(url, that.url) &&
//                Objects.equals(alternateUrl, that.alternateUrl) &&
//                Objects.equals(applyAlternateUrl, that.applyAlternateUrl) &&
//                Objects.equals(address, that.address) &&
//                Objects.equals(departmentId, that.departmentId) &&
//                Objects.equals(departmentName, that.departmentName) &&
//                Objects.equals(sortPointDistance, that.sortPointDistance) &&
//                Objects.equals(empId, that.empId) &&
//                Objects.equals(empName, that.empName) &&
//                Objects.equals(empUrl, that.empUrl) &&
//                Objects.equals(status, that.status) &&
//                Objects.equals(typeId, that.typeId) &&
//                Objects.equals(typeName, that.typeName) &&
//                Objects.equals(areaId, that.areaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, /*loadDateTime,*/ rawData/*, vacancyId, processingQueueId, name, areaUrl, areaName, salaryFrom, salaryTo, salaryGross, salaryCurrency, snippetRequirement, snippetResponsibility, archived, premium, source, createdAt, url, alternateUrl, applyAlternateUrl, address, departmentId, departmentName, sortPointDistance, empId, empName, empUrl, status, typeId, typeName, areaId*/);
    }
}
