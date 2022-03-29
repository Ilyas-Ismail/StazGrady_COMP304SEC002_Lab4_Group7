package com.example.stazgrady_comp304sec002_lab4_group7;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface LibrarianDao {

    @Insert
    void insert(Librarian librarian);

    @Update
    void update(Librarian librarian);

    @Delete
    void delete(Librarian librarian);

    @Query("SELECT * FROM librarian_table ORDER BY librarianId")
    LiveData<List<Librarian>> getAllLibrarians();
}
