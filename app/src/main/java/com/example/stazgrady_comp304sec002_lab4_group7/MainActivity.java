package com.example.stazgrady_comp304sec002_lab4_group7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button librarianBtn = findViewById(R.id.librarianBtn);
        Button studentBtn = findViewById(R.id.studentBtn);
        TextView signUpText = findViewById(R.id.signUpText);

        librarianBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initLibrarianLoginActivity();
            }
        });

        studentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initStudentLoginActivity();
            }
        });

        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initStudentSignUpActivity();
            }
        });
    }

    public void initLibrarianLoginActivity() {
        Intent intent = new Intent(this, LibrarianLoginActivity.class);
        startActivity(intent);
    }

    public void initStudentLoginActivity() {
        Intent intent = new Intent(this, StudentLoginActivity.class);
        startActivity(intent);
    }

    public void initStudentSignUpActivity() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
}