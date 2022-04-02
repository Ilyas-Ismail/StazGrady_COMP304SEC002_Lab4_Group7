package com.example.stazgrady_comp304sec002_lab4_group7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditBookActivity extends AppCompatActivity {

    Button saveBook;
    Button removeBook;
    BooksViewModel booksViewModel;
    EditText name;
    EditText author;
    EditText description;
    EditText category;
    EditText quantity;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);

        name = findViewById(R.id.name_edit);
        author = findViewById(R.id.author_edit);
        description = findViewById(R.id.description_edit);
        category = findViewById(R.id.category_edit);
        quantity = findViewById(R.id.quantity_edit);
        saveBook = findViewById(R.id.save_book);
        removeBook = findViewById(R.id.delete_book);

        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        booksViewModel = viewModelProvider.get(BooksViewModel.class);

        intent = getIntent();

        if(intent.hasExtra("bookID")){
            editBook();
        } else{
            addBook();
            removeBook.setEnabled(false);
        }
    }

    private void editBook() {
        name.setText(intent.getStringExtra("name"));
        author.setText(intent.getStringExtra("author"));
        description.setText(intent.getStringExtra("description"));
        category.setText(intent.getStringExtra("category"));
        quantity.setText(String.format("%d",intent.getIntExtra("quantity", 0)));

        saveBook.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Books updatedBook = getBookInfo();
                updatedBook.setBookId(intent.getIntExtra("bookID", 0));

                booksViewModel.update(updatedBook);

                Intent intent = new Intent(EditBookActivity.this, LibrarianActivity.class);
                startActivity(intent);
            }
        });

        removeBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Books deleteBook = getBookInfo();
                deleteBook.setBookId(intent.getIntExtra("bookID", 0));

                booksViewModel.delete(deleteBook);

                Intent intent = new Intent(EditBookActivity.this, LibrarianActivity.class);
                startActivity(intent);
            }
        });
    }

    private Books getBookInfo() {
        Books book = new Books(
                name.getText().toString(),
                author.getText().toString(),
                description.getText().toString(),
                category.getText().toString(),
                Integer.parseInt(quantity.getText().toString())
        );
        return book;
    }

    private void addBook() {
        saveBook.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Books newBook = new Books(
                        name.getText().toString(),
                        author.getText().toString(),
                        description.getText().toString(),
                        category.getText().toString(),
                        Integer.parseInt(quantity.getText().toString())
                );
                booksViewModel.insert(newBook);

                Intent intent = new Intent(EditBookActivity.this, LibrarianActivity.class);
                startActivity(intent);
            }
        });
    }
}