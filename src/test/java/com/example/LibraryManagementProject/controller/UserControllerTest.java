package com.example.LibraryManagementProject.controller;

import com.example.LibraryManagementProject.model.Book;
import com.example.LibraryManagementProject.model.Library;
import com.example.LibraryManagementProject.model.User;
import com.example.LibraryManagementProject.repository.BookRepository;
import com.example.LibraryManagementProject.repository.LibraryRepository;
import com.example.LibraryManagementProject.repository.UserRepository;
import com.example.LibraryManagementProject.requestbody.CheckInBookRequestBody;
import com.example.LibraryManagementProject.requestbody.CheckoutBookRequestBody;
import com.example.LibraryManagementProject.service.BookService;
import com.example.LibraryManagementProject.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.sql.Date;
import java.text.DateFormat;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
@ContextConfiguration
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Mock
    UserService userService;

    @Mock
    User user;

    @Mock
    Book book;

    @Mock
    BookRepository bookRepository;

    @Mock
    UserRepository userRepository;

    @MockBean
    LibraryRepository libraryRepository;

    @MockBean
    UserController userController;

    @Mock
    RequestAttributes attributes;


    @Before
    public void setUp() {
        RequestContextHolder.setRequestAttributes(attributes);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    /**
     * book checked out successfully test
     */
    @Test
    @DisplayName("Test Should Pass If Book Has Been Checked out successfully Library")
    public void bookCheckOut_SuccessTest() throws Exception {
        //create new object of checkOutBookRequest
        CheckoutBookRequestBody checkoutBookRequestBody = new CheckoutBookRequestBody();
        //setting the fields
        checkoutBookRequestBody.setUserEmailAddress("tiff@lib.com");
        checkoutBookRequestBody.setBookIsbn(12345678);
        //mock when checkoutbook method is called, this should return
        Mockito.when(userService.checkOutBook(checkoutBookRequestBody)).thenReturn("tiff@lib.com has borrowed one copy of 12345678!");
        //what the json body should look like
        String jsonBody = "{\"userEmailAddress\":\"tiff@lib.com\", " +
                "\"bookISBN\":\"12345678\"," +
                "\"borrowForDays\":\"7\"}";
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/user/checkout")
                                .content(jsonBody)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }


    @Test
    @DisplayName("Test Should Pass If Book Has Been Returned to the Library")
    public void bookReturned_SuccessTest() throws Exception {
        CheckInBookRequestBody checkInBookRequestBody = new CheckInBookRequestBody();
        checkInBookRequestBody.setUserEmailAddress("libby@lib.com");
        checkInBookRequestBody.setBookIsbn(87654321);
        Mockito.when(userService.CheckInBook(checkInBookRequestBody)).thenReturn("libby@lib.com has returned copy of 12345678!");
        String jsonBody1 = "{\"userEmailAddress\":\"libby@lib.com\", " +
                "\"bookISBN\":\"87654321\"";
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .put("/user/checkin")
                                .content(jsonBody1)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());


    }
}





