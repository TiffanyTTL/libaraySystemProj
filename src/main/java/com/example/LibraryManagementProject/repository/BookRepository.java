package com.example.LibraryManagementProject.repository;

import com.example.LibraryManagementProject.model.Book;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Book repository class.
 */
@Repository
public interface BookRepository extends MongoRepository<Book, String> {
  /**
   * find book by book title.
   */
  Book findBookByBookTitle(String bookTitle);

  /**
   * find book by book author.
   */
  List<Book> findBookByBookAuthor(String bookAuthor);

  /**
   * find book by book isbn.
   */
  Book findBookByBookISBN(int bookISBN);

  /**
   * delete book by isbn.
   */
  Book deleteBookByBookISBN(int bookISBN);



}
