package com.restapi.book.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;;
import java.util.List;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable=false)
    private String bookName;

    private String publishedDate;
    @ManyToOne(targetEntity=Author.class, fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name="author_fk")
    @JsonBackReference
    private Author author;

    @ApiModelProperty(readOnly = true)
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public Book(){}

    public Book(int id, String bookName, String publishedDate, Author author, String type) {
        this.id = id;
        this.bookName = bookName;
        this.publishedDate = publishedDate;
        this.author = author;
        this.type = type;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }
}

