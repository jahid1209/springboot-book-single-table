package com.restapi.book.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int authorId;

    @OneToMany(mappedBy = "author", targetEntity=Book.class, fetch=FetchType.LAZY, cascade=CascadeType.ALL,
            orphanRemoval=true)
    @JsonManagedReference
    @Column(nullable = true)
    private List<Book> booksByAuthor;

    private String name;
    private String dob;

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public Author(){}
    public Author(int authorId, List<Book> booksByAuthor, String name, String dob) {
        this.authorId = authorId;
        this.booksByAuthor = booksByAuthor;
        this.name = name;
        this.dob = dob;
    }

    public List<Book> getBooksByAuthor() {
        return booksByAuthor;
    }

    public void setBooksByAuthor(List<Book> booksByAuthor) {
        this.booksByAuthor = booksByAuthor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
