package com.masdmtr.skillsmonster.dao;

import com.masdmtr.skillsmonster.entity.*;
import com.masdmtr.skillsmonster.entity.ui.Menu;
import org.hibernate.*;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmaslov on 09/07/17.
 */

@Repository
@Transactional
@Component
public class SkillsMonsterDaoImpl implements SkillsMonsterDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Country> getCountryList() {

        Criteria criteria = sessionFactory.openSession().createCriteria(Country.class);
        //List tmpList = criteria.list();
        return criteria.list();
    }

    @Override
    public ArrayList<Specialization> getSpecializationList() {
        Criteria criteria = sessionFactory.openSession().createCriteria(Specialization.class);
       // criteria.add(Restrictions.ilike("subId","1.%"));
        return new ArrayList<Specialization>(criteria.list());
    }

    @Override
    public ArrayList<Area> getAreaList() {
        Criteria criteria = sessionFactory.openSession().createCriteria(Area.class);
       // criteria.add(Restrictions.ilike("subId","1.%"));
        return new ArrayList<Area>(criteria.list());
    }



    @Override
    public void addCountry(Country country) {
        System.out.println();
        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(country);
        tx1.commit();
        session.flush();
        session.close();
    }

    @Override
    public Area getAreaById(int id) {
        Session session = sessionFactory.openSession();
        Area area = (Area) session.get(Area.class, id);
        return area;
    }

    @Override
    public SourceSite getSourceSiteById(int id) {
        Session session = sessionFactory.openSession();
        SourceSite sourceSite = (SourceSite) session.get(SourceSite.class, id);
        return sourceSite;
    }

    @Override
    public void addSearchResult(SearchResult searchResult) {
        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(searchResult);
        tx1.commit();
        session.flush();
        session.close();
    }

    @Override
    public ArrayList getListToLoadFromHh() {

        Criteria criteria = sessionFactory.openSession().createCriteria(VacancyToLoadHh.class);
        return new ArrayList<VacancyToLoadHh>(criteria.list());
        //System.out.println("fd");
    }

    @Override
    public ArrayList getMenu() {

        Criteria criteria = sessionFactory.openSession().createCriteria(Menu.class);
        criteria.add(Restrictions.isNull("parent"));
        criteria.addOrder(Order.asc("order"));

        return new ArrayList<Menu>(criteria.list());
        //System.out.println("fd");
    }

    @Override
    public void addVacancy(Vacancy vacancy) {
        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(vacancy);
        tx1.commit();
        session.flush();
        session.close();

    }

    @Override
    public void addSearchRequest(SearchRequest searchRequest) {
        System.out.println();
        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(searchRequest);
        tx1.commit();
        session.flush();
        session.close();
    }


}