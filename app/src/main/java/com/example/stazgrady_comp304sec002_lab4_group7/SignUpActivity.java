package com.example.stazgrady_comp304sec002_lab4_group7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Button signUpBtn = findViewById(R.id.signUpBtn);
        TextView returnText = findViewById(R.id.returnText);

        EditText usernameText = findViewById(R.id.usernameText);
        EditText passwordText = findViewById(R.id.passwordText);
        EditText firstNameText = findViewById(R.id.firstNameText);
        EditText lastNameText = findViewById(R.id.lastNameText);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameText.getText().toString();
                String password = passwordText.getText().toString();
                String firstName = firstNameText.getText().toString();
                String lastName = lastNameText.getText().toString();

                signUp(username, password, firstName, lastName);
            }
        });

        returnText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initMainActivity();
            }
        });
    }

    public void initMainActivity() {
        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void signUp(String username, String password, String firstName, String lastName) {

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName)) {
            Toast.makeText(SignUpActivity.this, R.string.signUpFailure, Toast.LENGTH_SHORT).show();
        } else {
            //add user details to roomDB
            Student student = new Student(Integer.parseInt(username), firstName, lastName, password);
            StudentViewModel studentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);
            studentViewModel.insert(student);

            Toast.makeText(SignUpActivity.this, R.string.signUpSuccess, Toast.LENGTH_SHORT).show();
            initMainActivity();
        }
    }
}