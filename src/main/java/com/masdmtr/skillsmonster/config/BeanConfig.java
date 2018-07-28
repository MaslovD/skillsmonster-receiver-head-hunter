package com.masdmtr.skillsmonster.config;

import com.masdmtr.skillsmonster.SkillsMonsterApplication;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by dmaslov on 11/07/17.
 */
@Configuration
public class BeanConfig {


    private EntityManagerFactory entityManagerFactory;

    @Autowired
    public BeanConfig(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Bean
    public SessionFactory getSessionFactory() {

        if (entityManagerFactory.unwrap(SessionFactory.class) == null) {
            throw new NullPointerException("Factory is not a hibernate factory");
        }
        return entityManagerFactory.unwrap(SessionFactory.class);
    }

    @Bean
    public Logger logger() {
        return LoggerFactory.getLogger(SkillsMonsterApplication.class);
    }
    //public static final Logger logger = LoggerFactory.getLogger(SkillsMonsterApplication.class);

    @Bean
    public Executor taskScheduler() {
        return Executors.newScheduledThreadPool(5);
    }
}
