package com.example.stazgrady_comp304sec002_lab4_group7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LibrarianLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_librarian_login);
        Button librarianBtn = findViewById(R.id.librarianBtn);

        EditText usernameText = findViewById(R.id.usernameText);
        EditText passwordText = findViewById(R.id.passwordText);

        librarianBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameText.getText().toString();
                String password = passwordText.getText().toString();

                auth(username, password);
            }
        });
    }

    //placeholder authentication method, not connected to the db
    public void auth (String username, String password) {
        //still need the activities for the librarian module
        if (username.equals("valid") && password.equals("valid")) {
            //Intent intent = new Intent(StudentLoginActivity.this, BookListActivity.class);
            //startActivity(intent);
            Toast.makeText(LibrarianLoginActivity.this, R.string.loginSuccess, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(LibrarianLoginActivity.this, R.string.loginError, Toast.LENGTH_SHORT).show();
        }
    }
}