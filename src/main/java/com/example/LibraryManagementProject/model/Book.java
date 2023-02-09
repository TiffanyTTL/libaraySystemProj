package com.example.LibraryManagementProject.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Book model class.
 */

@Document("Books")
public class Book {

  /**
   * ID field.
   */
  @Id
  String bookID;
  /**
   * Book title field.
   */
  @Field(name = "Book Title")
  String bookTitle;
  /**
   * Book author field.
   */
  @Field(name = "Book Author")
  String bookAuthor;
  /**
   * Book Isbn field.
   */
  @Field(name = "ISBN Number")
  int bookISBN;
  /**
   * Book quantity field.
   */
  @Field(name = "Quantity")
  int bookQuantity;


  /**
   * book constructor.
   */
  public Book(String bookTitle, String bookAuthor, int bookISBN, int bookQuantity) {
    this.bookTitle = bookTitle;
    this.bookAuthor = bookAuthor;
    this.bookISBN = bookISBN;
    this.bookQuantity = bookQuantity;
  }

  public Book() {

  }

  public String getBookTitle() {
    return bookTitle;
  }

  public void setBookTitle(String bookTitle) {
    this.bookTitle = bookTitle;
  }

  public String getBookAuthor() {
    return bookAuthor;
  }

  public void setBookAuthor(String bookAuthor) {
    this.bookAuthor = bookAuthor;
  }

  public int getBookISBN() {
    return bookISBN;
  }

  public void setBookISBN(int bookISBN) {
    this.bookISBN = bookISBN;
  }

  public int getBookQuantity() {
    return bookQuantity;
  }

  public void setBookQuantity(int bookQuantity) {
    this.bookQuantity = bookQuantity;
  }

  public void borrowBook() {
    this.bookQuantity--;
  }

  public void returnBook() {
    this.bookQuantity++;
  }

  public void returnDate() {
    this.bookQuantity++;
  }


  @Override
    public String toString() {
    return "Book{"
                +
                ", bookTitle='" + bookTitle + '\''
                +
                ", bookAuthor='" + bookAuthor + '\''
                +
                ", bookISBN=" + bookISBN
                +
                ", bookQuantity=" + bookQuantity
                +
                '}';
  }
}
