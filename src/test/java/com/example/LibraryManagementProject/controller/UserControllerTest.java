//package com.example.LibraryManagementProject.controller;
//
//import com.example.LibraryManagementProject.model.Book;
//import com.example.LibraryManagementProject.repository.BookRepository;
//import com.example.LibraryManagementProject.service.BookService;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.request.RequestAttributes;
//import org.springframework.web.context.request.RequestContextHolder;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//public class UserControllerTest {
//
//    @MockBean
//    private BookRepository bookRepository;
//
//    @Mock
//    private Book book;
//
//    @MockBean
//    private UserController userController;
//
//    @Autowired
//    private BookService bookService;
//
//    @Mock
//    private RequestAttributes attributes;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Before
//    public void setUp() {
//        RequestContextHolder.setRequestAttributes(attributes);
//        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
//    }
//
//    @Test
//    @DisplayName("Test Should Pass If All Books Are Returned")
//    public void getAllBooks_SuccessTest() throws Exception {
//        List<Book> booksList = new ArrayList<>();
//        Book book = new Book();
//        book.setBookAuthor("Effective Java");
//        book.setBookAuthor("Joshua Bloch");
//        book.setBookAuthor("34678354");
//        booksList.add(book);
//        when(bookService.getAllBooks()).thenReturn(booksList);
//        mockMvc.perform(MockMvcRequestBuilders
//                        .get("/books/all")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookTitle").value("Effective Java"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookAuthor").value("Joshua Bloch"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookISBN").value("34678354"));
//
//    }
//
//    @Test
//    @DisplayName("Test Should Pass If A Specific Book Is Returned By Its Title")
//    public void getAllBooks_ByTitleTest() throws Exception {
//    }
//
//    @Test
//    @DisplayName("Test Should Pass If All Books Are Returned By Named Author")
//    public void getAllBooks_ByAuthorTest() throws Exception {
//
//    }
//
//}
