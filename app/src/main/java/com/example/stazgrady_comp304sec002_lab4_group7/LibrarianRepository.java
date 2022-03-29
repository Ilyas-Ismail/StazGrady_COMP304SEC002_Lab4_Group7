package com.example.stazgrady_comp304sec002_lab4_group7;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class LibrarianRepository {

    private LibrarianDao librarianDao;
    private LiveData<List<Librarian>> allLibrarians;

    public LibrarianRepository(Application application) {
        LibrarianDatabase db = LibrarianDatabase.getInstance(application);
        librarianDao = db.librarianDao();
        allLibrarians = librarianDao.getAllLibrarians();
    }

    public void insert(Librarian librarian) {
        new LibrarianRepository.InsertLibrarianAsyncTask(librarianDao).execute(librarian);
    }

    public void update(Librarian librarian) {
        new LibrarianRepository.UpdateLibrarianAsyncTask(librarianDao).execute(librarian);
    }

    public void delete(Librarian librarian) {
        new LibrarianRepository.DeleteLibrarianAsyncTask(librarianDao).execute(librarian);
    }

    public LiveData<List<Librarian>> getAllLibrarians() {
        return allLibrarians;
    }

    private static class InsertLibrarianAsyncTask extends AsyncTask<Librarian, Void, Void> {
        private LibrarianDao librarianDao;

        private InsertLibrarianAsyncTask(LibrarianDao librarianDao) {
            this.librarianDao = librarianDao;
        }

        @Override
        protected Void doInBackground(Librarian... librarians) {
            librarianDao.insert(librarians[0]);
            return null;
        }
    }

    private static class UpdateLibrarianAsyncTask extends AsyncTask<Librarian, Void, Void> {
        private LibrarianDao librarianDao;

        private UpdateLibrarianAsyncTask(LibrarianDao librarianDao) {
            this.librarianDao = librarianDao;
        }

        @Override
        protected Void doInBackground(Librarian... librarians) {
            librarianDao.update(librarians[0]);
            return null;
        }
    }
    private static class DeleteLibrarianAsyncTask extends AsyncTask<Librarian, Void, Void> {
        private LibrarianDao librarianDao;

        private DeleteLibrarianAsyncTask(LibrarianDao librarianDao) {
            this.librarianDao = librarianDao;
        }

        @Override
        protected Void doInBackground(Librarian... librarians) {
            librarianDao.delete(librarians[0]);
            return null;
        }
    }
}
