package com.restapi.book.service;

import com.restapi.book.model.Author;
import com.restapi.book.model.Book;

import com.restapi.book.model.Story;
import com.restapi.book.model.Thesis;
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
    public List<Book> getAllBooks()
    {
        List<Book> temp = new ArrayList<Book>();
        try{
            temp = bookRepository.findAll();
        }catch (Exception e)
        {
            System.out.println("***** Exception in getting all books *****");
        }
        for(int i=0;i<temp.size();i++)
        {
            Author author = temp.get(i).getAuthor();
            //temp.get(i).setAuthor(author);
        }
        return temp;
    }
    public List<Book> getPreferedBooksList(String type)
    {
        List<Book> filteredList = new ArrayList<Book>();
        List<Book> temp = new ArrayList<Book>();
        try{
            bookRepository.findAll().forEach(temp::add);
        }catch (Exception e)
        {
            System.out.println("***** Exception in getting prefered books List *****");
        }
        for(int i=0;i<temp.size();i++)
        {
            if(temp.get(i).getType().equals(type))
            {
                filteredList.add(temp.get(i));
            }
        }

        return filteredList;
    }

    public Book addBook(Book book) throws Exception{
        System.out.println("***** trying to add story book *****");
        Book savedBook=null;
        try {
            Optional<Author> author = authorRepository.findById(book.getAuthor().getAuthorId());
            if(author != null){
                book.setAuthor(author.get());
                savedBook = bookRepository.save(book);
            }
            List<Book> books = author.get().getBooksByAuthor();
            if(book == null){
                books = new ArrayList<>();
            }
            books.add(savedBook);
            author.get().setBooksByAuthor(books);
            authorRepository.save(author.get());
        }catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("******** => Dhora khaise chandu <=********");
        }
        if(savedBook != null){
            System.out.println("***** story book added  *****");
            return  savedBook;
        }

        return null;
    }

    public Book updateBook(Book book, String id) {

        Optional<Book> optional = bookRepository.findById(Integer.parseInt(id));

        if(optional != null){
            try {
                Book bookPrev = optional.get();
                if(book.getAuthor()!=null)
                    bookPrev.setAuthor(book.getAuthor());
                if(book.getBookName()!=null)
                    bookPrev.setBookName(book.getBookName());
                if(book.getPublishedDate()!=null)
                    bookPrev.setPublishedDate(book.getPublishedDate());
                bookRepository.save(bookPrev);
                return bookPrev;

            }catch (Exception e){
                e.printStackTrace();
                System.out.println("****** Exception while updating ******");
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

            }
        }

        try {

            return deletedBook;

        }catch (Exception e)
        {
            System.out.println("******** Exception while deletion ********");
        }
        return null;
    }

}
