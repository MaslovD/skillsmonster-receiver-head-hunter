package com.masdmtr.skillsmonster.dao;

import com.masdmtr.skillsmonster.dto.SkillRankingItem;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

/**
 * Created by dmaslov on 10/1/17.
 */

@Repository
@Transactional
@Component
public class AnalyticsDaoImpl implements AnalyticsDao {

    private SessionFactory sessionFactory;
    private EntityManager em;

    @Autowired
    public AnalyticsDaoImpl(SessionFactory sessionFactory, EntityManager em) {
        this.sessionFactory = sessionFactory;
        this.em = em;
    }

    @Override
    public List executeQuery(String query) {
        return em.createNativeQuery(query, SkillRankingItem.class).getResultList();
    }
}
