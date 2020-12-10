package com.restapi.book.repository;

import com.restapi.book.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthorRepository extends JpaRepository<Author,Integer> {
}
