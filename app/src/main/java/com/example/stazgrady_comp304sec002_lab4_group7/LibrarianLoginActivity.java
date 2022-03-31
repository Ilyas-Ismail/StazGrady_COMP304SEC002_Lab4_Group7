package com.example.stazgrady_comp304sec002_lab4_group7;

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
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class LibrarianLoginActivity extends AppCompatActivity {
    ArrayList<Librarian> librarianList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_librarian_login);
        Button librarianBtn = findViewById(R.id.librarianBtn);

        EditText usernameText = findViewById(R.id.usernameText);
        EditText passwordText = findViewById(R.id.passwordText);

        librarianList = new ArrayList<>();
        LibrarianViewModel librarianViewModel = new ViewModelProvider(this).get(LibrarianViewModel.class);

        librarianViewModel.getAllLibrarians().observe(this, new Observer<List<Librarian>>() {
            @Override
            public void onChanged(List<Librarian> librarians) {
                librarianList.addAll(librarians);
            }
        });

        librarianBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                String username = usernameText.getText().toString();
                String password = passwordText.getText().toString();

                auth(username, password);
            }
        });
    }

    //placeholder authentication method, not connected to the db
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void auth (String username, String password) {

        Librarian librarian = librarianList.stream().filter(s -> s.getLibrarianId() == Integer.parseInt(username) && s.getPassword().equals(password)).findFirst().orElse(null);
        if (librarian != null) {
            Intent intent = new Intent(LibrarianLoginActivity.this, LibrarianActivity.class);
            intent.putExtra("librarianID", librarian.getLibrarianId());
            startActivity(intent);
            Toast.makeText(LibrarianLoginActivity.this, R.string.loginSuccess, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(LibrarianLoginActivity.this, R.string.loginError, Toast.LENGTH_SHORT).show();
        }

    }
}