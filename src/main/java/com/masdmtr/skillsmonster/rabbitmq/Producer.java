package com.masdmtr.skillsmonster.rabbitmq;

/**
 * Created by dmaslov on 22/07/2018.
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.masdmtr.skillsmonster.dto.SearchRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Producer {
    private static final Logger logger = LoggerFactory.getLogger(Producer.class);

    private final RabbitTemplate rabbitTemplate;
    private ObjectMapper jacksonObjectMapper;

    @Value("${spring.rabbitmq.skillsmonster.exchange.general}")
    private String dataLoaderSparkExchange;
    @Value("${spring.rabbitmq.skillsmonster.routingkey.loadVacancyDetailsRequest}")
    public String dataLoaderSparkRoutingKey;

    @Autowired
    public Producer(final RabbitTemplate rabbitTemplate, ObjectMapper jacksonObjectMapper) {
        this.jacksonObjectMapper = jacksonObjectMapper;
        this.rabbitTemplate = rabbitTemplate;
    }

//    private String itemMessageToJson(SearchResultDto processingQueueItem) {
//
//        try {
//            return jacksonObjectMapper.writeValueAsString(processingQueueItem);
//
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    public void sendMessage(SearchRequestDto queueItem) {
        logger.debug("Sending message...");

//        String jsonMsg = itemMessageToJson(queueItem);
//
//        Message jsonMessage = MessageBuilder.withBody(Objects.requireNonNull(jsonMsg).getBytes())
//                .andProperties(MessagePropertiesBuilder
//                        .newInstance().setContentType("application/json")
//                        .build())
//                .build();

        rabbitTemplate.convertAndSend(dataLoaderSparkExchange, dataLoaderSparkRoutingKey, queueItem);
    }

}