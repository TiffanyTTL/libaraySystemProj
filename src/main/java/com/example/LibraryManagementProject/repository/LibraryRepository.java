package com.example.LibraryManagementProject.repository;

import com.example.LibraryManagementProject.model.Book;
import com.example.LibraryManagementProject.model.Library;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryRepository extends MongoRepository<Library, Integer> {
  Library findByUserId(int userId);
  Library findByBookId(int bookId);

}
