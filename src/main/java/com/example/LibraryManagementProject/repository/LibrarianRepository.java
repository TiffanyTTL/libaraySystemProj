package com.example.LibraryManagementProject.repository;

import com.example.LibraryManagementProject.model.Librarian;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibrarianRepository extends MongoRepository<Librarian, String> {
}
