package com.example.LibraryManagementProject.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Admin Model class.
 */
@Document("Admin")
public class Admin {

  @Id
  @Indexed(unique = true)
  public String adminId;
  @Field(name = "Admin Email Address")
  public String adminEmailAddress;


  public Admin() {

  }

  public Admin(String adminEmailAddress) {
    this.adminEmailAddress = adminEmailAddress;
  }


  public String getAdminEmailAddress() {
    return adminEmailAddress;
  }

  public void setAdminEmailAddress(String adminEmailAddress) {
    this.adminEmailAddress = adminEmailAddress;
  }

}
