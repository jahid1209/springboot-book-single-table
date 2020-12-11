package com.restapi.book.dto;

import javax.validation.constraints.NotBlank;

public class BookDto {


    @NotBlank(message = "Book Name is mandatory")
    private String bookName;
    @NotBlank(message = "published date is mandatory")
    private String publishedDate;
    private String genre;
    private String topic;
    private String publisher;

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }
}
