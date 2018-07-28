package com.masdmtr.skillsmonster.persistence.projection;

import java.sql.Timestamp;

/**
 * Created by dmaslov on 10/1/17.
 */
public class ThreeColumnRanking {
    private Timestamp month;
    private String skill;
    private Integer score;


    public Timestamp getMonth() {
        return month;
    }

    public void setMonth(Timestamp month) {
        this.month = month;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
