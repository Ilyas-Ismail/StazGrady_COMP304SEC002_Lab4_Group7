package com.example.stazgrady_comp304sec002_lab4_group7;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "books_table")
public class Books {
    @PrimaryKey(autoGenerate = true)
    private int bookId;

    private String bookName;
    private String authorName;
    private String bookDescription;
    private String category;
    private int quantity;

    //getters and setters for each variable
    public int getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public String getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    //constructor for Books object
    public Books(String bookName, String authorName, String bookDescription, String category, int quantity) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.bookDescription = bookDescription;
        this.category = category;
        this.quantity = quantity;
    }
}
