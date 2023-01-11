package com.example.LibraryManagementProject.controller;

import com.example.LibraryManagementProject.model.Book;
import com.example.LibraryManagementProject.service.BookService;
import com.example.LibraryManagementProject.service.LibrarianService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/admin")
public class LibrarianController {

    Logger logger = (Logger) LoggerFactory.getLogger(LibrarianController.class);

    @Autowired
    LibrarianService librarianService;

    @Autowired
    Book book;

    @Autowired
    BookService bookService;

    public LibrarianController(LibrarianService librarianService) {
        this.librarianService = librarianService;
    }

    @PostMapping("/create")
    public Book createBook(@RequestBody Book book) {
        logger.info("Book Created");
        return bookService.createNewBook(book);
    }

    @DeleteMapping("/remove-book/{id}")
    public String deleteBook(@PathVariable("id") Long id, Book book) {
        bookService.deleteBook(id);

        book.addAttribute("book", bookService.getAllBooks());
        logger.info("Book Deleted");
        return "redirect:/books";
    }



}
