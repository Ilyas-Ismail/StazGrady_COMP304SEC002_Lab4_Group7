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
import android.view.View;
import android.widget.Button;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LibrarianActivity extends AppCompatActivity {

    private ArrayList<Books> bookList;
    BooksViewModel booksViewModel;
    BookAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_librarian);

        bookList = new ArrayList<>();
        booksViewModel = new ViewModelProvider(this).get(BooksViewModel.class);

        recyclerView = findViewById(R.id.book_list_lib);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new BookAdapter();
        recyclerView.setAdapter(adapter);

        displayBookList("all");
        Button addBook = findViewById(R.id.add_book);

        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LibrarianActivity.this, EditBookActivity.class);
                startActivity(intent);
            }
        });

        adapter.setOnItemClickListener(new BookAdapter.onItemClickListener() {
            @Override
            public void onItemClick(Books book) {
                Intent intent = new Intent(LibrarianActivity.this, EditBookActivity.class);
                intent.putExtra("bookID", book.getBookId());
                intent.putExtra("name", book.getBookName());
                intent.putExtra("author", book.getAuthorName());
                intent.putExtra("description", book.getBookDescription());
                intent.putExtra("category", book.getCategory());
                intent.putExtra("quantity", book.getQuantity());
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
        inflater.inflate(R.menu.librarian_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.librarianMenuItem1:
                displayBookList("fiction");
                break;
            case R.id.librarianMenuItem2:
                displayBookList("nonFiction");
                break;
            case R.id.librarianMenuItem3:
                displayBookList("educational");
                break;
            case R.id.librarianMenuItem4:
                displayBookList("history");
                break;
            default:
                displayBookList("all");
                break;
        }
        return true;
    }
}