package com.masdmtr.skillsmonster.config;

import org.aopalliance.aop.Advice;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.RetryInterceptorBuilder;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.retry.RejectAndDontRequeueRecoverer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.interceptor.RetryOperationsInterceptor;

/**
 * Proudly created by dmaslov on 02/01/2018.
 */

@Configuration
public class RabbitConfig {

    @Value("${spring.rabbitmq.host}")
    private String rabbitmqHost;
    @Value("${spring.rabbitmq.username}")
    private String rabbitmqUsername;
    @Value("${spring.rabbitmq.password}")
    private String rabbitmqPassword;
    @Value("#{new Integer('${spring.rabbitmq.port}')}")
    private Integer rabbitmqPort;
    @Value("${spring.rabbitmq.virtual-host}")
    private String rabbitmqVirtualHost;
    @Value("${spring.rabbitmq.concurrentConsumers}")
    private Integer concurrentConsumers;
    @Value("${spring.rabbitmq.maxConcurrentConsumers}")
    private Integer maxConcurrentConsumers;


    @Value("${spring.rabbitmq.skillsmonster.queue.loadVacancyDetailsRequest}")
    public String skillsMonsterLoadVacancyDetailsRequestQueue;
    @Value("${spring.rabbitmq.skillsmonster.queue.loadVacancyDetailsRequestDeadLetter}")
    public String skillsMonsterLoadVacancyDetailsRequestDeadLetterQueue;

    @Value("${spring.rabbitmq.skillsmonster.exchange.general}")
    private String skillsMonsterGeneralExchange;
    @Value("${spring.rabbitmq.skillsmonster.exchange.dlx}")
    private String skillsMonsterDeadLetterExchange;

    @Value("${spring.rabbitmq.skillsmonster.routingkey.loadVacancyDetailsRequest}")
    public String skillsMonsterLoadVacancyDetailsRequestRoutingKey;


    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory =
                new CachingConnectionFactory(rabbitmqHost);
        connectionFactory.setUsername(rabbitmqUsername);
        connectionFactory.setPassword(rabbitmqPassword);
        connectionFactory.setPort(rabbitmqPort);
        connectionFactory.setVirtualHost(rabbitmqVirtualHost);
        return connectionFactory;
    }


    @Bean
    public Queue skillsMonsterLoadVacancyDetailsRequestQueue() {
        return QueueBuilder.durable(skillsMonsterLoadVacancyDetailsRequestQueue)
                .withArgument("x-dead-letter-exchange", skillsMonsterDeadLetterExchange)
                // .withArgument("x-dead-letter-routing-key", dataLoaderSkillsmonsterDeadLetterQueue)
                .build();
    }

    @Bean
    Queue skillsMonsterLoadVacancyDetailsRequestDeadLetterQueue() {
        return QueueBuilder
                .durable(skillsMonsterLoadVacancyDetailsRequestDeadLetterQueue)
                .build();
    }

    @Bean
    public TopicExchange skillsMonsterGeneralExchange() {
        return new TopicExchange(skillsMonsterGeneralExchange);
    }

    @Bean
    public TopicExchange skillsMonsterDeadLetterExchange() {
        return new TopicExchange(skillsMonsterDeadLetterExchange);
    }


    @Bean
    public Binding genericBinding() {
        return BindingBuilder
                .bind(skillsMonsterLoadVacancyDetailsRequestQueue())
                .to(skillsMonsterGeneralExchange())
                .with(skillsMonsterLoadVacancyDetailsRequestRoutingKey);
    }

    @Bean
    public Binding deadLetterBinding() {
        return BindingBuilder
                .bind(skillsMonsterLoadVacancyDetailsRequestDeadLetterQueue())
                .to(skillsMonsterDeadLetterExchange())
                .with(skillsMonsterLoadVacancyDetailsRequestRoutingKey);
    }


    @Bean
    public RetryOperationsInterceptor rabbitSourceRetryInterceptor() {
        return RetryInterceptorBuilder.stateless()
                .maxAttempts(2)
                .recoverer(new RejectAndDontRequeueRecoverer())
                .build();
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setConcurrentConsumers(concurrentConsumers);
        factory.setMaxConcurrentConsumers(maxConcurrentConsumers);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setAdviceChain(new Advice[]{rabbitSourceRetryInterceptor()});
        return factory;
    }

    @Bean
    public RabbitTemplate jsonRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonConverter());
        return template;
    }

//    @Bean
//    public SimpleRabbitListenerContainerFactory myRabbitListenerContainerFactory() {
//        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//        factory.setMessageConverter(new Jackson2JsonMessageConverter());
//        //factory.setConnectionFactory(connectionFactory());
//        factory.setMaxConcurrentConsumers(5);
//        return factory;
//    }

    @Bean
    public MessageConverter jsonConverter() {

        return new Jackson2JsonMessageConverter();
    }


}
