package com.masdmtr.skillsmonster.dao;

import com.masdmtr.skillsmonster.entity.analytics.ThreeColumnRanking;
import com.masdmtr.skillsmonster.entity.analytics.TopProgLangRuHh;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by dmaslov on 10/1/17.
 */


@SqlResultSetMapping(
        name = "ThreeColumnRankingMapping",
        entities = @EntityResult(
                entityClass = ThreeColumnRanking.class,
                fields = {
                        @FieldResult(name = "month", column = "month"),
                        @FieldResult(name = "skill", column = "skill"),
                        @FieldResult(name = "score", column = "score")}))
@Repository
@Transactional
@Component
public class AnalyticsDaoImpl implements AnalyticsDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    EntityManager em;


    @Override
    public List<TopProgLangRuHh> executeQuery(String q) {

        return em.createNativeQuery(q, TopProgLangRuHh.class).getResultList();

    }
}
