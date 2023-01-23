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


//  public Library checkOutBooksToUsers(String userEmailAddress, int bookIsbnNumber)
//  {
//    Book borrowedBook = bookRepository.findBookByBookISBN(bookIsbnNumber);
//    User borrowedUser = userRepository.findUserByUserEmailAddress(userEmailAddress);
//
//    Library checkOutBookToUser = new Library();
//    checkOutBookToUser.setBorrower(borrowedUser.getUsername());
//    checkOutBookToUser.setBookIssued(borrowedBook.getBookName());
//    checkOutBookToUser.setBorrowDate(LocalDate.now());
//    checkOutBookToUser.setReturnDate(LocalDate.now().plusDays(7));
//    checkOutBookToUser.setStatus("Not Avaiable");
//    checkOutBookToUser.setBookIsbnNumber(borrowedBook.getBookISBN());
//    libraryRepoistory.save(bookLendedToUser);
//
//    return bookLendedToUser;
//
//}
//
//  public Library returnBooksToLib(String userEmailAddress, int bookIsbnNumber) {
//
//    Library lendedBook = this.libraryRepo.findById(bookId)
//            .orElseThrow(() -> new ResourceNotFoundException("BookId", " Id ", bookId));
//
//    Library bookReturnedByUser = new Library();
//
//    bookReturnedByUser.setBookid(lendedBook.getBookid());
//    bookReturnedByUser.setBorrower(lendedBook.getBorrower());
//    bookReturnedByUser.setBookIssued(lendedBook.getBookIssued());
//    bookReturnedByUser.setBorrowDate(lendedBook.getBorrowDate());
//    bookReturnedByUser.setReturnDate(lendedBook.getReturnDate());
//    bookReturnedByUser.setStatus("Available");
//
//
//    return bookReturnedByUser;
//  }



