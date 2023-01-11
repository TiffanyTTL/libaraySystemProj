package com.example.LibraryManagementProject.controller;

import com.example.LibraryManagementProject.model.Book;
import com.example.LibraryManagementProject.service.BookService;
import com.example.LibraryManagementProject.service.LibrarianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class LibrarianController {

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
        return bookService.createNewBook(book);
    }



}
