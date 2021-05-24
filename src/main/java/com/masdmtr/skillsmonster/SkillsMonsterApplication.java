package com.masdmtr.skillsmonster;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
//@EnableDiscoveryClient
//@EnableEurekaClient
@EnableScheduling
@ComponentScan("com.masdmtr")
public class SkillsMonsterApplication {

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
