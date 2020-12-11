package com.restapi.book.controller;

import com.restapi.book.dto.BookDto;
import com.restapi.book.model.*;
import com.restapi.book.service.BookService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import static util.Constants.*;

@RequestMapping(value ="/book", produces = { MediaType.APPLICATION_JSON_VALUE })
@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @RequestMapping(method = RequestMethod.GET,value = {"/", "/{type}"})
    @ApiOperation(
            value = "Get the list of books and associated information.",
            notes = "Get the list of books and associated information.",
            response = Book.class
    )
    @ApiResponses(value = {

            @ApiResponse(code = 200, message = SUCCESS_REQUEST, response = Book.class),
            @ApiResponse(code = 204, message = NO_BOOK_FOUND),
            @ApiResponse(code = 404, message = RESOURCE_NOT_FOUND_ERROR),
            @ApiResponse(code = 404, message = BAD_REQUEST),
            @ApiResponse(code = 500, message = INTERNAL_SERVER_ERROR),
            @ApiResponse(code = 400, message = PAYLOAD_SYNTAX_ERROR )
    })
    public List<Book> getAllBooks(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "3") Integer size,
            @ApiParam(value = "To filter the retrieved books (type Can be 'story' or 'thesis' or 'journal)")
            @RequestParam(name = "type", required = false)  String type ){

        if(type!="" && type!=null)
            return bookService.getFilteredBooks(type,page,size);
        else
            return bookService.getAllBooks(page,size);
    }

    @ApiOperation(
            value = "Post a book information.",
            notes = "Post book information.",
            response = Book.class
    )
    @ApiResponses(value = {

            @ApiResponse(code = 200, message = SUCCESS_REQUEST, response = Book.class),
            @ApiResponse(code = 204, message = NO_BOOK_FOUND),
            @ApiResponse(code = 404, message = RESOURCE_NOT_FOUND_ERROR),
            @ApiResponse(code = 404, message = BAD_REQUEST),
            @ApiResponse(code = 500, message = INTERNAL_SERVER_ERROR),
            @ApiResponse(code = 400, message = PAYLOAD_SYNTAX_ERROR )
    })

    @RequestMapping(method = RequestMethod.POST,value="/{type}/{id}")
    public Book addBook(
            @Valid
            @RequestBody BookDto bookDto,
            @ApiParam(value = "type can be 'story' or 'thesis' or 'journal")
            @PathVariable String type,
            @ApiParam(value = "author's id for this particular book")
            @PathVariable String id) {
        Book result = null;
        try {
            result = bookService.addBook(bookDto,type, id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @ApiOperation(
            value = "Update a  Book's information.",
            notes = "Update a Book's information.",
            response = Book.class
    )
    @ApiResponses(value = {

            @ApiResponse(code = 200, message = SUCCESS_REQUEST, response = Book.class),
            @ApiResponse(code = 204, message = NO_BOOK_FOUND),
            @ApiResponse(code = 404, message = RESOURCE_NOT_FOUND_ERROR),
            @ApiResponse(code = 404, message = BAD_REQUEST),
            @ApiResponse(code = 500, message = INTERNAL_SERVER_ERROR),
            @ApiResponse(code = 400, message = PAYLOAD_SYNTAX_ERROR )
    })
    @RequestMapping(method = RequestMethod.PUT,value="/{id}")
    public Book updateBook(
            @Valid
            @RequestBody BookDto bookDto,
            @ApiParam(value = "id of the book you wanna update")
            @PathVariable String id) {
        return bookService.updateBook(bookDto,id);
    }

    @ApiOperation(
            value = "Delete a  Book's information.",
            notes = "Delete a Book's information.",
            response = Book.class
    )
    @ApiResponses(value = {

            @ApiResponse(code = 200, message = SUCCESS_REQUEST, response = Book.class),
            @ApiResponse(code = 204, message = NO_BOOK_FOUND),
            @ApiResponse(code = 404, message = RESOURCE_NOT_FOUND_ERROR),
            @ApiResponse(code = 404, message = BAD_REQUEST),
            @ApiResponse(code = 500, message = INTERNAL_SERVER_ERROR),
            @ApiResponse(code = 400, message = PAYLOAD_SYNTAX_ERROR )
    })
    @RequestMapping(method = RequestMethod.DELETE,value="/{id}")
    public Book deleteBook(
            @ApiParam(value = "id of the book you wanna delete")
            @PathVariable String id) {
        return bookService.deleteBook(id);
    }

}
