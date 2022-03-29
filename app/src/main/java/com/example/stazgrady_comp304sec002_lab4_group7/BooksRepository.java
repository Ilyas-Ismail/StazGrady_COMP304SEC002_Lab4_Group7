package com.example.stazgrady_comp304sec002_lab4_group7;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class BooksRepository {

    private BooksDao booksDao;
    private LiveData<List<Books>> allBooks;

    public BooksRepository(Application application) {
        BooksDatabase db = BooksDatabase.getInstance(application);
        booksDao = db.booksDao();
        allBooks = booksDao.getAllBooks();
    }

    public void insert(Books books) {
        new BooksRepository.InsertBooksAsyncTask(booksDao).execute(books);
    }

    public void update(Books books) {
        new BooksRepository.UpdateBooksAsyncTask(booksDao).execute(books);
    }

    public void delete(Books books) {
        new BooksRepository.DeleteBooksAsyncTask(booksDao).execute(books);
    }

    public LiveData<List<Books>> getAllBooks() {
        return allBooks;
    }

    private static class InsertBooksAsyncTask extends AsyncTask<Books, Void, Void> {
        private BooksDao booksDao;

        private InsertBooksAsyncTask(BooksDao booksDao) {
            this.booksDao = booksDao;
        }

        @Override
        protected Void doInBackground(Books... books) {
            booksDao.insert(books[0]);
            return null;
        }
    }

    private static class UpdateBooksAsyncTask extends AsyncTask<Books, Void, Void> {
        private BooksDao booksDao;

        private UpdateBooksAsyncTask(BooksDao booksDao) {
            this.booksDao = booksDao;
        }

        @Override
        protected Void doInBackground(Books... books) {
            booksDao.update(books[0]);
            return null;
        }
    }
    private static class DeleteBooksAsyncTask extends AsyncTask<Books, Void, Void> {
        private BooksDao booksDao;

        private DeleteBooksAsyncTask(BooksDao booksDao) {
            this.booksDao = booksDao;
        }

        @Override
        protected Void doInBackground(Books... books) {
            booksDao.delete(books[0]);
            return null;
        }
    }

}
