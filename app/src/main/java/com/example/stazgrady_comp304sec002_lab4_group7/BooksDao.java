package com.example.stazgrady_comp304sec002_lab4_group7;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BooksDao {

    //method to insert an object into the database
    @Insert
    void insert(Books books);

    //method to update an object from the database
    @Update
    void update(Books books);

    //method to delete an object from the database
    @Delete
    void delete(Books books);

    //method to retrieve all objects from the database
    @Query("SELECT * FROM books_table order by bookId")
    LiveData<List<Books>> getAllBooks();
}
