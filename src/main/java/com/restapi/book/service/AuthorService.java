package com.restapi.book.service;

import com.restapi.book.model.Author;
import com.restapi.book.model.Book;
import com.restapi.book.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {


    @Autowired
    AuthorRepository authorRepository;


    public List<Author> getAllAuthors()
    {
        List<Author> temp = new ArrayList<Author>();
        try{
            authorRepository.findAll().forEach(temp::add);
        }catch (Exception e)
        {
            System.out.println("***** Exception in getting all authors *****");
        }
        return temp;
    }

    public Author createAuthor(Author author) throws Exception{
        Author createdAuthor = authorRepository.save(author);
        if(createdAuthor != null){
            return  createdAuthor;
        }
        return null;
    }

    public Optional<Author> deleteAuthor(String authorId) {
        Optional<Author> author=null;
        try {
            author = authorRepository.findById(Integer.parseInt(authorId));
        }catch (Exception e)
        {
            System.out.println("Exception while deleting author");
        }
        try {
            authorRepository.deleteById(Integer.parseInt(authorId));

        }catch(Exception e)
        {
            System.out.println("Exception while deleting an author");
        }
        return author;
    }

    public Author updateAuthor(Author author,String authorId) {

        Optional<Author> optional = authorRepository.findById(Integer.parseInt(authorId));
        Author existingAuthor = optional.get();

        try {

            authorRepository.deleteById(Integer.parseInt(authorId));
            if (author.getDob() != null) {
                existingAuthor.setDob(author.getDob());
            }
            if (author.getName() != null) {
                existingAuthor.setName(author.getName());
            }
            if (author.getBooksByAuthor() != null) {
                existingAuthor.setBooksByAuthor(author.getBooksByAuthor());
            }
            System.out.println(" Name: "+existingAuthor.getName());
            System.out.println(" AuthorId: "+existingAuthor.getAuthorId());

            authorRepository.save(existingAuthor);

            return existingAuthor;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("****** Exception in updating ******");
        }
        return null;
    }
}
