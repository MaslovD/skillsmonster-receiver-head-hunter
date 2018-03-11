package com.masdmtr.skillsmonster.dto;

import javax.persistence.*;
import java.sql.Timestamp;


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
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "id", updatable = false, nullable = false)
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


    public Timestamp getMonth() {
        return month;
    }


    public Long getScore() {
        return score;
    }

}

