package com.example.LibraryManagementProject.controller;

import com.example.LibraryManagementProject.model.Book;
import com.example.LibraryManagementProject.service.BookService;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * book controller class.
 */
@Log4j2
@RestController
@RequestMapping("/books")
public class BookController {

  @Autowired
  BookService bookService;

  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  Logger logger = LoggerFactory.getLogger(BookController.class);

  /**
   * post mapping request, used to create new book.
   */
  @PostMapping("/create")
    public Book createBook(@RequestBody Book book) {
    logger.info("Book Created");
    return bookService.createNewBook(book);

  }

  /**
   * Get mapping request, used to get all available book.
   */
  @GetMapping ("/all")
    public List<Book> getAllBooks() {
    logger.info("All Books Returned");
    return bookService.getAllBooks();
  }

  /**
   * Get mapping request, used to get all available book by title.
   */
  @GetMapping("/booktitle/{bookTitle}")
    public Book getAllBooksByTitle(@PathVariable String bookTitle) {
    logger.info("Books By Specified Title Returned");
    return bookService.getAllBooksByTitle(bookTitle);
  }

  /**
   * Get mapping request, used to get all available book by authors name.
   */
  @GetMapping ("/bookauthor/{bookAuthor}")
    public List<Book> getAllBooksByAuthor(@PathVariable String bookAuthor) {
    logger.info("Books By Specified Author Returned");
    return bookService.getAllBooksByAuthor(bookAuthor);
  }

  /**
   * Get mapping request, used to get all available book by its ISBN.
   */
  @GetMapping("/bookisbn/{bookISBN}")
    public Book getAllBooksByIsbnNumber(@PathVariable int bookISBN) {
    logger.info("Book By Specified ISBN Number Returned");
    return bookService.getBookByISBNNumber(bookISBN);
  }
}







