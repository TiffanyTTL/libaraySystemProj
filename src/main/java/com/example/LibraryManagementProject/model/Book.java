package com.example.LibraryManagementProject.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document("Books")
public class Book {

    @Id
    String bookID;
    @Field(name = "Book Title")
    String bookTitle;
    @Field(name = "Book Author")
    String bookAuthor;
    @Field( name = "ISBN Number")
    int bookISBN;
    @Field (name = "Quantity")
    int bookQuantity;


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

    @Override
    public String toString() {
        return "Book{" +
                ", bookTitle='" + bookTitle + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", bookISBN=" + bookISBN +
                ", bookQuantity=" + bookQuantity +
                '}';
    }

}
