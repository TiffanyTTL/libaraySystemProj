package com.example.LibraryManagementProject.service;

import com.example.LibraryManagementProject.model.Book;
import com.example.LibraryManagementProject.model.Library;
import com.example.LibraryManagementProject.model.User;
import com.example.LibraryManagementProject.repository.BookRepository;
import com.example.LibraryManagementProject.repository.LibraryRepository;
import com.example.LibraryManagementProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

/**
 * User service class.
 */

@Service
public class UserService {

  LibraryRepository libraryRepository;

  UserRepository userRepository;

  BookRepository bookRepository;

  Library library;

  User user;


  public UserService(LibraryRepository libraryRepository) {
    this.libraryRepository = libraryRepository;
  }


  /**
   * method to allow a user.
   * to check out a book.
   */
  public String checkOutBook(Library library, int borrowForDays) {

    User user = userRepository.findUserByUserEmailAddress(library.getUserEmailAddress());
    Book book = bookRepository.findBookByBookISBN(library.getBookISBN());


    if (book.getBookQuantity() < 1) {
      return "The book \"" + book.getBookTitle() + "\" is out of stock!";
    }
    book.borrowBook();
    bookRepository.save(book);
    LocalDate currentDate = LocalDate.now();
    LocalDate overdueDate = currentDate.plusDays(borrowForDays);
    library.setIssueDate(currentDate);
    library.setDueDate(overdueDate);
    libraryRepository.save(library);
    return user.getUserEmailAddress() + " has borrowed one copy of \"" + book.getBookTitle() + "\"!";
  }
}
//  /**
//   * method to allow a user.
//   * to check in a book.
//   */
//  public boolean checkInBook(Library library) {
//
//    Library library1 = libraryRepository.findById(library.getBorrowId()).get();
//    Books book = libraryRepository.findById(library.getBookISBN()).get();
//
//    book.returnBook();
//    booksRepository.save(book);
//
//    Date currentDate = new Date();
//    borrowBook.setReturnDate(currentDate);
//    return borrowRepository.save(borrowBook);
//
//  }
//}



