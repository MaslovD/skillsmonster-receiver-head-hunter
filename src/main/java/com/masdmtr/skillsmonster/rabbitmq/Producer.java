package com.masdmtr.skillsmonster.rabbitmq;

/**
 * Created by dmaslov on 22/07/2018.
 */

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.masdmtr.skillsmonster.dto.ProcessingQueueItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class Producer {
    private static final Logger logger = LoggerFactory.getLogger(Producer.class);

    private final RabbitTemplate rabbitTemplate;
    @Value("${spring.rabbitmq.skillsmonster.exchange}")
    private String dataLoaderSparkExchange;
    @Value("${spring.rabbitmq.skillsmonster.routingKey}")
    public String dataLoaderSparkRoutingKey;

    @Autowired
    public Producer(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    private String itemMessageToJson(ProcessingQueueItem processingQueueItem) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.writeValueAsString(processingQueueItem);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void sendMessage(ProcessingQueueItem queueItem) {
        logger.debug("Sending message...");

        String jsonMsg = itemMessageToJson(queueItem);
        Message jsonMessage = MessageBuilder.withBody(Objects.requireNonNull(jsonMsg).getBytes())
                .andProperties(MessagePropertiesBuilder
                        .newInstance().setContentType("application/json")
                        .build()).build();

        rabbitTemplate.convertAndSend(dataLoaderSparkExchange, dataLoaderSparkRoutingKey, jsonMessage);
    }

}