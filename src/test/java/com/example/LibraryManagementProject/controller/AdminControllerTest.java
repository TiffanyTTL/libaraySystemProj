package com.example.LibraryManagementProject.controller;

import com.example.LibraryManagementProject.dto.AdminDto;
import com.example.LibraryManagementProject.model.Book;
import com.example.LibraryManagementProject.model.User;
import com.example.LibraryManagementProject.repository.BookRepository;
import com.example.LibraryManagementProject.requestbody.CheckInBookForUserRequestBody;
import com.example.LibraryManagementProject.requestbody.CheckOutBookForUserRequestBody;
import com.example.LibraryManagementProject.requestbody.FrequentLateReturnersRequestBody;
import com.example.LibraryManagementProject.service.BookService;
import com.example.LibraryManagementProject.service.AdminService;
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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
public class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private BookService bookService;

    @Mock
    private AdminService adminService;

    @InjectMocks
    AdminController librarianController;

    @Mock
    private Book book;

    @Mock
    private RequestAttributes attributes;


    /**
     * set up spring test in stand-alone mode
     */
    @Before
    public void setUp() {
        RequestContextHolder.setRequestAttributes(attributes);
        this.mockMvc = MockMvcBuilders.standaloneSetup(librarianController).build();
    }

    /**
     * test method, that should pass if
     * book is successfully created
     */
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

    /**
     * Test method that should pass if book
     * has been successfully deleted
     */

    @Test
    public void deleteBookSuccess_Test() throws Exception {
        AdminDto adminDto = new AdminDto();
        adminDto.setLibrarianEmailAddress("tiffany@lib.com");
        adminDto.setBookTitle("Cloud-Native Java");
        adminDto.setBookAuthor("Josh Long");
        adminDto.setBookISBN(45013867);
        adminDto.setBookQuantity(9);
        Mockito.when(adminService.deleteBook(45013867)).thenReturn("Book successfully deleted");
        mockMvc.perform(delete("/admin/remove-book")
                        .param("bookISBNNumber", "45013867")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());


    }

    /**
     * Test method that should pass if book
     * has been successfully deleted
     */

    @Test
    @DisplayName("Test Should Pass If Admin Successfully Checked Out Book For User")
    public void checkOutBookForUserSuccess_Test() throws Exception {
        CheckOutBookForUserRequestBody checkOutBookForUserRequestBody = new CheckOutBookForUserRequestBody();
        checkOutBookForUserRequestBody.setAdminEmailAddress("libby@lib.com");
        checkOutBookForUserRequestBody.setUserEmailAddress("lori@lib.com");
        checkOutBookForUserRequestBody.setBookIsbn(45678901);
        Mockito.when(adminService.checkOutBook(checkOutBookForUserRequestBody)).thenReturn("lori@lib.com has borrowed one copy of 45678901!");
        String jsonBody = "{\"adminEmailAddress\":\"libby@lib.com\", " + "\"userEmailAddress\":\"lori@lib.com\", " +
                "\"bookISBN\":\"45678901\"," +
                "\"borrowForDays\":\"7\"}";
        MvcResult result = (MvcResult) mockMvc.perform(post("/admin/checkout")
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        Assert.assertNotNull(content);
        assertEquals("lori@lib.com has borrowed one copy of 45678901!", adminService.checkOutBook(checkOutBookForUserRequestBody));
        System.out.println(content);


    }

    @Test
    @DisplayName("Test Should Pass If Book Has Been Returned to the Library")
    public void bookReturned_SuccessTest() throws Exception {
        CheckInBookForUserRequestBody checkInBookForUserRequestBody = new CheckInBookForUserRequestBody();
        checkInBookForUserRequestBody.setAdminEmailAddress("libby@lib.com");
        checkInBookForUserRequestBody.setUserEmailAddress("lori@lib.com");
        checkInBookForUserRequestBody.setBookIsbn(87654321);
        Mockito.when(adminService.checkInBook(checkInBookForUserRequestBody)).thenReturn("libby@lib.com has returned copy of 87654321!");
        String jsonBody1 = "{\"adminEmailAddress\":\"libby@lib.com\", " + "\"userEmailAddress\":\"lori@lib.com\", " +
                "\"bookISBN\":\"45678901\"}";
        MvcResult result = (MvcResult) mockMvc.perform(put("/admin/checkin")
                        .content(jsonBody1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        Assert.assertNotNull(content);
        assertEquals("libby@lib.com has returned copy of 87654321!", adminService.checkInBook(checkInBookForUserRequestBody));
        System.out.println(content);


    }

//    @Test
//    public void getAllFrequentLateReturners() throws Exception {
//            // created a list of users
//            List<FrequentLateReturnersRequestBody> frequentLateReturnersRequestBodyList = new ArrayList<>();
//            FrequentLateReturnersRequestBody frequentLateReturnersRequestBody = new FrequentLateReturnersRequestBody();
//            frequentLateReturnersRequestBody.setUserEmailAddress("libby@lib.com");
//            frequentLateReturnersRequestBodyList.add(frequentLateReturnersRequestBody);d
//            //when get all books method is called, return the list of books created
//            when(adminService.getAllFrequeentLateReturners()).thenReturn(frequentLateReturnersRequestBodyList);
//            mockMvc.perform(MockMvcRequestBuilders
//                            .get("/addmin/LateReturns")
//                            .contentType(MediaType.APPLICATION_JSON)
//                            .accept(MediaType.APPLICATION_JSON))
//                    .andDo(print())
//                    .andExpect(status().isOk())
//                    .andExpect(MockMvcResultMatchers.jsonPath("$.userEmailAddress").value("libby@lib.com"));
//        }


}


