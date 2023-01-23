package com.example.LibraryManagementProject.controller;

import com.example.LibraryManagementProject.model.Book;
import com.example.LibraryManagementProject.model.Library;
import com.example.LibraryManagementProject.model.User;
import com.example.LibraryManagementProject.repository.BookRepository;
import com.example.LibraryManagementProject.repository.LibraryRepository;
import com.example.LibraryManagementProject.repository.UserRepository;
import com.example.LibraryManagementProject.service.BookService;
import com.example.LibraryManagementProject.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
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
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private LibraryRepository libraryRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private Book book;

    @MockBean
    private UserController userController;

    @Autowired
    private UserService userService;

    @Mock
    private RequestAttributes attributes;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        RequestContextHolder.setRequestAttributes(attributes);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    @DisplayName("Test Should Pass If Book Has Been Checked out successfully Library")
    public void bookCheckOut_SuccessTest() throws Exception {
        Library library = new Library();
        library.setUserEmailAddress("tiff@lib.com");
        library.setBookISBN(12345678);
        library.setIssueDate(LocalDate.parse("2022-09-21"));
        library.setDueDate(LocalDate.parse("2022-10-01"));
        LibraryRepository libraryRepository = mock(LibraryRepository.class);
        when(libraryRepository.insert((Library) org.mockito.Mockito.any()))
                .thenReturn(library);
        UserController userController = new UserController(new UserService(libraryRepository));
        Library library1 = new Library();
        library1.setUserEmailAddress("tiff@lib.com");
        library1.setBookISBN(12345678);
        library.setIssueDate(LocalDate.parse("2022-09-21"));
        library1.setDueDate(LocalDate.parse("2022-10-01"));
        assertSame(library1, userController.checkOutBook(library1));
        verify(libraryRepository).insert((Library) org.mockito.Mockito.any());

        }



//    @Test
//    @DisplayName("Test Should Pass If Book Has Been Returned to the Library")
//    public void bookReturned_SuccessTest() throws Exception {
//        doNothing().when(userService).checkInBook("tiffany@lib.com", 12345678);
//        mockMvc.perform(MockMvcRequestBuilders
//                .post("/user/checkin")
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON));
//
//    }
//


}


