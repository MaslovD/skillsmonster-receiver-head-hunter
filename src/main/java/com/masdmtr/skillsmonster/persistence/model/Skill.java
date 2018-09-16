package com.masdmtr.skillsmonster.persistence.model;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Objects;

/**
 * Created by dmaslov on 7/26/17.
 */
@Entity
public class Skill {
    private Long id;
    private String skill_group;
    private String name;
    private Vacancy vacancy;
    private OffsetDateTime dateTime;

    public Skill(String keySkillName, Vacancy vacancy) {
        this.name = keySkillName;
        this.vacancy = vacancy;
        this.dateTime = vacancy.getCreatedAt();
    }

    public Skill() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "skill_group", nullable = true, length = 50)
    public String getSkill_group() {
        return skill_group;
    }

    public void setSkill_group(String code) {
        this.skill_group = code;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 200)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vacancy")
    public Vacancy getVacancy() {
        return vacancy;
    }

    public void setVacancy(Vacancy vacancy) {
        this.vacancy = vacancy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skill skill = (Skill) o;
        return Objects.equals(id, skill.id) &&
                Objects.equals(skill_group, skill.skill_group) &&
                Objects.equals(name, skill.name) &&
                Objects.equals(vacancy, skill.vacancy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, skill_group, name, vacancy);
    }

    public OffsetDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(OffsetDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
