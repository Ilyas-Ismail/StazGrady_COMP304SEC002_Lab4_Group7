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

    @Insert
    void insert(Books books);

    @Update
    void update(Books books);

    @Delete
    void delete(Books books);

    @Query("SELECT * FROM books_table ORDER BY bookId")
    LiveData<List<Books>> getAllBooks();
}
