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

<<<<<<< HEAD
    //method to retrieve all objects from the database
=======
>>>>>>> 12125c334d4f076657d67bfc9e1f21639404f4be
    @Query("SELECT * FROM books_table order by bookId")
    LiveData<List<Books>> getAllBooks();
}
