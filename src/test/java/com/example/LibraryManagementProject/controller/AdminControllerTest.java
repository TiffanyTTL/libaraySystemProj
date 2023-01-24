package com.example.LibraryManagementProject.controller;

import com.example.LibraryManagementProject.dto.AdminDto;
import com.example.LibraryManagementProject.model.Admin;
import com.example.LibraryManagementProject.model.Book;
import com.example.LibraryManagementProject.repository.AdminRepository;
import com.example.LibraryManagementProject.repository.BookRepository;
import com.example.LibraryManagementProject.service.BookService;
import com.example.LibraryManagementProject.service.AdminService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
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

    /**
     * Test method that should pass if book
     * has been successfully deleted
     */

    @Test
    public void deleteBookSuccess_Test() throws Exception{
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
     * test method, that should pass if
     * book is successfully created
     */
    @Test
    public void testCreateAdmin () {

        Admin admin = new Admin();
        admin.setAdminEmailAddress("libby@lib.com");
        AdminRepository adminRepository = mock(AdminRepository.class);
        when(AdminRepository.insert((Admin) org.mockito.Mockito.any())).thenReturn(admin);

        AdminController adminController = new AdminController(new AdminService(adminRepository));
        Admin admin1 = new Admin();
        admin1.setAdminEmailAddress("libby@lib.com");
        assertSame(admin1, adminController.createAdmin(admin1));
        verify(adminRepository).insert((Admin) org.mockito.Mockito.any());
    }


}
