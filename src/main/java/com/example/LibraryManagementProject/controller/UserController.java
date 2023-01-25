package com.example.LibraryManagementProject.controller;

import com.example.LibraryManagementProject.model.User;
import com.example.LibraryManagementProject.repository.UserRepository;
import com.example.LibraryManagementProject.requestbody.CheckInBookRequestBody;
import com.example.LibraryManagementProject.requestbody.CheckoutBookRequestBody;
import com.example.LibraryManagementProject.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * User Controller class.
 */
@RestController
@Log4j2
@RequestMapping("/user")
public class UserController {


  @Autowired
  private final UserService userService;


  @Autowired
  UserRepository userRepository;


  public UserController(UserService userService) {
    this.userService = userService;
  }

  Logger logger = LoggerFactory.getLogger(BookController.class);

  /**
   * Post request method to borrow books to the user.
   */
  @PostMapping("/checkout")
  @ResponseStatus(HttpStatus.CREATED)
  public String checkOutBook(@RequestBody CheckoutBookRequestBody checkoutBookRequestBody) {
    logger.info("Book successfully checked out");
    return userService.checkOutBook(checkoutBookRequestBody);
  }

  /**
   * Post request method to create a user.
   */
  @PostMapping("/createUser")
  public String createUser(@RequestBody User user) {
    logger.info("User Created");
    return userService.createUser(user);

  }

  @PutMapping("/checkin")
  @ResponseStatus(HttpStatus.CREATED)
  public String checkInBook(@RequestBody CheckInBookRequestBody checkInBookRequestBody) {
    logger.info("Book successfully checked in");
    return userService.checkInBook(checkInBookRequestBody);
  }
}




