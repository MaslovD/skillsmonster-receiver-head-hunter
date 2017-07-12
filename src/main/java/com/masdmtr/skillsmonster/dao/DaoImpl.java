package com.masdmtr.skillsmonster.dao;

import com.masdmtr.skillsmonster.entity.Country;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by dmaslov on 09/07/17.
 */

@Component
public class DaoImpl implements Dao {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Country> getCountryList() {

        Criteria criteria = sessionFactory.openSession().createCriteria(Country.class);

        List<Country> tmpList= criteria.list();

        return criteria.list();
    }
}