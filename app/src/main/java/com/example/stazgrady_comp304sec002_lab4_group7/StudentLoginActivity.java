package com.example.stazgrady_comp304sec002_lab4_group7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StudentLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);

        Button studentBtn = findViewById(R.id.studentBtn);

        EditText usernameText = findViewById(R.id.usernameText);
        EditText passwordText = findViewById(R.id.passwordText);



        studentBtn.setOnClickListener(new View.OnClickListener() {
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
        //still need the activities for the student module
        if (username.equals("valid") && password.equals("valid")) {
            //Intent intent = new Intent(StudentLoginActivity.this, BookListActivity.class);
            //startActivity(intent);
            Toast.makeText(StudentLoginActivity.this, R.string.loginSuccess, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(StudentLoginActivity.this, R.string.loginError, Toast.LENGTH_SHORT).show();
        }
    }
}