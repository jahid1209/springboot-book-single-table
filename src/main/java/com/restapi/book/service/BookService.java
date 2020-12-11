package com.restapi.book.service;

import com.restapi.book.dto.BookDto;
import com.restapi.book.model.*;

import com.restapi.book.repository.AuthorRepository;
import com.restapi.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {


    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    BookService()
    {
    }

    public List<Book> getFilteredBooks(String type) {
        System.out.println("Type :"+type);
        List<Book> temp = new ArrayList<Book>();
        List<Book> filteredList = new ArrayList<Book>();
        for(int i=0;i<temp.size();i++)
        {
            if(temp.get(i).getType().equals(type))
            {
                filteredList.add(temp.get(i));
            }
        }
        return filteredList;
    }
    public List<Book> getAllBooks()
    {
        List<Book> temp = new ArrayList<Book>();

        try{
            temp = bookRepository.findAll();
        }catch (Exception e)
        {
            System.out.println("***** Exception in getting all books *****");
        }
        return temp;
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

        Optional<Book> book=null;
        try {
            book = bookRepository.findById(Integer.parseInt(id));

        }catch (Exception e)
        {
            System.out.println("Book cant be found");
        }
        List<Book> listOfBooks = bookRepository.findAll();
        Book deletedBook = book.get();
        for (int i=0;i<listOfBooks.size();i++)
        {
            if(listOfBooks.get(i).getId() == Integer.parseInt(id))
            {
                bookRepository.delete(listOfBooks.get(i));
                break;
            }
        }
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
            story.setType("story");
            story.setGenre(bookDto.getGenre());
            story.setAuthor(author.get());
            story.setBookName(bookDto.getBookName());
            story.setPublishedDate(bookDto.getPublishedDate());
            books.add(story);
            author.get().setBooksByAuthor(books);
            authorRepository.save(author.get());

            return story;
        }
        else if(type.equals("journal"))
        {
            Journal journal = new Journal();
            journal.setType("journal");
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
            thesis.setType("journal");
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
