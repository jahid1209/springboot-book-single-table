package com.restapi.book.service;

import com.restapi.book.model.Book;
import com.restapi.book.repository.AuthorRepository;
import com.restapi.book.repository.BookRepository;
import com.restapi.book.repository.FilteredListDao;
import com.restapi.book.utils.Utils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class BookServiceTests {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;
    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private FilteredListDao filteredListDao;

    @Test
    public void testGetAllBooks()
    {

        when(bookRepository.findAll(ArgumentMatchers.isA(Pageable.class)))
                .thenReturn(new PageImpl<>(Utils.prepareMockData()));

        List<Book> books = bookService.getAllBooks(0,3);
        assertNotNull(books);
        assertEquals("ANN",books.get(1).getBookName());

        assertEquals(3,books.size());


    }

    @Test
    public void testFilteredBooksList()
    {
        when(filteredListDao.getFilteredBooksByType("story"))
                .thenReturn(Utils.storyBook());
        List<Book> books = bookService.getFilteredBooks("story",0,3);
        assertNotNull(books);
        assertEquals("Hundred Years of Solitude",books.get(0).getBookName());
        assertEquals(1,books.size());
    }


    @Test
    public void testAddBook() throws Exception {

        when( authorRepository.findById(anyInt())).thenReturn(java.util.Optional.ofNullable(Utils.singleAuthor()));

        when(bookRepository.save(any(Book.class))).thenReturn(Utils.singleBook());

        Book book = bookService.addBook( Utils.bookDto(),"story","1");
        assertNotNull(book);
        assertEquals("Hundred Years of Solitude",book.getBookName());
    }

    @Test
    void testDeleteBook() {

        when(bookRepository.findById(4)).thenReturn(java.util.Optional.ofNullable(Utils.singleBook()));
        bookService.deleteBook("4");
        verify(bookRepository).deleteById(4);
    }


}
