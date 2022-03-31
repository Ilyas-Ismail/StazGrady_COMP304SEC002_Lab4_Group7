package com.example.stazgrady_comp304sec002_lab4_group7;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class StudentActivity extends AppCompatActivity {

    private ArrayList<Books> bookList;
    private int studentID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        Intent intent = getIntent();

        studentID = intent.getIntExtra("studentID", 0);

        bookList = new ArrayList<>();

        BooksViewModel booksViewModel = new ViewModelProvider(this).get(BooksViewModel.class);

        booksViewModel.getAllBooks().observe(this, new Observer<List<Books>>() {
            @Override
            public void onChanged(@Nullable List<Books> result) {
                bookList.addAll(result);
            }
        });

        displayBookList("all");
    }

    private void displayBookList(String category) {
        RecyclerView recyclerView = findViewById(R.id.book_list_stu);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
<<<<<<< HEAD
        MyAdapter adapter = new MyAdapter();
        recyclerView.setAdapter(adapter);
=======
>>>>>>> 12125c334d4f076657d67bfc9e1f21639404f4be

        switch(category){
            default:
                break;
        }
<<<<<<< HEAD
=======

        MyAdapter adapter = new MyAdapter(bookList);
        recyclerView.setAdapter(adapter);
>>>>>>> 12125c334d4f076657d67bfc9e1f21639404f4be
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.student_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.studentProfile:
                Intent intent = new Intent(StudentActivity.this, ProfileActivity.class);
                intent.putExtra("studentID", studentID);
                startActivity(intent);
                break;
<<<<<<< HEAD
            case R.id.studentMenuItem4:
                Intent intent1 = new Intent(StudentActivity.this, StudentActivity.class);
                startActivity(intent1);
                displayBookList("history");
=======
>>>>>>> 12125c334d4f076657d67bfc9e1f21639404f4be
            default:
                displayBookList("all");
        }
        return true;
    }
}