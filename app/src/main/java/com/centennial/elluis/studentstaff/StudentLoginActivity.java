package com.centennial.elluis.studentstaff;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.centennial.elluis.studentstaff.R;

import java.util.ArrayList;
import java.util.Objects;

public class StudentLoginActivity extends AppCompatActivity {

    private EditText usernameET;
    private EditText passwordET;
    private Button loginBtn;
    private String[] studentUsernames;
    private String[] studentPasswords;
    private static final String STUDENT_USERNAME_PREFS = "student_username_prefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        usernameET = findViewById(R.id.usernameET);
        passwordET = findViewById(R.id.passwordTV);
        loginBtn = findViewById(R.id.loginBtn);

        Student student = new Student();

        //Find passwords and usernames
        studentUsernames = student.FindUsername();
        studentPasswords = student.FindPasswords();

        //retrieving username from shared preferences
        SharedPreferences prefs =
                getSharedPreferences(STUDENT_USERNAME_PREFS,MODE_PRIVATE);

        String username = prefs.getString("username_key","");
        if(!TextUtils.isEmpty(username))
            usernameET.setText(username);

    }

    public void cancelBtn_OnClick(View view) {
        usernameET.setText("");
        passwordET.setText("");
    }

    public void loginBtn_OnClick(View view) {

        String _username = usernameET.getText().toString();
        String _password = passwordET.getText().toString();

        if(validate(_username,_password))
        {
            SharedPreferences.Editor editor =
                    getSharedPreferences("student_username_prefs", MODE_PRIVATE).edit();
            editor.putString("username_key",_username);
            editor.apply();

            //view student activity
            Toast.makeText(this, "valid", Toast.LENGTH_SHORT).show();
        }
        else
        {
            //throw error
            Toast.makeText(this, "invalid", Toast.LENGTH_SHORT).show();
            passwordET.setFocusable(true);
            passwordET.setError("Password and/or username is wrong...");
        }

    }
    public boolean validate(String username, String password)
    {
        for (int i = 0; i < studentUsernames.length;i++)
        {
            if(Objects.equals(studentUsernames[i], username) && Objects.equals(studentPasswords[i], password))
            {
                return true;
            }
        }
        return false;
    }

    public void NotRegistered_OnClick(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}