package com.masdmtr.skillsmonster.persistence.repository;

import com.masdmtr.skillsmonster.persistence.model.*;
import com.masdmtr.skillsmonster.persistence.model.ui.Menu;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmaslov on 09/07/17.
 */

@Repository
@Transactional(rollbackFor = Exception.class)
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
    public ArrayList<VacancyArch> getVacancyArchList(Long idStart, Long idEnd) {
        try (Session session = sessionFactory.openSession()) {
            Criteria criteria = session.createCriteria(VacancyArch.class);

            criteria.add(Restrictions.between("id", idStart, idEnd));

            return new ArrayList<VacancyArch>(criteria.list());
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
    public void getVacancyDetails() {
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
    public Boolean isVacancyExistInDb() {

        Vacancy vacancy;

        return null;
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
    public void addVacancy(Vacancy vacancy) throws ConstraintViolationException, JpaSystemException {
        try (Session session = sessionFactory.openSession()) {

            if (!exists(vacancy, session)) {
                session.persist(vacancy);
                session.flush();
            }
        }
    }

    public Boolean exists(Vacancy vacancy, Session session) {

        return session.createCriteria(Vacancy.class)
                .add(Restrictions.eq("vacancyId", vacancy.getVacancyId()))
                .add(Restrictions.eq("createdAt", vacancy.getCreatedAt()))
                .add(Restrictions.eq("publishedAt", vacancy.getPublishedAt()))
                .setProjection(Projections.property("vacancyId"))
                .uniqueResult() != null;

//        Query query = session.createQuery("select 1 from vacancy2 v where v.vacancy_id = :v_id");
//        query.setString("v_id", vacancy.getVacancyId());
//        return (query.uniqueResult() != null);


    }
}