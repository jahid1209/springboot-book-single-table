package com.restapi.book.controller;


import com.restapi.book.model.Author;
import com.restapi.book.model.Book;
import com.restapi.book.service.AuthorService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api")
@RestController
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @GetMapping("/v1/author/getallauthors")
    public List<Author> getAllAuthors()
    {
        return authorService.getAllAuthors();
    }

    @PostMapping("/v1/author/createauthor")
    @ApiOperation(value="Creates an author")
    public Author createAuthor(@RequestBody Author author)
    {
        try {
            return authorService.createAuthor(author);
        }catch (Exception e){
            System.out.println("Exception");
        }
        return null;
    }


    @DeleteMapping("/v1/author/deleteauthor/{authorId}")
    @ApiOperation(value="Deletes an author by specifid authorId")
    public Optional<Author> deleteAuthor(@PathVariable String authorId)
    {
        try {
            return authorService.deleteAuthor(authorId);
        }catch (Exception e){
            System.out.println("Exception");
        }
        return null;
    }
    @PutMapping("/v1/author/updateauthor/{authorId}")
    @ApiOperation(value="updates an author's information by specifid authorId")
    public Author updateAuthor(@RequestBody Author author,@PathVariable String authorId)
    {
        try {
            return authorService.updateAuthor(author,authorId);
        }catch (Exception e){
            System.out.println("*********** =>Exception in controller[updateauthor method] <=************");
        }
        return null;
    }

}
