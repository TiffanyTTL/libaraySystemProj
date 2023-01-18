package com.example.LibraryManagementProject.controller;

import com.example.LibraryManagementProject.model.Book;
import com.example.LibraryManagementProject.repository.BookRepository;
import com.example.LibraryManagementProject.service.BookService;
import com.example.LibraryManagementProject.service.LibrarianService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Librarian controller class.
 */
@RestController
@Log4j2
@RequestMapping("/admin")
public class LibrarianController {


  @Autowired
    LibrarianService librarianService;


  Book book;

  @Autowired
    BookService bookService;

  @Autowired
    BookRepository bookRepository;


  public LibrarianController(LibrarianService librarianService) {
    this.librarianService = librarianService;
  }
  //Logger logger = (Logger) LoggerFactory.getLogger(LibrarianController.class);

  /**
   * Post request method to create/add books to the library.
   */
  @PostMapping("/create")
    public Book createBook(@RequestBody Book book) {
    // logger.info("Book Created");
    return bookService.createNewBook(book);
  }

  /**
   * Delete request method, to remove books from the library.
   */
  @DeleteMapping("/remove-book")
    public String deleteBook(@RequestParam int bookISBNNumber) {
    librarianService.deleteBook(bookISBNNumber);
    return "Book Deleted Successfully";
  }
}
