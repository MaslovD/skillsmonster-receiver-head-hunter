package com.masdmtr.skillsmonster.rabbitmq;

import com.masdmtr.skillsmonster.dto.SearchRequestDto;
import com.masdmtr.skillsmonster.receiver.Receiver;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dmaslov on 02/01/2018.
 */

@Service
public class Consumer {
    //   private static final Logger logger = LoggerFactory.getLogger(Consumer.class);
    private Receiver receiver;

    @Autowired
    public Consumer(Receiver receiver) {
        this.receiver = receiver;
    }

//    @RabbitListener(queues = "${spring.rabbitmq.skillsmonster.routingkey.loadVacancyDetailsRequest}")
//    public void receiveProcessingQueueItem(final SearchResultDto processingQueueItem) {
//        receiver.loadVacancyDetails(processingQueueItem);
//    }

    //@RabbitListener(queues = "${spring.rabbitmq.skillsmonster.queue.loadVacancyDetailsRequest}")
    public void receiveProcessingQueueSerialItem(final SearchRequestDto searchRequestDto) {
        receiver.loadVacancyDetails(searchRequestDto);
    }
}
