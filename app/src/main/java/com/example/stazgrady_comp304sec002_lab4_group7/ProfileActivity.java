package com.example.stazgrady_comp304sec002_lab4_group7;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    private int studentID;
    private List<Student> studentList;
    private List<Books> bookList;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent intent = getIntent();
        studentID = intent.getIntExtra("studentID", 0);
        studentList = new ArrayList<>();
        bookList = new ArrayList<>();

        displayStudentInfo();
        displayBookInfo();

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void displayStudentInfo() {
        StudentViewModel studentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);

        LiveData<List<Student>> studentLiveData = studentViewModel.getAllStudents();
        studentList = studentLiveData.getValue();

        Student student = studentList.stream().filter(s -> s.getStudentId() == studentID).findFirst().orElse(null);

        TextView studentID = findViewById(R.id.studentID_profile);
        TextView firstName = findViewById(R.id.firstName_profile);
        TextView lastName = findViewById(R.id.lastName_profile);

<<<<<<< HEAD
        if (student.getFirstName() == null) {
            studentID.setText("null");
            firstName.setText("null");
            lastName.setText("null");
        } else {
            studentID.setText(student.getStudentId());
            firstName.setText(student.getFirstName());
            lastName.setText(student.getLastName());
        }
=======
        studentID.setText(student.getStudentId());
        firstName.setText(student.getFirstName());
        lastName.setText(student.getLastName());
>>>>>>> 12125c334d4f076657d67bfc9e1f21639404f4be
    }

    private void displayBookInfo() {
        BooksViewModel booksViewModel = new ViewModelProvider(this).get(BooksViewModel.class);

        booksViewModel.getAllBooks().observe(this, new Observer<List<Books>>() {
            @Override
            public void onChanged(@Nullable List<Books> result) {
                bookList.addAll(result);
            }
        });
    }



}