package com.example.LibraryManagementProject.controller;

import com.example.LibraryManagementProject.model.Book;
import com.example.LibraryManagementProject.repository.BookRepository;
import com.example.LibraryManagementProject.service.BookService;
import com.example.LibraryManagementProject.service.LibrarianService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
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
//    Logger logger = (Logger) LoggerFactory.getLogger(LibrarianController.class);

    @PostMapping("/create")
    public Book createBook(@RequestBody Book book) {
//        logger.info("Book Created");
        return bookService.createNewBook(book);
    }

    @DeleteMapping("/remove-book")
    public String deleteBook(@RequestParam int bookISBNNumber) {
        librarianService.deleteBook(bookISBNNumber);
//  logger.info("Book Deleted");
        return "redirect:/admin/remove-books";
    }





}
