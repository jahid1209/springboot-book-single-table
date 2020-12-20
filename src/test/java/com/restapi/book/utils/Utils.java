package com.restapi.book.utils;

import com.restapi.book.dto.AuthorDto;
import com.restapi.book.dto.BookDto;
import com.restapi.book.model.*;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class Utils {


    public static List<Author> createAuthor()
    {
        Author authorOne = new Author();
        authorOne.setDob("1922");
        authorOne.setName("G G Marquez");
        authorOne.setAuthorId(1);

        Author authorTwo = new Author();
        authorTwo.setDob("1971");
        authorTwo.setName("Mark W");
        authorTwo.setAuthorId(2);

        Author authorThree = new Author();
        authorThree.setDob("1990");
        authorThree.setName("John Doe");
        authorThree.setAuthorId(3);

        List<Author> authorList = new ArrayList<Author>();
        authorList.add(authorOne);
        authorList.add(authorTwo);
        authorList.add(authorThree);
        return  authorList;

    }

    public static ResponseEntity<List<Author>>listOfAuthors()
    {
        Author authorOne = new Author();
        authorOne.setDob("1922");
        authorOne.setName("G G");
        authorOne.setAuthorId(1);

        Author authorTwo = new Author();
        authorTwo.setDob("1971");
        authorTwo.setName("Mak W");
        authorTwo.setAuthorId(2);

        Author authorThree = new Author();
        authorThree.setDob("1990");
        authorThree.setName("Jane Doe");
        authorThree.setAuthorId(3);

        List<Author> authorList = new ArrayList<Author>();
        authorList.add(authorOne);
        authorList.add(authorTwo);
        authorList.add(authorThree);
        return (ResponseEntity<List<Author>>) authorList;

    }

    public static List<Book> prepareMockData() {
        List<Author> author = createAuthor();
        Book storyBook = new Story();
        storyBook.setId(4);
        storyBook.setBookName("Hundred Years of Solitude");
        storyBook.setPublishedDate("1970");
        storyBook.setAuthor(author.get(0));
        ((Story) storyBook).setGenre("fiction");

        Book journalBook = new Journal();
        journalBook.setId(5);
        journalBook.setBookName("ANN");
        journalBook.setPublishedDate("2000");
        journalBook.setAuthor(author.get(1));
        ((Journal) journalBook).setPublisher("IEEE");

        Book thesisBook = new Thesis();
        thesisBook .setId(6);
        thesisBook .setBookName("aaaaaaaa");
        thesisBook .setPublishedDate("2020");
        thesisBook .setAuthor(author.get(2));
        ((Thesis) thesisBook ).setTopic("xyzyzyzy");

        List<Book> bookList = new ArrayList<Book>();

        bookList.add(storyBook);
        bookList.add(journalBook);
        bookList.add(thesisBook);

        return bookList;
    }

    public static List<Book> storyBook()
    {
        List<Book>book = new ArrayList<Book>();
        book.add(prepareMockData().get(0));
        return book;
    }


    public static Author singleAuthor()
    {
        return createAuthor().get(0);
    }


    public static Book singleBook()
    {
        return prepareMockData().get(0);
    }
    public static BookDto bookDto(){
        BookDto bookDto = new BookDto();
        bookDto.setBookName("Hundred Years of Solitude");
        bookDto.setPublishedDate("1970");
        bookDto.setGenre("fiction");
        return bookDto;
    }
    public static AuthorDto authorDto(){
        AuthorDto authorDto = new AuthorDto();
        authorDto.setDob("1922");
        authorDto.setName("G G Marquez");
        return authorDto;
    }
}
