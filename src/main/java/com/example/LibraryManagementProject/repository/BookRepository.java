package com.example.LibraryManagementProject.repository;

import com.example.LibraryManagementProject.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {
    Book findBookByBookTitle (String bookTitle);
    List<Book> findBookByBookAuthor(String bookAuthor);
    Book findBookByBookISBN(int bookISBN);

}
