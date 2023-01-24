package com.example.LibraryManagementProject.service;

import com.example.LibraryManagementProject.model.Book;
import com.example.LibraryManagementProject.repository.BookRepository;
import com.example.LibraryManagementProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for admin.
 */
@Service
public class AdminService {

  @Autowired
  private BookRepository bookRepository;

  @Autowired
  private UserRepository userRepository;


  public AdminService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }


  /**
   * create new book method
   */
  public Book createNewBook(Book book) {
    bookRepository.insert(book);
    return book;
  }

  /**
   * delete book method
   */
  public String deleteBook(int bookISBN) {
    bookRepository.deleteBookByBookISBN(bookISBN);
    return "Deleted";
  }
}

