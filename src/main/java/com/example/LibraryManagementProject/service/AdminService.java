package com.example.LibraryManagementProject.service;

import com.example.LibraryManagementProject.model.Admin;
import com.example.LibraryManagementProject.model.Book;
import com.example.LibraryManagementProject.model.Library;
import com.example.LibraryManagementProject.model.User;
import com.example.LibraryManagementProject.repository.AdminRepository;
import com.example.LibraryManagementProject.repository.BookRepository;
import com.example.LibraryManagementProject.repository.LibraryRepository;
import com.example.LibraryManagementProject.repository.UserRepository;
import com.example.LibraryManagementProject.requestbody.CheckOutBookForUserRequestBody;
import com.example.LibraryManagementProject.requestbody.CheckoutBookRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * Service class for admin.
 */
@Service
public class AdminService {

  @Autowired
  private BookRepository bookRepository;

  @Autowired
  AdminRepository adminRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  LibraryRepository libraryRepository;


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

  /**
   * create new book method
   */
  public String createNewAdmin(Admin admin) {
    adminRepository.insert(admin);
    return "admin has been created";
  }

  /**
   * method to allow a user.
   * to check out a book.
   */
  public String checkOutBook(CheckOutBookForUserRequestBody checkOutBookForUserRequestBody) {

    User user = userRepository.findUserByUserEmailAddress(checkOutBookForUserRequestBody.getUserEmailAddress());
    Admin admin = adminRepository.findAdminByAdminEmailAddress(checkOutBookForUserRequestBody.getAdminEmailAddress());
    Book book = bookRepository.findBookByBookISBN(checkOutBookForUserRequestBody.getBookIsbn());


      if (book.getBookQuantity() < 1) {
        return "The book \"" + book.getBookTitle() + "\" is out of stock!";
    }
    book.borrowBook();
    bookRepository.save(book);
    LocalDate currentDate = LocalDate.now();
    LocalDate overdueDate = currentDate.plusDays(checkOutBookForUserRequestBody.getBorrowForDays());
    Library library = new Library();
    library.setIssueDate(currentDate);
    library.setDueDate(overdueDate);
    libraryRepository.save(library);
    return user.getUserEmailAddress() + " has borrowed one copy of \"" + book.getBookTitle() + "\"!";
  }


}

