package com.restapi.book.repository;

import com.restapi.book.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
