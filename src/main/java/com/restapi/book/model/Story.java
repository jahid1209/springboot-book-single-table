package com.restapi.book.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Story extends Book {
    @Column(nullable=false)
    private String genre;


    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
