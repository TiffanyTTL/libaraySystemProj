package com.example.LibraryManagementProject.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("Librarian")
public class Librarian {

    @Id
    @Indexed(unique = true)
    public String librarianID;
    @Field(name = "Librarian Email Address")
    public String librarianEmailAddress;


    public Librarian() {

    }

    public Librarian(String librarianEmailAddress) {
        this.librarianEmailAddress = librarianEmailAddress;
    }


    public String getLibrarianEmailAddress() {
        return librarianEmailAddress;
    }

    public void setLibrarianEmailAddress(String librarianEmailAddress) {
        this.librarianEmailAddress = librarianEmailAddress;
    }

}
