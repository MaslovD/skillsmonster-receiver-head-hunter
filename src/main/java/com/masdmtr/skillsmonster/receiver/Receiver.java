package com.masdmtr.skillsmonster.receiver;

import org.springframework.stereotype.Component;

/**
 * Created by dmaslov on 7/31/17.
 */
@Component
public interface Receiver  {
    public void load ();
    public void loadVacancyDetailes();
    public void updateProcessingQueue();
}
