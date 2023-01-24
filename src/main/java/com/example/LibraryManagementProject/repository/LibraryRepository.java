package com.example.LibraryManagementProject.repository;

import com.example.LibraryManagementProject.model.Library;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Library repository class.
 */
@Repository
public interface LibraryRepository extends MongoRepository<Library, Integer> {

}
