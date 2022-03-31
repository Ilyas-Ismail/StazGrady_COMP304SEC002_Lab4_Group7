package com.example.stazgrady_comp304sec002_lab4_group7;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class BooksRepository {

    private BooksDao booksDao;
    private LiveData<List<Books>> allBooks;

    //constructor to load the database and get all entries
    public BooksRepository(Application application) {
        BooksDatabase db = BooksDatabase.getInstance(application);
        booksDao = db.booksDao();
        allBooks = booksDao.getAllBooks();
    }

    //method to call when trying to insert an object into the database
    public void insert(Books books) {
        new BooksRepository.InsertBooksAsyncTask(booksDao).execute(books);
    }

    //method to call when trying to update an object from the database
    public void update(Books books) {
        new BooksRepository.UpdateBooksAsyncTask(booksDao).execute(books);
    }

    //method to call when trying to delete an object from the database
    public void delete(Books books) {
        new BooksRepository.DeleteBooksAsyncTask(booksDao).execute(books);
    }

    //method to retrieve all the entries from the database
    public LiveData<List<Books>> getAllBooks() {
        return allBooks;
    }

    //Async methods for each interface method
    private static class InsertBooksAsyncTask extends AsyncTask<Books, Void, Void> {
        private BooksDao booksDao;

        //setting this booksDao as the initialized booksDao above
        private InsertBooksAsyncTask(BooksDao booksDao) {
            this.booksDao = booksDao;
        }

        @Override
        protected Void doInBackground(Books... books) {
            //inserting the book into the database
            booksDao.insert(books[0]);
            return null;
        }
    }

    //same as above, except updating a book from the database
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

    //same as above, except deleting a book from the database
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
