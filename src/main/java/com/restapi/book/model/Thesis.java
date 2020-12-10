package com.restapi.book.model;

import javax.persistence.Column;
import javax.persistence.Entity;
@Entity
public class Thesis extends Book{
    @Column(nullable=false)
    private String topic;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
