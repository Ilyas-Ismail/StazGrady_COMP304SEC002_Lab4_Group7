package com.example.stazgrady_comp304sec002_lab4_group7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);

        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        BooksViewModel booksViewModel = viewModelProvider.get(BooksViewModel.class);

        Button addBook = findViewById(R.id.add_book_edit);
        addBook.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                EditText name = findViewById(R.id.name_edit);
                EditText author = findViewById(R.id.author_edit);
                EditText description = findViewById(R.id.description_edit);
                EditText category = findViewById(R.id.category_edit);
                EditText quantity = findViewById(R.id.quantity_edit);

<<<<<<< HEAD
                Books newBook = new Books(
                        name.getText().toString(),
                        author.getText().toString(),
                        description.getText().toString(),
                        category.getText().toString(),
                        Integer.parseInt(quantity.getText().toString())
                );
=======
                Books newBook = new Books(name.getText().toString(), author.getText().toString(), description.getText().toString(),
                        category.getText().toString(), Integer.parseInt(quantity.getText().toString()));
>>>>>>> 12125c334d4f076657d67bfc9e1f21639404f4be
                booksViewModel.insert(newBook);

                Intent intent = new Intent(EditBookActivity.this, LibrarianActivity.class);
                startActivity(intent);
            }
        });
    }
}