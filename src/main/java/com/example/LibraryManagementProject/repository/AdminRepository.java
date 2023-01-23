package com.example.LibraryManagementProject.repository;

import com.example.LibraryManagementProject.model.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends MongoRepository<Admin, String> {
}
