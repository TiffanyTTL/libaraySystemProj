package com.example.LibraryManagementProject.controller;

import com.example.LibraryManagementProject.model.Book;
import com.example.LibraryManagementProject.repository.BookRepository;
import com.example.LibraryManagementProject.service.BookService;
import com.example.LibraryManagementProject.service.LibrarianService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import com.example.LibraryManagementProject.dto.BookDto;

import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LibrarianControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private BookService bookService;

    @Mock
    private LibrarianService librarianService;

    @InjectMocks
    LibrarianController librarianController;

    @Mock
    private Book book;

    @Mock
    private RequestAttributes attributes;



    //set up spring test in stand-alone mode
    @Before
    public void setUp() {
        RequestContextHolder.setRequestAttributes(attributes);
        this.mockMvc = MockMvcBuilders.standaloneSetup(librarianController).build();
    }

    @Test
    public void testCreateBook () {


        Book book = new Book();
        book.setBookAuthor("Josh Long");
        book.setBookISBN(45013867);
        book.setBookQuantity(1);
        book.setBookTitle("Cloud-Native Java");
        BookRepository bookRepository = mock(BookRepository.class);
        when(bookRepository.insert((Book) org.mockito.Mockito.any())).thenReturn(book);
        BookController bookController = new BookController(new BookService(bookRepository));

        Book book1 = new Book();
        book1.setBookAuthor("Josh Long");
        book1.setBookISBN(45013867);
        book1.setBookQuantity(1);
        book1.setBookTitle("Cloud-Native Java");
        assertSame(book1, bookController.createBook(book1));
        verify(bookRepository).insert((Book) org.mockito.Mockito.any());
    }

    @Test
    public void deleteBookSuccess_Test() throws Exception{
        BookDto bookDto = new BookDto();
        bookDto.setBookTitle("Cloud-Native Java");
        bookDto.setBookAuthor("Josh Long");
        bookDto.setBookISBN(45013867);
        bookDto.setBookQuantity(9);
        Mockito.when(librarianService.deleteBook(700886452)).thenReturn("Book successfully deleted");
                mockMvc.perform(delete("/admin/remove-book")
                                .param("bookISBNNumber", "700886452")
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(status().isOk());


    }


}
