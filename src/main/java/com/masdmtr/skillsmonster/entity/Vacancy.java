package com.masdmtr.skillsmonster.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Map;

/**
 * Created by dmaslov on 7/26/17.
 */
@Entity
public class Vacancy {
    private long id;
    private Timestamp loadDateTime;
    private Map<String, Object> rawData;
    private String vacancyId;
    private ProcessingQueue processingQueueId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "vacancy_id", nullable = false, length = 100)
    public String getVacancyId() {
        return vacancyId;
    }

    public void setVacancyId(String vacancyId) {
        this.vacancyId = vacancyId;
    }

    @Basic
    @Type(type = "JsonDataUserType")
    @Column(name = "raw_data", nullable = true)
    public Map<String, Object> getRawData() {
        return rawData;
    }

    public void setRawData(Map<String, Object> rawResponse) {
        this.rawData = rawResponse;
    }

    @Basic
    @Column(name = "load_date_time")
    public Timestamp getLoadDateTime() {
        return loadDateTime;
    }

    public void setLoadDateTime(Timestamp loadDateTime) {
        this.loadDateTime = loadDateTime;
    }

    @ManyToOne
    @JoinColumn(name = "processing_queue_id", referencedColumnName = "id")
    public ProcessingQueue getProcessingQueueId() {
        return processingQueueId;
    }

    public void setProcessingQueueId(ProcessingQueue processingQueueId) {
        this.processingQueueId = processingQueueId;
    }
}
