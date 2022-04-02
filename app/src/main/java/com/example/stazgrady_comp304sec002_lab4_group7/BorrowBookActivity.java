package com.example.stazgrady_comp304sec002_lab4_group7;

import static java.lang.Integer.parseInt;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class BorrowBookActivity extends AppCompatActivity {

    Button borrow_btn;
    TextView name_txt;
    TextView author_txt;
    TextView description_txt;
    TextView category_txt;
    TextView quantity_txt;
    BooksViewModel booksViewModel;
    StudentViewModel studentViewModel;
    String name;
    String author;
    String description;
    String category;
    int quantity;
    int bookID;
    int studentID;

    ArrayList<Books> bookList;
    ArrayList<Student> studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow_book);

        bookList = new ArrayList<>();
        studentList = new ArrayList<>();

        name_txt = findViewById(R.id.name_borrow);
        author_txt = findViewById(R.id.author_borrow);
        description_txt = findViewById(R.id.description_borrow);
        category_txt = findViewById(R.id.category_borrow);
        quantity_txt = findViewById(R.id.quantity_borrow);

        name = getIntent().getStringExtra("name");
        author = getIntent().getStringExtra("author");
        description = getIntent().getStringExtra("description");
        category = getIntent().getStringExtra("category");
        quantity = getIntent().getIntExtra("quantity", 0);
        bookID = getIntent().getIntExtra("bookID", 0);
        studentID = getIntent().getIntExtra("studentID", 0);

        name_txt.setText(name);
        author_txt.setText(author);
        description_txt.setText(description);
        category_txt.setText(category);
        quantity_txt.setText(String.format("%d", quantity));

        borrow_btn = findViewById(R.id.borrow_btn);

        booksViewModel = new ViewModelProvider(this).get(BooksViewModel.class);
        studentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);

        studentViewModel.getAllStudents().observe(this, new Observer<List<Student>>() {
            @Override
            public void onChanged(@Nullable List<Student> result) {
                studentList.addAll(result);
            }
        });

        borrow_btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                borrowBook();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void borrowBook() {

        Student student = studentList.stream().filter(s -> s.getStudentId() == studentID).findFirst().orElse(null);

        if (quantity > 0 && student.getBookId() == 0) {

            student.setBookId(bookID);

            studentViewModel.update(student);

            Books book = new Books(name, author, description, category, quantity);
            book.setBookId(bookID);
            book.setQuantity(quantity--);

            booksViewModel.update(book);

            Toast.makeText(BorrowBookActivity.this, "You have successfully borrowed a book", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(BorrowBookActivity.this, "You are unable to borrow a book at this time", Toast.LENGTH_SHORT).show();
        }

        Intent intent = new Intent(BorrowBookActivity.this, StudentActivity.class);
        intent.putExtra("studentID", studentID);
        startActivity(intent);
    }


}
