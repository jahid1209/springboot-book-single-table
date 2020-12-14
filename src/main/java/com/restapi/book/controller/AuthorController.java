package com.restapi.book.controller;
import com.restapi.book.dto.AuthorDto;
import com.restapi.book.model.Author;
import com.restapi.book.model.Book;
import com.restapi.book.service.AuthorService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static util.Constants.*;
import static util.Constants.INTERNAL_SERVER_ERROR;

@RequestMapping(value = "/author", produces = { MediaType.APPLICATION_JSON_VALUE })
@RestController
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @GetMapping("/")

    @ApiOperation(
            value = "retrieves all author's information.",
            notes = "retrieves all author's information.",
            response = Author.class
    )
    @ApiResponses(value = {

            @ApiResponse(code = 200, message = SUCCESS_REQUEST, response = Book.class),
            @ApiResponse(code = 204, message = NO_BOOK_FOUND),
            @ApiResponse(code = 404, message = RESOURCE_NOT_FOUND_ERROR),
            @ApiResponse(code = 404, message = BAD_REQUEST),
            @ApiResponse(code = 500, message = INTERNAL_SERVER_ERROR),
            @ApiResponse(code = 400, message = PAYLOAD_SYNTAX_ERROR )
    })

    public List<Author> getAllAuthors(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "3") Integer size) {

        return authorService.getAllAuthors(page,size);
    }

    @PostMapping("/")
    @ApiOperation(
            value = "creates an author with the provided information",
            notes = "creates an author with the provided information",
            response = Author.class
    )
    @ApiResponses(value = {

            @ApiResponse(code = 200, message = SUCCESS_REQUEST, response = Book.class),
            @ApiResponse(code = 204, message = NO_BOOK_FOUND),
            @ApiResponse(code = 404, message = RESOURCE_NOT_FOUND_ERROR),
            @ApiResponse(code = 404, message = BAD_REQUEST),
            @ApiResponse(code = 500, message = INTERNAL_SERVER_ERROR),
            @ApiResponse(code = 400, message = PAYLOAD_SYNTAX_ERROR )
    })

    public Author createAuthor(
            @ApiParam(value = "creates an author with the provided information")
            @Valid
            @RequestBody AuthorDto authorDto) {
        try {
            return authorService.createAuthor(authorDto);
        }catch (Exception e){
            System.out.println("Exception");
        }
        return null;
    }

    @DeleteMapping("/{authorId}")

    @ApiOperation(
            value = "deletes an author",
            notes = "delete an author",
            response = Author.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SUCCESS_REQUEST, response = Book.class),
            @ApiResponse(code = 204, message = NO_BOOK_FOUND),
            @ApiResponse(code = 404, message = RESOURCE_NOT_FOUND_ERROR),
            @ApiResponse(code = 404, message = BAD_REQUEST),
            @ApiResponse(code = 500, message = INTERNAL_SERVER_ERROR),
            @ApiResponse(code = 400, message = PAYLOAD_SYNTAX_ERROR )
    })
    public Optional<Author> deleteAuthor(
            @ApiParam(value = "id of the author you wanna delete")
            @PathVariable String authorId) {

        try {
            return authorService.deleteAuthor(authorId);
        }catch (Exception e){
            System.out.println("Exception");
        }
        return null;
    }
    @PutMapping("{authorId}")
    @ApiOperation(
            value = "adds an author",
            notes = "adds an author",
            response = Author.class
    )
    @ApiResponses(value = {

            @ApiResponse(code = 200, message = SUCCESS_REQUEST, response = Book.class),
            @ApiResponse(code = 204, message = NO_BOOK_FOUND),
            @ApiResponse(code = 404, message = RESOURCE_NOT_FOUND_ERROR),
            @ApiResponse(code = 404, message = BAD_REQUEST),
            @ApiResponse(code = 500, message = INTERNAL_SERVER_ERROR),
            @ApiResponse(code = 400, message = PAYLOAD_SYNTAX_ERROR )
    })
    public Author updateAuthor(
            @Valid
            @RequestBody AuthorDto authorDto,
            @ApiParam(value = "id of the author you wanna update")
            @PathVariable String authorId) {
        try {
            return authorService.updateAuthor(authorDto,authorId);
        }catch (Exception e){
            System.out.println("*********** =>Exception in controller[updateauthor method] <=************");
        }
        return null;
    }
}
