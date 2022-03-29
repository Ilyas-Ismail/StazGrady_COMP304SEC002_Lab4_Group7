package com.example.stazgrady_comp304sec002_lab4_group7;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Books.class, version = 1)
public abstract class BooksDatabase extends RoomDatabase {

    private static BooksDatabase instance;

    public abstract BooksDao booksDao();

    public static synchronized BooksDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    BooksDatabase.class, "books_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
