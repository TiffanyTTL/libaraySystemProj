package com.example.LibraryManagementProject.service;

import com.example.LibraryManagementProject.model.Book;
import com.example.LibraryManagementProject.model.Library;
import com.example.LibraryManagementProject.model.User;
import com.example.LibraryManagementProject.repository.BookRepository;
import com.example.LibraryManagementProject.repository.LibraryRepository;
import com.example.LibraryManagementProject.repository.UserRepository;
import com.example.LibraryManagementProject.requestbody.CheckInBookRequestBody;
import com.example.LibraryManagementProject.requestbody.CheckoutBookRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


/**
 * User service class.
 */

@Service
public class UserService {

  @Autowired
  LibraryRepository libraryRepository;

  @Autowired
  UserRepository userRepository;

  @Autowired
  BookRepository bookRepository;

  /**
   * constructor generated for the user service class.
   */
  public UserService(LibraryRepository libraryRepository, UserRepository userRepository, BookRepository bookRepository) {
    this.libraryRepository = libraryRepository;
    this.userRepository = userRepository;
    this.bookRepository = bookRepository;
  }

  /**
   * create user method class.
   */
  public  String createUser(User user) {
    userRepository.insert(user);
    return "user has been created";

  }

  /**
   * method to allow a user.
   * to check out a book.
   */
  public String checkOutBook(CheckoutBookRequestBody checkoutBookRequestBody) {

    User user = userRepository.findUserByUserEmailAddress(checkoutBookRequestBody.getUserEmailAddress());
    Book book = bookRepository.findBookByBookISBN(checkoutBookRequestBody.getBookIsbn());


    if (book.getBookQuantity() < 1) {
      return "The book \"" + book.getBookTitle() + "\" is out of stock!";
    }
    book.borrowBook();
    bookRepository.save(book);
    LocalDate currentDate = LocalDate.now();
    LocalDate overdueDate = currentDate.plusDays(checkoutBookRequestBody.getBorrowForDays());
    Library library = new Library();
    library.setIssueDate(currentDate);
    library.setDueDate(overdueDate);
    libraryRepository.save(library);
    return user.getUserEmailAddress() + " has borrowed one copy of \"" + book.getBookTitle() + "\"!";
  }


  /**
   * method to allow a user.
   * to check out a book.
   */
  public String checkInBook(CheckInBookRequestBody checkInBookRequestBody) {

    User user = userRepository.findUserByUserEmailAddress(checkInBookRequestBody.getUserEmailAddress());
    Book book = bookRepository.findBookByBookISBN(checkInBookRequestBody.getBookIsbn());

    book.returnBook();
    bookRepository.save(book);
    LocalDate currentDate = LocalDate.now();
    Library library = new Library();
    library.setReturnDate(currentDate);
    libraryRepository.save(library);
    return user.getUserEmailAddress() + " has returned copy of \"" + book.getBookTitle() + "\"!";

  }
  }