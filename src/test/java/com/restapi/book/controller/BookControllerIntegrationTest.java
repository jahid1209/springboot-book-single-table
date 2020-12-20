package com.restapi.book.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restapi.book.dto.BookDto;
import com.restapi.book.model.Author;
import com.restapi.book.model.Book;
import com.restapi.book.model.Story;
import com.restapi.book.service.BookService;
import com.restapi.book.utils.Utils;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
public class BookControllerIntegrationTest {

    @InjectMocks
    private BookController bookController;

    @MockBean
    private BookService bookService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testGetAllBooks() throws Exception {

        when(bookService.getAllBooks(anyInt(),anyInt()))
                .thenReturn(Utils.prepareMockData());

        List<Book> bookList = bookService.getAllBooks(0,3);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/book/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(bookList.size())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookName", is("Hundred Years of Solitude")))
        ;
        System.out.println(bookList.get(0).getBookName());
    }

    @Test
    public void testBooksByType() throws Exception {

        String bookType="story";
        when(bookService.getFilteredBooks(any(),anyInt(),anyInt()))
                .thenReturn(Utils.storyBook());

        List<Book> bookList = bookService.getFilteredBooks("story",0,3);
        System.out.println("******** => "+ bookList.size());
        this.mockMvc.perform(MockMvcRequestBuilders.get("/book/{type}", bookType))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.size()", is(bookList.size())))
                        .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookName", is("Hundred Years of Solitude")))
        ;

    }


    @Test
    public void testPostBook() throws Exception {

        String bookType = "story";
        String authorId = "1";
        when(bookService.addBook(any(BookDto.class), any(), any()))
                .thenReturn(Utils.prepareMockData().get(0));

        Book book = bookService.addBook(Utils.bookDto(), "story", "1");
        mockMvc.perform(MockMvcRequestBuilders.post("/book/{type}/{id}", bookType, authorId)
                .content(new ObjectMapper().writeValueAsString(book))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.publishedDate").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.bookName").value("Hundred Years of Solitude"));
    }

    @Test
    public void testUpdateBook() throws Exception {

        List<Author> author = Utils.createAuthor();
        Book storyBook = new Story();
        storyBook.setId(14);
        storyBook.setBookName("Hundred Years");
        storyBook.setPublishedDate("1970");
        storyBook.setAuthor(author.get(0));
        ((Story) storyBook).setGenre("fiction");

        String bookId = "14";
        BookDto bookDto = new BookDto();
        bookDto.setPublishedDate("1980");
        bookDto.setBookName("Hundred Years of Solitude");
        storyBook.setBookName(bookDto.getBookName());
        storyBook.setPublishedDate(bookDto.getPublishedDate());

        when(bookService.updateBook(any(BookDto.class), any()))
                .thenReturn(storyBook);

        Book book = bookService.updateBook(Utils.bookDto(),"14");
        mockMvc.perform(MockMvcRequestBuilders.put("/book/{id}",bookId)
                .content(new ObjectMapper().writeValueAsString(bookDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.publishedDate").value("1980"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bookName").value("Hundred Years of Solitude"));
    }

    @Test
    public void testDelete()
    {
        when(bookService.deleteBook("4")).thenReturn((Utils.singleBook()));
        Book book = bookService.deleteBook("4");
        assertEquals("Hundred Years of Solitude",book.getBookName());
        verify(bookService).deleteBook("4");
    }

}