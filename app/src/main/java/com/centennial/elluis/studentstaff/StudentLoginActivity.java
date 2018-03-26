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

    private static final int REGISTER_REQUEST = 1001;
    private EditText usernameET;
    private EditText passwordET;
    private Button loginBtn;
    private ArrayList<String> studentUsernames;
    private ArrayList<String> studentPasswords;
    private static final String STUDENT_USERNAME_PREFS = "student_username_prefs";

    private String firstname;
    private String lastname;
    private String city;
    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        usernameET = findViewById(R.id.usernameET1);
        passwordET = findViewById(R.id.passwordET1);
        loginBtn = findViewById(R.id.loginBtn);


        //Find passwords and usernames
        studentUsernames = new ArrayList<>();
        studentPasswords = new ArrayList<>();

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
                    getSharedPreferences(STUDENT_USERNAME_PREFS, MODE_PRIVATE).edit();
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
    //validate credentials
    public boolean validate(String username, String password)
    {
        for(int i = 0; i < studentUsernames.size();i++)
        {
            if (studentUsernames.contains(username) && studentPasswords.contains(password))
                return true;
        }
        return false;
    }

    public void NotRegistered_OnClick(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivityForResult(intent,REGISTER_REQUEST);
    }

    //receive registration credentials
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && requestCode == REGISTER_REQUEST)
        {
            firstname = data.getStringExtra("fname_key");
            lastname = data.getStringExtra("lname_key");
            city = data.getStringExtra("city_key");
            username = data.getStringExtra("username_key");
            password = data.getStringExtra("password_key");

            studentUsernames.add(username);
            studentPasswords.add(password);
        }
    }
}