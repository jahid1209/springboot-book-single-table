package com.restapi.book.repository;

import com.restapi.book.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface AuthorRepository extends PagingAndSortingRepository<Author,Integer> {
}
