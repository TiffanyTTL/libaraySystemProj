package com.example.LibraryManagementProject.repository;

import com.example.LibraryManagementProject.model.BookOrder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Library repository class.
 */
@Repository
  public interface BookOrderRepository extends MongoRepository<BookOrder, Integer> {
    BookOrder findBookOrderByBookISBN(int BookISBN);
}
