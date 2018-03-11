package com.masdmtr.skillsmonster.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.time.Year;
import java.util.Date;


@SqlResultSetMapping(
        name = "SkillsByMonthRuHhMapping",
        entities = @EntityResult(
                entityClass = SkillRankingItem.class,
                fields = {
                        @FieldResult(name = "id", column = "id"),
                        @FieldResult(name = "month", column = "month"),
                        @FieldResult(name = "skill", column = "skill"),
                        @FieldResult(name = "score", column = "score")}))

@Entity
public class SkillRankingItem {
    @Id
    @JsonIgnore
    private Long id;
    private Timestamp month;
    public String skill;
    private Long score;

    public String getSkill() {
        return skill;
    }

    public Long getId() {
        return id;
    }

    public String getMonth() {
        Month m = month.toLocalDateTime().getMonth();
        int y = month.toLocalDateTime().getYear();
        return String.valueOf(m.getValue()).concat("/").concat(String.valueOf(y));
    }


    public Long getScore() {
        return score;
    }

}

