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

    @Value("${spring.rabbitmq.skillsmonster.exchange}")
    private String dataLoaderSkillsMonsterExchange;
    @Value("${spring.rabbitmq.skillsmonster.queue.searchResults}")
    public String dataLoaderSkillsMonsterSearchResultsQueue;

    @Value("${spring.rabbitmq.skillsmonster.queue.serialLoader}")
    public String dataLoaderSkillsMonsterSerialLoaderQueue;

    @Value("${spring.rabbitmq.skillsmonster.routingKey}")
    public String dataLoaderSkillsmonsterRoutingKey;
    @Value("${spring.rabbitmq.skillsmonster.deadLetterQueue}")
    public String dataLoaderSkillsmonsterDeadLetterQueue;
    @Value("${spring.rabbitmq.skillsmonster.deadLetterExchange}")
    public String dataLoaderSkillsMonsterDeadLetterExchange;

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
    public TopicExchange exchange() {
        return new TopicExchange(dataLoaderSkillsMonsterExchange);
    }

    @Bean
    public Queue skillsMonsterSearchResultsQueue() {
        return QueueBuilder.durable(dataLoaderSkillsMonsterSearchResultsQueue)
                .withArgument("x-dead-letter-exchange", dataLoaderSkillsMonsterDeadLetterExchange)
                // .withArgument("x-dead-letter-routing-key", dataLoaderSkillsmonsterDeadLetterQueue)
                .build();
    }

    @Bean
    public Queue skillsMonsterSerialLoaderQueue() {
        return QueueBuilder.durable(dataLoaderSkillsMonsterSerialLoaderQueue)
                .withArgument("x-dead-letter-exchange", dataLoaderSkillsMonsterDeadLetterExchange)
                // .withArgument("x-dead-letter-routing-key", dataLoaderSkillsmonsterDeadLetterQueue)
                .build();
    }

    @Bean
    public Binding declareBindingGeneric() {
        return BindingBuilder.bind(skillsMonsterSearchResultsQueue()).to(exchange()).with(dataLoaderSkillsmonsterRoutingKey);


    }

    @Bean
    Queue deadLetterQueue() {
        return QueueBuilder.durable(dataLoaderSkillsmonsterDeadLetterQueue).build();
    }

    @Bean
    public RetryOperationsInterceptor rabbitSourceRetryInterceptor() {
        return RetryInterceptorBuilder.stateless()
                .maxAttempts(5)
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
        Jackson2JsonMessageConverter jsonConverter = new Jackson2JsonMessageConverter();
        //jsonConverter.setClassMapper(classMapper());

        return jsonConverter;
    }



}
