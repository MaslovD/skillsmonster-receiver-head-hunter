package com.masdmtr.skillsmonster.dto;

import java.util.Objects;

public class SearchRequestDto {
    private String vacancyId;
    private String name;
    private String industryId;
    private String industrySubId;
    private String specializationId;
    private String specializationSubId;

    public SearchRequestDto(Long vacancyId) {
        this.vacancyId = vacancyId.toString();
    }

    public SearchRequestDto() {

    }

    public String getVacancyId() {
        return vacancyId;
    }

    public void setVacancyId(String vacancyId) {
        this.vacancyId = vacancyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndustryId() {
        return industryId;
    }

    public void setIndustryId(String industryId) {
        this.industryId = industryId;
    }

    public String getIndustrySubId() {
        return industrySubId;
    }

    public void setIndustrySubId(String industrySubId) {
        this.industrySubId = industrySubId;
    }

    public String getSpecializationId() {
        return specializationId;
    }

    public void setSpecializationId(String specializationId) {
        this.specializationId = specializationId;
    }

    public String getSpecializationSubId() {
        return specializationSubId;
    }

    public void setSpecializationSubId(String specializationSubId) {
        this.specializationSubId = specializationSubId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchRequestDto that = (SearchRequestDto) o;
        return Objects.equals(vacancyId, that.vacancyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vacancyId);
    }
}
