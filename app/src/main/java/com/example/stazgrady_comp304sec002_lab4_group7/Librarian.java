package com.example.stazgrady_comp304sec002_lab4_group7;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "librarian_table")
public class Librarian {

    @PrimaryKey(autoGenerate = false)
    private int librarianId;

    private String firstName;
    private String lastName;
    private String password;

    public int getLibrarianId() {
        return librarianId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setLibrarianId(int librarianId) {
        this.librarianId = librarianId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Librarian(int librarianId, String firstName, String lastName, String password) {
        this.librarianId = librarianId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }
}
