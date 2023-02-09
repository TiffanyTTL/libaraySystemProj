package com.example.LibraryManagementProject.service;

import com.example.LibraryManagementProject.model.Book;
import com.example.LibraryManagementProject.repository.BookRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for books.
 */
@Service
public class BookService {

  @Autowired
    private BookRepository bookRepository;

  public BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  /**
   * create new book method.
   */
  public  Book createNewBook(Book book) {
    bookRepository.insert(book);
    return book;

  }

  /**
   * get all books method.
   */
  public List<Book> getAllBooks() {
    return bookRepository.findAll();
  }

  /**
   * get all books by the title method.
   */
  public Book getAllBooksByTitle(String bookTitle) {
    return bookRepository.findBookByBookTitle(bookTitle);
  }

  /**
   * get all books by the author method.
   */
  public List<Book> getAllBooksByAuthor(String bookAuthor) {
    return bookRepository.findBookByBookAuthor(bookAuthor);
  }

  /**
   * get all books by the ISBN method.
   */
  public Book getBookByISBNNumber(int bookISBN) {
    return bookRepository.findBookByBookISBN(bookISBN);
  }
}




