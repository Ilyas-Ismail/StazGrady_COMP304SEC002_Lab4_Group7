package com.example.stazgrady_comp304sec002_lab4_group7;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Librarian.class, version = 1)
public abstract class LibrarianDatabase extends RoomDatabase {

    private static LibrarianDatabase instance;

    public abstract LibrarianDao librarianDao();

    public static synchronized LibrarianDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    LibrarianDatabase.class, "librarian_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateLibrarianAsyncTask(instance).execute();
        }
    };

    private static class PopulateLibrarianAsyncTask extends AsyncTask<Void, Void, Void>{
        private LibrarianDao librarianDao;

        private PopulateLibrarianAsyncTask(LibrarianDatabase db) {
            librarianDao = db.librarianDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            librarianDao.insert(new Librarian(001, "Yash", "Sheth", "COMP304"));
            librarianDao.insert(new Librarian(007, "James", "Bond", "CasinoRoyale"));
            return null;
        }
    }
}
