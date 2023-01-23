package com.example.LibraryManagementProject.controller;

import com.example.LibraryManagementProject.model.Book;
import com.example.LibraryManagementProject.model.Library;
import com.example.LibraryManagementProject.repository.BookRepository;
import com.example.LibraryManagementProject.repository.UserRepository;
import com.example.LibraryManagementProject.service.BookService;
import com.example.LibraryManagementProject.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * User Controller class.
 */
@RestController
@Log4j2
@RequestMapping("/user")
public class UserController {

  @Autowired
  UserService userService;

  UserRepository userRepository;

  Book book;

  @Autowired
  BookService bookService;

  @Autowired
  BookRepository bookRepository;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  Logger logger = LoggerFactory.getLogger(BookController.class);

  /**
   * Put request method to borrow books to the user.
   */
  @PostMapping("/checkout")
  public String checkOutBook(@RequestBody Library library, int borrowForDays) {
    logger.info("Book successfully checked out");
    return userService.checkOutBook(library,borrowForDays);
  }
}

//  @PutMapping("/checkin")
//  public String returnBook(@RequestBody Library library) {
//   return userService.checkInBook(library);
//  }


