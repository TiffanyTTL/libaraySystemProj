package com.example.LibraryManagementProject.repository;

import com.example.LibraryManagementProject.model.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Admin repository class.
 */
@Repository
public interface AdminRepository extends MongoRepository<Admin, String> {
}
