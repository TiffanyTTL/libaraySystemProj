package com.example.LibraryManagementProject.controller;

import com.example.LibraryManagementProject.model.Book;
import com.example.LibraryManagementProject.repository.BookRepository;
import com.example.LibraryManagementProject.service.AdminService;
import com.example.LibraryManagementProject.service.BookService;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Librarian controller class.
 */
@RestController
@Log4j2
@RequestMapping("/admin")
public class AdminController {


  @Autowired
  AdminService adminService;


  Book book;

  @Autowired
    BookService bookService;

  @Autowired
    BookRepository bookRepository;


  public AdminController(AdminService adminService) {
    this.adminService = adminService;
  }

  Logger logger = LoggerFactory.getLogger(BookController.class);

  /**
   * Post request method to create/add books to the library.
   */
  @PostMapping("/create")
    public Book createBook(@RequestBody Book book) {
    logger.info("Book Created");
    return bookService.createNewBook(book);
  }

  /**
   * Delete request method, to remove books from the library.
   */
  @DeleteMapping("/remove-book")
    public String deleteBook(@RequestParam int bookISBNNumber) {
    adminService.deleteBook(bookISBNNumber);
    logger.info("Book Deleted");
    return "Book Deleted Successfully";
  }
}
