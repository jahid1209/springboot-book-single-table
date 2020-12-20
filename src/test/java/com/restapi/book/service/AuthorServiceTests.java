package com.restapi.book.service;

import com.restapi.book.model.Author;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
class AuthorServiceTests {

    @InjectMocks
    private AuthorService authorService;


    @Mock
    private AuthorRepository authorRepository;

    @Test
    public void getAllAuthors(){
        Pageable paging = PageRequest.of(0, 3);
        List<Author> authorsList = Utils.createAuthor();
        Page<Author> pagedResult = new PageImpl<>(authorsList);
        when(authorRepository.findAll(paging)).thenReturn(pagedResult);
        List<Author> authors = authorService.getAllAuthors(0,3);
        assertNotNull(authors);
        assertEquals("G G Marquez",authors.get(0).getName());

        assertEquals(3,authors.size());

    }

    @Test
    public void testAddAuthor() throws Exception {

        when(authorRepository.save(any(Author.class))).thenReturn(Utils.createAuthor().get(0));

        Author author = authorService.createAuthor( Utils.authorDto());
        assertNotNull(author);
        assertEquals("1922",author.getDob());
    }

    @Test
    void testDeleteAuthor() {

        when(authorRepository.findById(1))
                .thenReturn(java.util.Optional.ofNullable(Utils.createAuthor().get(0)));
        authorService.deleteAuthor("1");
        verify(authorRepository).deleteById(1);
    }
}
