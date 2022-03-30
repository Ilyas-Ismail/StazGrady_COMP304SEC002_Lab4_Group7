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

public class StudentLoginActivity extends AppCompatActivity {
    ArrayList<Student> studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);

        Button studentBtn = findViewById(R.id.studentBtn);

        EditText usernameText = findViewById(R.id.usernameText);
        EditText passwordText = findViewById(R.id.passwordText);

        studentList = new ArrayList<>();

        StudentViewModel studentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);

        studentViewModel.getAllStudents().observe(this, new Observer<List<Student>>() {
            @Override
            public void onChanged(@Nullable List<Student> result) {
                studentList.addAll(result);
            }
        });

        studentBtn.setOnClickListener(new View.OnClickListener() {
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

        Student student = studentList.stream().filter(s -> s.getStudentId() == Integer.parseInt(username) && s.getPassword().equals(password)).findFirst().orElse(null);
        if (student != null) {
            Intent intent = new Intent(StudentLoginActivity.this, StudentActivity.class);
            intent.putExtra("studentID", student.getStudentId());
            startActivity(intent);
            Toast.makeText(StudentLoginActivity.this, R.string.loginSuccess, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(StudentLoginActivity.this, R.string.loginError, Toast.LENGTH_SHORT).show();
        }
    }
}