package com.masdmtr.skillsmonster.persistence.model;

import javax.persistence.*;

/**
 * Created by dmaslov on 7/26/17.
 */
@Entity
public class Skill {
    private int id;
    private String code;
    private Integer collectionId;
    private Boolean keySkill;
    private String name;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "code", nullable = true, length = 50)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "collection_id", nullable = true)
    public Integer getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Integer collectionId) {
        this.collectionId = collectionId;
    }

    @Basic
    @Column(name = "key_skill", nullable = true)
    public Boolean getKeySkill() {
        return keySkill;
    }

    public void setKeySkill(Boolean keySkill) {
        this.keySkill = keySkill;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 200)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Skill skill = (Skill) o;

        if (id != skill.id) return false;
        if (code != null ? !code.equals(skill.code) : skill.code != null) return false;
        if (collectionId != null ? !collectionId.equals(skill.collectionId) : skill.collectionId != null) return false;
        if (keySkill != null ? !keySkill.equals(skill.keySkill) : skill.keySkill != null) return false;
        if (name != null ? !name.equals(skill.name) : skill.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (collectionId != null ? collectionId.hashCode() : 0);
        result = 31 * result + (keySkill != null ? keySkill.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
