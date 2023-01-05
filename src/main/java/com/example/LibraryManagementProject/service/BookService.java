package com.example.LibraryManagementProject.service;

import com.example.LibraryManagementProject.model.Book;
import com.example.LibraryManagementProject.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    //add new book
    public Book createNewBook(Book book) {
        bookRepository.insert(book);
        return book;

    }

    //get all books
    public List<Book> getAllBooks(){
       return bookRepository.findAll();
    }

    //get books by book title
    public Book getAllBooksByTitle(String bookTitle){
        return bookRepository.findBookByBookTitle(bookTitle);
    }

    //get books by author
    public List<Book> getAllBooksByAuthor(String bookAuthor){
        return bookRepository.findBookByBookAuthor(bookAuthor);
    }

    //get books by ISBNNumber
    public Book getBookByISBNNumber(int bookISBN){
        return bookRepository.findBookByBookISBN(bookISBN);
    }


    }




