package com.restapi.book.controller;

import com.restapi.book.model.*;
import com.restapi.book.service.AuthorService;
import com.restapi.book.service.BookService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("/api")
@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    AuthorService authorService;

    @RequestMapping(method = RequestMethod.GET,value="/v1/books/getallbooks")
    @ApiOperation(value="Retrieves all the books and associated information from database")
    public List<Book> getAllBooks()
    {
        return bookService.getAllBooks();
    }


    @RequestMapping(method = RequestMethod.GET,value="/v1/books/{type}")
    @ApiOperation(value="Filters books by type (Story/Thesis/Journal)")
    public List<Book> getPreferedBooksList(@PathVariable String type)
    {
        return bookService.getPreferedBooksList(type);
    }

    @RequestMapping(method = RequestMethod.POST,value="/v1/books/story")
    @ApiOperation(value="{type} = story")
    public Book addBook(@RequestBody Story story) {
        story.setType("story");
        Book result = null;
        try {

            result = bookService.addBook(story);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("********* Exception in adding book **********");
        }
        /*try {
            Author author = story.getAuthor();
            authorService.addBookToAuthor(author,story);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Exception in posting book");
        }*/

        return result;
    }

    @RequestMapping(method = RequestMethod.POST,value="/v1/books/thesis")
    @ApiOperation(value="{type} = thesis")
    public Book addThesis(@RequestBody Thesis thesis)
    {
        thesis.setType("thesis");
        Book result = null;
        try {
            result = bookService.addBook(thesis);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("********** Exception in posting thesis ***********");
        }
        return result;

    }
    @RequestMapping(method = RequestMethod.POST,value="/v1/books/journal")
    @ApiOperation(value="{type} =journal")
    public Book addJournal(@RequestBody Journal journal)
    {
        journal.setType("journal");
        Book result = null;
        try {
            result = bookService.addBook(journal);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("********** Exception in posting journal ***********");
        }
        return result;
    }
    @RequestMapping(method = RequestMethod.PUT,value="/v1/books/{id}")
    public Book updateBook(@RequestBody Book book, @PathVariable String id) {
        return bookService.updateBook(book,id);
    }

    @RequestMapping(method = RequestMethod.DELETE,value="/v1/books/{id}")
    public Book deleteBook(@PathVariable String id)
    {
        return bookService.deleteBook(id);
    }

}
