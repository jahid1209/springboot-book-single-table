package com.restapi.book.repository;

import com.restapi.book.model.Book;
import com.restapi.book.model.Journal;
import com.restapi.book.model.Story;
import com.restapi.book.model.Thesis;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.awt.print.Pageable;
import java.util.List;

@Repository
public class FilteredListDao {
    @PersistenceContext
    protected EntityManager entityManager;

    public List<Book> getFilteredBooksByType(String type){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Book> query = criteriaBuilder.createQuery(Book.class);
        Root<Book> book = query.from(Book.class);

        if(type.equals("story"))
            query.where(criteriaBuilder.equal(book.type(), Story.class));

        else if(type.equals("thesis"))
            query.where(criteriaBuilder.equal(book.type(), Thesis.class));

        else if(type.equals("journal"))
            query.where(criteriaBuilder.equal(book.type(), Journal.class));

        return entityManager.createQuery(query).getResultList();

    }

}
