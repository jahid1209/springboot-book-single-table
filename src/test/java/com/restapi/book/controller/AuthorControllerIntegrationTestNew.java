package com.restapi.book.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restapi.book.dto.AuthorDto;
import com.restapi.book.model.Author;
import com.restapi.book.service.AuthorService;
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
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(AuthorController.class)
public class AuthorControllerIntegrationTestNew {

    @InjectMocks
    AuthorController authorController;

    @MockBean
    AuthorService authorService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testGetAllAuthors() throws Exception {
        when(authorService.getAllAuthors(anyInt(),anyInt()))
                .thenReturn(Utils.createAuthor());

        List<Author> authorList = authorService.getAllAuthors(0,3);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/author/"))
                .andExpect(status().isOk())
               .andExpect(jsonPath("$.size()", is(authorList.size())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", is("G G Marquez")));

    }

    @Test
    public void testPostAuthor() throws Exception {


        when(authorService.createAuthor(any(AuthorDto.class)))
                .thenReturn(Utils.singleAuthor());

        Author author = authorService.createAuthor(Utils.authorDto());
        mockMvc.perform(MockMvcRequestBuilders.post("/author/")
                .content(new ObjectMapper().writeValueAsString(author))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.dob").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("G G Marquez"));
    }

    @Test
    public void testUpdateAuthor() throws Exception {

        String authorId = "1";
        Author authorOne = new Author();
        authorOne.setDob("1922");
        authorOne.setName("G G Marquez");
        authorOne.setAuthorId(1);


        AuthorDto authorDto = new AuthorDto();
        authorDto.setDob("1930");
        authorDto.setName("G G Marquez");
        authorOne.setDob(authorDto.getDob());
        authorOne.setName(authorDto.getName());

        when(authorService.updateAuthor(any(AuthorDto.class), any()))
                .thenReturn(authorOne);

        Author author = authorService.updateAuthor(authorDto,"1");
        mockMvc.perform(MockMvcRequestBuilders.put("/author/{id}",authorId)
                .content(new ObjectMapper().writeValueAsString(authorDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.dob").value("1930"))

        ;
    }


    @Test
    void testDeleteAuthor() {

        when(authorService.deleteAuthor("1")).thenReturn(java.util.Optional.ofNullable(Utils.singleAuthor()));
        Optional<Author> author = authorService.deleteAuthor("1");
        assertEquals("1922",author.get().getDob());
        verify(authorService).deleteAuthor("1");
    }
}
