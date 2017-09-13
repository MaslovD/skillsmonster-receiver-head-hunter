package com.masdmtr.skillsmonster;

import com.masdmtr.skillsmonster.loader.LoaderController;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManagerFactory;


@SpringBootApplication
@EnableScheduling
@ComponentScan("com.masdmtr")
public class SkillsMonsterApplication {


    @Autowired
    LoaderController loaderController;

    @Autowired
    Logger logger;


    public static void main(String[] args) {
        SpringApplication.run(SkillsMonsterApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        return args -> {

//            Query q = entityManagerFactory.createNativeQuery("CREATE VIEW result_set AS select record FROM my_data");
            //                  q.executeUpdate();


        };
    }
}
