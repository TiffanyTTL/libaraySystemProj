package com.example.LibraryManagementProject.service;

import com.example.LibraryManagementProject.model.Book;
import com.example.LibraryManagementProject.model.Librarian;
import com.example.LibraryManagementProject.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibrarianService {

    @Autowired
    private BookRepository bookRepository;

    public LibrarianService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    //add new book
    public Book createNewBook(Book book) {
        bookRepository.insert(book);
        return book;

    }

}
