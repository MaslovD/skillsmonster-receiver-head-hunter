package com.masdmtr.skillsmonster.persistence.repository;

import com.masdmtr.skillsmonster.persistence.model.ui.Menu;
import com.masdmtr.skillsmonster.persistence.model.*;
import org.hibernate.*;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmaslov on 09/07/17.
 */

@Repository
@Transactional
@Component
public class SkillsMonsterDaoImpl implements SkillsMonsterDao {


    private SessionFactory sessionFactory;
    private EntityManager entityManager;

    @Autowired
    public SkillsMonsterDaoImpl(SessionFactory sessionFactory, EntityManager entityManager) {
        this.sessionFactory = sessionFactory;
        this.entityManager = entityManager;
    }

    @Override
    public List<Country> getCountryList() {
        try (Session session = sessionFactory.openSession()) {
            Criteria criteria = session.createCriteria(Country.class);
            //List tmpList = criteria.list();
            return criteria.list();
        }
    }

    @Override
    public ArrayList<Specialization> getSpecializationList() {
        try (Session session = sessionFactory.openSession()) {
            Criteria criteria = sessionFactory.openSession().createCriteria(Specialization.class);

            // criteria.add(Restrictions.ilike("subId","1.%"));
            return new ArrayList<Specialization>(criteria.list());
        }
    }

    @Override
    public ArrayList<Area> getAreaList() {
        try (Session session = sessionFactory.openSession()) {
            Criteria criteria = sessionFactory.openSession().createCriteria(Area.class);
            // criteria.add(Restrictions.ilike("subId","1.%"));
            return new ArrayList<Area>(criteria.list());
        }

    }

    @Override
    @Transactional
    public void getVacancyDetailes() {
        StoredProcedureQuery sp = entityManager.createStoredProcedureQuery("public.add_vacancy_to_queue");
        sp.getResultList();
    }

    @Override
    public void updateProcessingQueueItem(ProcessingQueue processingQueueItem) {
        Session session = sessionFactory.openSession();
//        try {
        Transaction tx1 = session.beginTransaction();
        session.update(processingQueueItem);
        tx1.commit();
        session.flush();
//        } finally {
//            session.close();
//        }

        session.close();
    }

    @Override
    public void getAreaChildren(Area area) {

        try (Session session = sessionFactory.openSession()) {
            Criteria criteria = session.createCriteria(Area.class);
            List tmpList = criteria.list();

            //return criteria.list();
        }
    }

    @Override
    public ArrayList<Integer> getAreaCountryList() {

        try (Session session = sessionFactory.openSession()) {
            Criteria criteria = session.createCriteria(Area.class)
                    .setProjection(Projections.projectionList()
                            .add(Projections.groupProperty("countryId")));
            return (ArrayList) criteria.list();

        }
    }

    @Override
    public ArrayList<Area> getAreaByCountryId(Integer countryId) {

        try (Session session = sessionFactory.openSession()) {
            ArrayList<Area> areas = (ArrayList) session.createCriteria(Area.class)
                    .add(Restrictions.eq("countryId", countryId))
                    .list();
            return areas;
        }

    }

    @Override
    public void addCountry(Country country) {

        try (Session session = sessionFactory.openSession()) {
            Transaction tx1 = session.beginTransaction();
            session.save(country);
            tx1.commit();
            session.flush();
        }

    }

    @Override
    public Area getAreaById(int id) {
        try (Session session = sessionFactory.openSession()) {
            Area area = (Area) session.get(Area.class, id);
            return area;
        }

    }

    @Override
    public SourceSite getSourceSiteById(int id) {
        try (Session session = sessionFactory.openSession()) {
            SourceSite sourceSite = (SourceSite) session.get(SourceSite.class, id);
            return sourceSite;
        }
    }

    @Override
    public void addSearchResult(SearchResult searchResult) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx1 = session.beginTransaction();
            session.save(searchResult);
            tx1.commit();
            session.flush();
        }

    }

    @Override
    public ArrayList getListToLoadFromHh() {

        try (Session session = sessionFactory.openSession()) {
            Criteria criteria = session.createCriteria(ProcessingQueue.class);
            criteria.addOrder(Order.asc("createdAt"));
            criteria.add(Restrictions.eq("status", "NEW"));
            criteria.setMaxResults(1000);
            ArrayList<ProcessingQueue> processingQueue = new ArrayList<ProcessingQueue>(criteria.list());
            return processingQueue;
        }
    }

    @Override
    public ArrayList getMenu() {

        try (Session session = sessionFactory.openSession()) {
            Criteria criteria = session.createCriteria(Menu.class);
            criteria.add(Restrictions.isNull("parent"));
            criteria.addOrder(Order.asc("order"));
            return new ArrayList<Menu>(criteria.list());
        }
    }

    @Override
    public void addVacancy(Vacancy vacancy) {

        try (Session session = sessionFactory.openSession()) {
            Transaction tx1 = session.beginTransaction();
            session.save(vacancy);
            tx1.commit();
            session.flush();
        }
    }
}