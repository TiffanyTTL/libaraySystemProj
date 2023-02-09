package com.example.LibraryManagementProject.service;

import com.example.LibraryManagementProject.model.Admin;
import com.example.LibraryManagementProject.model.Book;
import com.example.LibraryManagementProject.model.BookOrder;
import com.example.LibraryManagementProject.model.User;
import com.example.LibraryManagementProject.repository.AdminRepository;
import com.example.LibraryManagementProject.repository.BookOrderRepository;
import com.example.LibraryManagementProject.repository.BookRepository;
import com.example.LibraryManagementProject.repository.UserRepository;
import com.example.LibraryManagementProject.requestbody.CheckInBookForUserRequestBody;
import com.example.LibraryManagementProject.requestbody.CheckOutBookForUserRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;
import org.springframework.stereotype.Service;


/**
 * Service class for admin.
 */
@Service
public class AdminService {


  @Autowired
  private BookRepository bookRepository;

  @Autowired
  private AdminRepository adminRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private BookOrderRepository bookOrderRepository;


  /**
   * constructor for admin service.
   */
  public AdminService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }


  /**
   * create new book method.
   */
  public Book createNewBook(Book book) {
    bookRepository.insert(book);
    return book;
  }

  /**
   * delete book method.
   */
  public String deleteBook(int bookISBN) {
    bookRepository.deleteBookByBookISBN(bookISBN);
    return "Deleted";
  }

  /**
   * create new book method.
   */
  public String createNewAdmin(Admin admin) {
    adminRepository.insert(admin);
    return "admin has been created";
  }

  /**
   * method to allow admin.
   * to check out a book on behalf of the user.
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
    BookOrder bookOrder = new BookOrder();
    bookOrder.setIssueDate(currentDate);
    bookOrder.setDueDate(overdueDate);
    bookOrderRepository.save(bookOrder);
    return user.getUserEmailAddress() + " has borrowed one copy of \"" + book.getBookTitle() + "\"!";
  }

  /**
   * method to allow admin.
   * to check in a book on behalf of the user.
   */
  public String checkInBook(CheckInBookForUserRequestBody checkInBookForUserRequestBody) {

    User user = userRepository.findUserByUserEmailAddress(checkInBookForUserRequestBody.getUserEmailAddress());
    Admin admin = adminRepository.findAdminByAdminEmailAddress(checkInBookForUserRequestBody.getAdminEmailAddress());
    Book book = bookRepository.findBookByBookISBN(checkInBookForUserRequestBody.getBookIsbn());

    book.returnBook();
    bookRepository.save(book);
    LocalDate currentDate = LocalDate.now();
    BookOrder bookOrder = new BookOrder();
    bookOrder.setActualReturnedDate(currentDate);
    bookOrderRepository.save(bookOrder);
    return user.getUserEmailAddress() + " has returned copy of \"" + book.getBookTitle() + "\"!";

  }






}

