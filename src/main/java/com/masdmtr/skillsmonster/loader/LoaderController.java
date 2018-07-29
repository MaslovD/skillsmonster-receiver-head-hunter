package com.masdmtr.skillsmonster.loader;


import com.masdmtr.skillsmonster.receiver.Receiver;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by dmaslov on 7/31/17.
 */
@Service
@ComponentScan("com.masdmtr.skillsmonster")
@Scope("singleton")
public class LoaderController {

    private Receiver receiver;
    private Logger logger;


    @Autowired
    public LoaderController(Receiver receiver, Logger logger) {
        this.receiver = receiver;
        this.logger = logger;
    }


    @PostConstruct
    private void initController() {
        // Receiver receiver = beans.getBean("HeadHunter", Receiver.class);
        receivers.add(receiver);
        logger.info(receiver.toString());
    }

    private List<Receiver> receivers = new LinkedList<>();

    public List<Receiver> getReceivers() {
        return receivers;
    }

    public void setReceivers(List<Receiver> receivers) {
        this.receivers = receivers;
    }
}
