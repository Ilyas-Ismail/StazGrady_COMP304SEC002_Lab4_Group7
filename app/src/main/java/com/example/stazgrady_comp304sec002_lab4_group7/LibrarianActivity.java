package com.example.stazgrady_comp304sec002_lab4_group7;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Person;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class LibrarianActivity extends AppCompatActivity {

    private ArrayList<Books> bookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_librarian);

        bookList = new ArrayList<>();
        BooksViewModel booksViewModel = new ViewModelProvider(this).get(BooksViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.book_list_lib);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        MyAdapter adapter = new MyAdapter();
        recyclerView.setAdapter(adapter);

        booksViewModel.getAllBooks().observe(this, new Observer<List<Books>>(){
            @Override
            public void onChanged(@Nullable List<Books> result){
                bookList.addAll(result);
                adapter.setBookList(bookList);
            }
        });

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

        BooksViewModel booksViewModel = new ViewModelProvider(this).get(BooksViewModel.class);

        ArrayList<Books> fictionList = new ArrayList<>();
        ArrayList<Books> nonFictionList = new ArrayList<>();

        switch(category){
            case "all":
                bookList = new ArrayList<>();
                booksViewModel.getAllBooks().observe(this, new Observer<List<Books>>(){
                    @Override
                    public void onChanged(@Nullable List<Books> result){
                        bookList.addAll(result);
                    }
                });
                break;
            case "fiction":
                for (int i = 0; i < bookList.size(); i++) {
                    if (bookList.get(i).getCategory() == "fiction"){
                        bookList.remove(i);
                        //fictionList.add(bookList.get(i));
                    }
                    //adapter.setBookList(fictionList);
                }
                break;
            case "non-fiction":
                bookList = new ArrayList<>();
                booksViewModel.getAllBooks().observe(this, new Observer<List<Books>>(){
                    @Override
                    public void onChanged(@Nullable List<Books> result){
                        bookList.addAll(result);
                        for (int i = 0; i < bookList.size() - 1; i++) {
                            if (bookList.get(i).getCategory() == "non-fiction"){
                                bookList.remove(i);
                            }
                        }
                    }
                });
                break;
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.librarian_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(LibrarianActivity.this, LibrarianActivity.class);
        switch (item.getItemId()) {
            case R.id.librarianMenuItem1:
                //startActivity(intent);
                displayBookList("all");
                break;
            case R.id.librarianMenuItem2:
                //startActivity(intent);
                displayBookList("non-fiction");
                break;
        }
        return true;
    }
}