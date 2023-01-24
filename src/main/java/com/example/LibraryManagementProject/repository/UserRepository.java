package com.example.LibraryManagementProject.repository;

import com.example.LibraryManagementProject.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * User repository class.
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {

  User findUserByUserEmailAddress(String userEmailAddress);
}
