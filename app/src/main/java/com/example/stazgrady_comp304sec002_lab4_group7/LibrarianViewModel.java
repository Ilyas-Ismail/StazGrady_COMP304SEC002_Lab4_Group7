package com.example.stazgrady_comp304sec002_lab4_group7;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class LibrarianViewModel extends AndroidViewModel {

    private LibrarianRepository repository;
    private LiveData<List<Librarian>> allLibrarians;

    public LibrarianViewModel(@NonNull Application application) {
        super(application);
        repository = new LibrarianRepository(application);
        allLibrarians = repository.getAllLibrarians();
    }

    //adds a librarian to the db
    public void insert(Librarian librarian) {
        repository.insert(librarian);
    }

    //updates a librarian from the db
    public void update(Librarian librarian) {
        repository.update(librarian);
    }

    //deletes a librarian from the db
    public void delete(Librarian librarian) {
        repository.delete(librarian);
    }

    //returns all the librarians as LiveData objects by using a query
    public LiveData<List<Librarian>> getAllLibrarians() {
        return allLibrarians;
    }
}
