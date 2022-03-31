package com.example.stazgrady_comp304sec002_lab4_group7;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Books.class, version = 1)
public abstract class BooksDatabase extends RoomDatabase {

    private static BooksDatabase instance;

    public abstract BooksDao booksDao();

    //method to load the database
    public static synchronized BooksDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    BooksDatabase.class, "books_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    //callback to add a few entries into the database on creation of the database
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new BooksDatabase.PopulateBooksAsyncTask(instance).execute();
        }
    };

    //async method to add entries into the database
    private static class PopulateBooksAsyncTask extends AsyncTask<Void, Void, Void> {
        private BooksDao booksDao;

        private PopulateBooksAsyncTask(BooksDatabase db) {
            booksDao = db.booksDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            booksDao.insert(new Books("Test1", "Staz", "First Test", "Fiction", 5));
            booksDao.insert(new Books("Test2", "Staz", "Second Test", "Fiction", 5));
            return null;
        }
    }
}
