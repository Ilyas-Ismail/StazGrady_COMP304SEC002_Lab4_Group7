package com.example.stazgrady_comp304sec002_lab4_group7;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

        StudentViewModel studentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);

        studentViewModel.getAllStudents().observe(this, result -> {
            studentList.addAll(result);
            displayStudentInfo();
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void displayStudentInfo() {

        TextView studentID_txt = findViewById(R.id.studentID_profile);
        TextView firstName_txt = findViewById(R.id.firstName_profile);
        TextView lastName_txt = findViewById(R.id.lastName_profile);

        Student student = studentList.stream().filter(s -> s.getStudentId() == studentID).findFirst().orElse(null);

        assert student != null;
        studentID_txt.setText(String.format(Locale.CANADA,"%d", student.getStudentId()));
        firstName_txt.setText(student.getFirstName());
        lastName_txt.setText(student.getLastName());

        BooksViewModel booksViewModel = new ViewModelProvider(this).get(BooksViewModel.class);

        booksViewModel.getAllBooks().observe(this, result -> {
            assert result != null;
            bookList.addAll(result);
            displayBookInfo(student.getBookId());
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void displayBookInfo(int bookID) {
        TextView bookName = findViewById(R.id.bookName_profile);
        TextView author = findViewById(R.id.bookAuthor_profile);
        TextView description = findViewById(R.id.bookDescription_profile);
        TextView category = findViewById(R.id.bookCategory_profile);

        if(bookID != 0){
            Books book = bookList.stream().filter(b -> b.getBookId() == bookID).findFirst().orElse(null);

            assert book != null;
            bookName.setText(book.getBookName());
            author.setText(book.getAuthorName());
            description.setText(book.getBookDescription());
            category.setText(book.getCategory());
        }

    }


}