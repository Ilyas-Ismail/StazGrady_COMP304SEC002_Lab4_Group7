package com.example.stazgrady_comp304sec002_lab4_group7;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class BooksViewModel extends AndroidViewModel {

    private BooksRepository repository;
    private LiveData<List<Books>> allBooks;

    public BooksViewModel(@NonNull Application application) {
        super(application);
        repository = new BooksRepository(application);
        allBooks = repository.getAllBooks();
    }

    //adds a book to the db
    public void insert(Books books) {
        repository.insert(books);
    }

    //updates a book from the db
    public void update(Books books) {
        repository.update(books);
    }

    //deletes a book from the db
    public void delete(Books books) {
        repository.delete(books);
    }

    //returns all the books as LiveData objects by using a query
    public LiveData<List<Books>> getAllBooks() {
        return allBooks;
    }
}
