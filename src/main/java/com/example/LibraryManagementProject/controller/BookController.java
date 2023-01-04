package com.example.LibraryManagementProject.controller;

import com.example.LibraryManagementProject.model.Book;
import com.example.LibraryManagementProject.repository.BookRepository;
import com.example.LibraryManagementProject.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookService bookService;



    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/create")
    public Book createBook(@RequestBody Book book) {
        return bookService.createNewBook(book);
    }

    //get all books
    @GetMapping("/all")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    //get all books by the book title
    @GetMapping("/booktitle/{bookTitle}")
    public Book getAllBooksByTitle(@PathVariable String bookTitle) {
        return bookService.getAllBooksByTitle(bookTitle);
    }


    //get all books by the author
    @GetMapping("/bookauthor/{bookAuthor}")
    public List<Book> getAllBooksByAuthor(@PathVariable String bookAuthor) {
        return bookService.getAllBooksByAuthor(bookAuthor);
    }

    @GetMapping("/bookisbn/{bookISBN}")
    public Book getAllBooksByISBNNumber(@PathVariable int bookISBN) {
        return bookService.getBookByISBNNumber(bookISBN);
    }
}







