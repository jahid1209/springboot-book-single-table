package com.restapi.book.service;

import com.restapi.book.dto.BookDto;
import com.restapi.book.model.*;

import com.restapi.book.repository.AuthorRepository;
import com.restapi.book.repository.BookRepository;
import com.restapi.book.repository.FilteredListDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {


    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private FilteredListDao filteredListDao;


    BookService()
    {
    }

    public List<Book> getFilteredBooks(String type,Integer pageNo,Integer pageSize) {

        Pageable paging = PageRequest.of(pageNo, pageSize);
        List<Book> filteredList = filteredListDao.getFilteredBooksByType(type);
        Page<Book> pagedResult = new PageImpl<>(filteredList);
        if(pagedResult.hasContent())
            return pagedResult.getContent();
        else
            return new ArrayList<Book>();
    }
    public List<Book> getAllBooks(Integer pageNo, Integer pageSize)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Book> pagedResult = bookRepository.findAll(paging);
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        }
        else {
            return new ArrayList<Book>();
        }
    }

    public Book updateBook(BookDto bookDto, String id) {

        Optional<Book> optional = bookRepository.findById(Integer.parseInt(id));

        if(optional != null){
            try {
                Book bookPrev = optional.get();

                if(bookDto.getBookName()!=null)
                    bookPrev.setBookName(bookDto.getBookName());
                if(bookDto.getPublishedDate()!=null)
                    bookPrev.setPublishedDate(bookDto.getPublishedDate());
                bookRepository.save(bookPrev);
                return bookPrev;

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public Book deleteBook(String id) {

        Optional<Book> book = bookRepository.findById(Integer.parseInt(id));
        Book deletedBook = book.get();
        bookRepository.deleteById(Integer.parseInt(id));
        return deletedBook;
    }
    public Book addBook(BookDto bookDto, String type, String authorId) throws Exception{

        Optional<Author> author = authorRepository.findById(Integer.parseInt(authorId));
        List<Book> books = author.get().getBooksByAuthor();
        if(books == null){
            books = new ArrayList<Book>();
        }
        if(type.equals("story"))
        {
            Story story = new Story();
            story.setGenre(bookDto.getGenre());
            story.setAuthor(author.get());
            story.setBookName(bookDto.getBookName());
            story.setPublishedDate(bookDto.getPublishedDate());
            bookRepository.save(story);
            books.add(story);
            author.get().setBooksByAuthor(books);
            authorRepository.save(author.get());

            return story;
        }
        else if(type.equals("journal"))
        {
            Journal journal = new Journal();
            journal.setPublisher(bookDto.getPublisher());
            journal.setAuthor(author.get());
            journal.setBookName(bookDto.getBookName());
            journal.setPublishedDate(bookDto.getPublishedDate());
            bookRepository.save(journal);
            books.add(journal);
            author.get().setBooksByAuthor(books);
            authorRepository.save(author.get());
            return journal;

        }

        else if(type.equals("thesis"))
        {
            Thesis thesis = new Thesis();
            thesis.setTopic(bookDto.getTopic());
            thesis.setAuthor(author.get());
            thesis.setBookName(bookDto.getBookName());
            thesis.setPublishedDate(bookDto.getPublishedDate());
            bookRepository.save(thesis);
            books.add(thesis);
            author.get().setBooksByAuthor(books);
            authorRepository.save(author.get());
            return thesis;

        }
        else
            return null;

    }

}
