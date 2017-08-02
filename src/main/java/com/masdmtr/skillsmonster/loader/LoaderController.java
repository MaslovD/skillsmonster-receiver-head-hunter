package com.masdmtr.skillsmonster.loader;

import com.masdmtr.skillsmonster.receiver.Receiver;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import sun.jvm.hotspot.debugger.linux.sparc.LinuxSPARCThreadContext;

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

    @Autowired
    @Qualifier("HeadHunter")
    Receiver receiver;

    @Autowired
    BeanFactory beans;


    @PostConstruct
    private void initController() {
       // Receiver receiver = beans.getBean("HeadHunter", Receiver.class);
        receivers.add(receiver);
        System.out.println(receiver);
    }

    private List<Receiver> receivers = new LinkedList<>();

    public List<Receiver> getReceivers() {
        return receivers;
    }

    public void setReceivers(List<Receiver> receivers) {
        this.receivers = receivers;
    }
}
