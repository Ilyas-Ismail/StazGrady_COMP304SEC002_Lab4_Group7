package com.example.stazgrady_comp304sec002_lab4_group7;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class StudentViewModel extends AndroidViewModel {

    private StudentRepository repository;
    private LiveData<List<Student>> allStudents;

    public StudentViewModel(@NonNull Application application) {
        super(application);
        repository = new StudentRepository(application);
        allStudents = repository.getAllStudents();
    }

    //adds a student to the db
    public void insert(Student student) {
        repository.insert(student);
    }

    //updates a student from the db
    public void update(Student student) {
        repository.update(student);
    }

    //deletes a student from the db
    public void delete(Student student) {
        repository.delete(student);
    }

    //returns all the students as LiveData objects by using a query
    public LiveData<List<Student>> getAllStudents() {
        return allStudents;
    }
}
