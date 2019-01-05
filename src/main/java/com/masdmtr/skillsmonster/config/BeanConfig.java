package com.masdmtr.skillsmonster.config;

import com.google.gson.*;
import com.masdmtr.skillsmonster.SkillsMonsterApplication;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.Converter;
import javax.persistence.EntityManagerFactory;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by dmaslov on 11/07/17.
 */
@Configuration
public class BeanConfig {

    private EntityManagerFactory entityManagerFactory;
    private DateFormatter dateFormatter;

    @Autowired
    public BeanConfig(EntityManagerFactory entityManagerFactory, DateFormatter dateFormatter) {
        this.entityManagerFactory = entityManagerFactory;
        this.dateFormatter = dateFormatter;
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

    @Bean
    public Gson gson() {
        Gson gson = new GsonBuilder().registerTypeAdapter(OffsetDateTime.class, new JsonDeserializer<OffsetDateTime>() {
            @Override
            public OffsetDateTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                String offsetDateTime = jsonElement.toString().replaceAll("^\"+|\"+$", "");

                return OffsetDateTime.parse(offsetDateTime, dateFormatter.getFormatter());
            }

        }).create();

        return gson;
    }
}
