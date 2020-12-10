package com.restapi.book.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Journal extends Book {
    @Column(nullable=false)
    private String publisher;

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
