package com.restapi.book.service;

import com.restapi.book.dto.AuthorDto;
import com.restapi.book.model.Author;
import com.restapi.book.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class AuthorService {


    @Autowired
    AuthorRepository authorRepository;



    public List<Author> getAllAuthors(Integer pageNo, Integer pageSize)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Author> pagedResult = authorRepository.findAll(paging);
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        }
        else {
            return new ArrayList<Author>();
        }

        /*

        List<Author> temp = new ArrayList<Author>();
        try{
            authorRepository.findAll().forEach(temp::add);
        }catch (Exception e)
        {
            System.out.println("***** Exception in getting all authors *****");
        }
        return temp;

        */
    }

    public Author createAuthor(AuthorDto authorDto) throws Exception{
        Author author =  new Author();
        author.setName(authorDto.getName());
        author.setDob(authorDto.getDob());
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

    public Author updateAuthor(AuthorDto authorDto,String authorId) {

        Optional<Author> optional = authorRepository.findById(Integer.parseInt(authorId));
        Author existingAuthor = optional.get();

        try {

            authorRepository.deleteById(Integer.parseInt(authorId));
            if (authorDto.getDob() != null) {
                existingAuthor.setDob(authorDto.getDob());
            }
            if (authorDto.getName() != null) {
                existingAuthor.setName(authorDto.getName());
            }
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
