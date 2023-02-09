package com.example.LibraryManagementProject.controller;

import com.example.LibraryManagementProject.model.Book;
import com.example.LibraryManagementProject.repository.BookRepository;
import com.example.LibraryManagementProject.service.BookService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private BookService bookService;

    @Mock
    private Book book;

    @MockBean
    private BookRepository bookRepository;

    @InjectMocks
    private BookController bookController;

    @Mock
    private RequestAttributes attributes;


    //set up spring test in stand alone mode
    @Before
    public void setUp() {
        RequestContextHolder.setRequestAttributes(attributes);
        this.mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }


    //get all book test
    @Test
    public void getAllBooksSuccessTest() throws Exception {
        // created a list of book
        List<Book> bookList = new ArrayList<>();
        //created a new book object
        Book book = new Book();
        book.setBookTitle("Clean Code");
        book.setBookAuthor("Craig Walls");
        book.setBookISBN(9369722);
        book.setBookQuantity(2);
        //added the new book object with the new fields to the list
        bookList.add(book);
        //when get all books method is called, return the list of books created
        when(bookService.getAllBooks()).thenReturn(bookList);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/books/all")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookTitle").value("Clean Code"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookAuthor").value("Craig Walls"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookISBN").value("9369722"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookQuantity").value("2"));
    }

    @Test
    public void getAllBooksByTitleSuccessTest() throws Exception {
        //created a new book object called booktitle
        Book bookTitle = new Book();
        bookTitle.setBookTitle("Clean Code");
        bookTitle.setBookAuthor("Craig Walls");
        bookTitle.setBookISBN(9369722);
        bookTitle.setBookQuantity(2);
        //when get all books by title method is called, return the new object booktitle
        when(bookService.getAllBooksByTitle(any())).thenReturn(bookTitle);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/books/booktitle/Clean Code")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.bookTitle").value("Clean Code"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bookAuthor").value("Craig Walls"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bookISBN").value("9369722"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bookQuantity").value("2"));
    }


    @Test
    public void getAllBooksByISBNNumberTest() throws Exception {
        Book bookISBN = new Book();
        bookISBN.setBookTitle("Clean Code");
        bookISBN.setBookAuthor("Craig Walls");
        bookISBN.setBookISBN(9369722);
        bookISBN.setBookQuantity(2);
        when(bookService.getBookByISBNNumber(anyInt())).thenReturn(bookISBN);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/books/bookisbn/9369722")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.bookTitle").value("Clean Code"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bookAuthor").value("Craig Walls"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bookISBN").value("9369722"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bookQuantity").value("2"));
    }

    @Test
    public void testCreateBook() {


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
}


