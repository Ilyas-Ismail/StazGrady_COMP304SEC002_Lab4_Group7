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
import java.util.Locale;

public class StudentActivity extends AppCompatActivity {

    private ArrayList<Books> bookList;
    BooksViewModel booksViewModel;
    BookAdapter adapter;
    RecyclerView recyclerView;
    private int studentID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        Intent intent = getIntent();

        studentID = intent.getIntExtra("studentID", 0);

        bookList = new ArrayList<>();
        booksViewModel = new ViewModelProvider(this).get(BooksViewModel.class);

        recyclerView = findViewById(R.id.book_list_stu);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new BookAdapter();
        recyclerView.setAdapter(adapter);

        displayBookList("all");

        adapter.setOnItemClickListener(new BookAdapter.onItemClickListener() {
            @Override
            public void onItemClick(Books book) {
                Intent intent = new Intent(StudentActivity.this, BorrowBookActivity.class);
                intent.putExtra("bookID", book.getBookId());
                intent.putExtra("name", book.getBookName());
                intent.putExtra("author", book.getAuthorName());
                intent.putExtra("description", book.getBookDescription());
                intent.putExtra("category", book.getCategory());
                intent.putExtra("quantity", book.getQuantity());
                intent.putExtra("studentID", studentID);
                startActivity(intent);
            }
        });

    }

    private void displayBookList(String category) {

        ArrayList<Books> catList = new ArrayList<>();
        bookList = new ArrayList<>();

        booksViewModel.getAllBooks().observe(this, new Observer<List<Books>>() {
            @Override
            public void onChanged(@Nullable List<Books> result) {
                bookList.addAll(result);
                switch (category) {
                    case "fiction":
                        for (int i = 0; i < bookList.size(); i++) {
                            if (bookList.get(i).getCategory().toLowerCase(Locale.ROOT).equals("fiction")) {
                                catList.add(bookList.get(i));
                                adapter.setBookList(catList);
                            }
                        }
                        break;
                    case "nonFiction":
                        for (int i = 0; i < bookList.size(); i++) {
                            if (bookList.get(i).getCategory().toLowerCase(Locale.ROOT).equals("non-fiction")) {
                                catList.add(bookList.get(i));
                                adapter.setBookList(catList);
                            }
                        }
                        break;
                    case "educational":
                        for (int i = 0; i < bookList.size(); i++) {
                            if (bookList.get(i).getCategory().toLowerCase(Locale.ROOT).equals("educational")) {
                                catList.add(bookList.get(i));
                                adapter.setBookList(catList);
                            }
                        }
                        break;
                    case "history":
                        for (int i = 0; i < bookList.size(); i++) {
                            if (bookList.get(i).getCategory().toLowerCase(Locale.ROOT).equals("history")) {
                                catList.add(bookList.get(i));
                                adapter.setBookList(catList);
                            }
                        }
                        break;
                    default:
                        adapter.setBookList(bookList);
                        break;
                }
            }
        });

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
            case R.id.studentMenuItem1:
                displayBookList("fiction");
                break;
            case R.id.studentMenuItem2:
                displayBookList("nonFiction");
                break;
            case R.id.studentMenuItem3:
                displayBookList("educational");
                break;
            case R.id.studentMenuItem4:
                displayBookList("history");
                break;
            default:
                displayBookList("all");
                break;
        }
        return true;
    }
}