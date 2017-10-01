package com.masdmtr.skillsmonster.entity.analytics;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Created by dmaslov on 10/1/17.
 */
@Entity
@Table(name = "top_prog_lang_ru_hh", schema = "public", catalog = "skillsmonster")
@Immutable
public class TopProgLangRuHh {
    private Timestamp month;
    private String skill;
    private Long score;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Basic
    @Column(name = "month", nullable = true)
    public Timestamp getMonth() {
        return month;
    }

    public void setMonth(Timestamp month) {
        this.month = month;
    }

    @Basic
    @Column(name = "skill", nullable = true, length = -1)
    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    @Basic
    @Column(name = "score", nullable = true)
    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TopProgLangRuHh that = (TopProgLangRuHh) o;
        return Objects.equals(month, that.month) &&
                Objects.equals(skill, that.skill) &&
                Objects.equals(score, that.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(month, skill, score);
    }
}
