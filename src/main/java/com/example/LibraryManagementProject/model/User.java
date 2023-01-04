package com.example.LibraryManagementProject.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("Users")
public class User {

    @Id
    @Indexed(unique = true)
    public String userID;
    @Field(name = "User Email Address")
    public String userEmailAddress;

    public User() {

    }

    public User( String userEmailAddress) {

        this.userEmailAddress = userEmailAddress;
    }

    public String getUserEmailAddress() {
        return userEmailAddress;
    }

    public void setUserEmailAddress(String userEmailAddress) {
        this.userEmailAddress = userEmailAddress;
    }

}
