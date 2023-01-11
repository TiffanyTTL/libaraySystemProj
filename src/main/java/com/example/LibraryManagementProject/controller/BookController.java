package com.example.LibraryManagementProject.controller;

import com.example.LibraryManagementProject.model.Book;
import com.example.LibraryManagementProject.repository.BookRepository;
import com.example.LibraryManagementProject.service.BookService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/books")
public class BookController {

    Logger logger = (Logger) LoggerFactory.getLogger(BookController.class);

    @Autowired
    BookService bookService;


    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/create")
    public Book createBook(@RequestBody Book book) {
        logger.info("Book Created");
        return bookService.createNewBook(book);

    }

    //get all books
    @GetMapping("/all")
    public List<Book> getAllBooks() {
        logger.info("All Books Returned");
        return bookService.getAllBooks();
    }

    //get all books by the book title
    @GetMapping("/booktitle/{bookTitle}")
    public Book getAllBooksByTitle(@PathVariable String bookTitle) {
        logger.info("Books By Specified Title Returned");
        return bookService.getAllBooksByTitle(bookTitle);
    }


    //get all books by the author
    @GetMapping("/bookauthor/{bookAuthor}")
    public List<Book> getAllBooksByAuthor(@PathVariable String bookAuthor) {
        logger.info("Books By Specified Author Returned");
        return bookService.getAllBooksByAuthor(bookAuthor);
    }

    @GetMapping("/bookisbn/{bookISBN}")
    public Book getAllBooksByISBNNumber(@PathVariable int bookISBN) {
        logger.info("Book By Specified ISBN Number Returned");
        return bookService.getBookByISBNNumber(bookISBN);
    }
}







