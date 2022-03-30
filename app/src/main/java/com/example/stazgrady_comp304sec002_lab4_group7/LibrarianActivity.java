package com.example.stazgrady_comp304sec002_lab4_group7;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Person;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class LibrarianActivity extends AppCompatActivity {

    private ArrayList<Books> bookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_librarian);

        bookList = new ArrayList<> ();

        BooksViewModel booksViewModel = new ViewModelProvider(this).get(BooksViewModel.class);

        booksViewModel.getAllBooks().observe(this, new Observer<List<Books>>(){
            @Override
            public void onChanged(@Nullable List<Books> result){
                bookList.addAll(result);
            }
        });

        displayBookList("all");

        Button addBook = findViewById(R.id.add_book);

        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(LibrarianActivity.this, EditBookActivity.class);
                startActivity(intent);
            }
        });
    }

    private void displayBookList(String category) {
        RecyclerView recyclerView = findViewById(R.id.book_list_lib);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        switch(category){
            default:
                break;
        }

        MyAdapter adapter = new MyAdapter(bookList);
        recyclerView.setAdapter(adapter);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.librarian_menu, menu);
        return true;
    }
}