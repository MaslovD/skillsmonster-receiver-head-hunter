package com.masdmtr.skillsmonster.persistence.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Map;

/**
 * Created by dmaslov on 7/26/17.
 */
@Entity
public class Vacancy {
    private long id;
    private OffsetDateTime loadDateTime;
    private Map<String, Object> rawData;
    private String vacancyId;
    private String name;
    private String areaUrl;
    private String areaId;
    private String areaName;
    private Double salaryFrom;
    private Double salaryTo;
    private Boolean salaryGross;
    private String salaryCurrency;
    private String snippetRequirement;
    private String snippetResponsibility;
    private Boolean archived;
    private Boolean premium;
    private String source;
    private LocalDateTime createdAt;
    private LocalDateTime publishedAt;
    private String url;
    private String alternateUrl;
    private String applyAlternateUrl;
    private String address;
    private String departmentId;
    private String departmentName;
    private String sortPointDistance;
    private String empId;
    private String empName;
    private String empUrl;
    private String status;
    private String typeId;
    private String typeName;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "vacancy_id", nullable = false, length = 100)
    public String getVacancyId() {
        return vacancyId;
    }

    public void setVacancyId(String vacancyId) {
        this.vacancyId = vacancyId;
    }

    @Basic
    @Type(type = "JsonDataUserType")
    @Column(name = "raw_data", nullable = true)
    public Map<String, Object> getRawData() {
        return rawData;
    }

    public void setRawData(Map<String, Object> rawResponse) {
        this.rawData = rawResponse;
    }

    @Basic
    @Column(name = "load_date_time", columnDefinition = "DATE")
    public OffsetDateTime getLoadDateTime() {
        return loadDateTime;
    }

    public void setLoadDateTime(OffsetDateTime loadDateTime) {
        this.loadDateTime = loadDateTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAreaUrl(String areaUrl) {
        this.areaUrl = areaUrl;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public void setSalaryFrom(Double salaryFrom) {
        this.salaryFrom = salaryFrom;
    }

    public void setSalaryTo(Double salaryTo) {
        this.salaryTo = salaryTo;
    }

    public void setSalaryGross(Boolean salaryGross) {
        this.salaryGross = salaryGross;
    }

    public void setSalaryCurrency(String salaryCurrency) {
        this.salaryCurrency = salaryCurrency;
    }

    public void setSnippetRequirement(String snippetRequirement) {
        this.snippetRequirement = snippetRequirement;
    }

    public void setSnippetResponsibility(String snippetResponsibility) {
        this.snippetResponsibility = snippetResponsibility;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public void setPremium(Boolean premium) {
        this.premium = premium;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setPublishedAt(LocalDateTime publishedAt) {
        this.publishedAt = publishedAt;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setAlternateUrl(String alternateUrl) {
        this.alternateUrl = alternateUrl;
    }

    public void setApplyAlternateUrl(String applyAlternateUrl) {
        this.applyAlternateUrl = applyAlternateUrl;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void setSortPointDistance(String sortPointDistance) {
        this.sortPointDistance = sortPointDistance;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public void setEmpUrl(String empUrl) {
        this.empUrl = empUrl;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getName() {
        return name;
    }

    public String getAreaUrl() {
        return areaUrl;
    }

    public String getAreaId() {
        return areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public Double getSalaryFrom() {
        return salaryFrom;
    }

    public Double getSalaryTo() {
        return salaryTo;
    }

    public Boolean getSalaryGross() {
        return salaryGross;
    }

    public String getSalaryCurrency() {
        return salaryCurrency;
    }

    public String getSnippetRequirement() {
        return snippetRequirement;
    }

    public String getSnippetResponsibility() {
        return snippetResponsibility;
    }

    public Boolean getArchived() {
        return archived;
    }

    public Boolean getPremium() {
        return premium;
    }

    public String getSource() {
        return source;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }

    public String getUrl() {
        return url;
    }

    public String getAlternateUrl() {
        return alternateUrl;
    }

    public String getApplyAlternateUrl() {
        return applyAlternateUrl;
    }

    public String getAddress() {
        return address;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public String getSortPointDistance() {
        return sortPointDistance;
    }

    public String getEmpId() {
        return empId;
    }

    public String getEmpName() {
        return empName;
    }

    public String getEmpUrl() {
        return empUrl;
    }

    public String getStatus() {
        return status;
    }

    public String getTypeId() {
        return typeId;
    }

    public String getTypeName() {
        return typeName;
    }
}
