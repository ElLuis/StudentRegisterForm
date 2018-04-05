package com.centennial.elluis.studentstaff;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.centennial.elluis.studentstaff.database.DataSource;

import java.util.Objects;

public class StaffLoginActivity extends AppCompatActivity {

    private static final String staff_username_prefs = "staff_username_prefs";
    private EditText emailET;
    private EditText passwordET;
    private Button loginBtn;
    private DataSource db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_login);

        db = new DataSource(this);
        emailET = findViewById(R.id.emailTV);
        passwordET = findViewById(R.id.passwordTV);
        loginBtn = findViewById(R.id.loginBtn);

        //retrieving email from shared preferences
        SharedPreferences prefs =
                getSharedPreferences(staff_username_prefs,MODE_PRIVATE);

        String email = prefs.getString("email_key","");
        if(!TextUtils.isEmpty(email))
            emailET.setText(email);

    }

    public void cancelBtn_OnClick(View view) {
        emailET.setText("");
        passwordET.setText("");
    }

    public void loginBtn_OnClick(View view) {

        String _email = emailET.getText().toString();
        String _password = passwordET.getText().toString();

        if(validate(_email,_password))
        {
            SharedPreferences.Editor editor =
                    getSharedPreferences(staff_username_prefs, MODE_PRIVATE).edit();
            editor.putString("email_key",_email);
            editor.apply();

            //view staff activity
            Toast.makeText(this, "valid", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,StaffActivity.class);
            startActivity(intent);
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
        db.open();
        Cursor c = db.validateStaffPassword(username);
        db.close();
        if (c.moveToFirst())
        {
            return c.getString(0).equals(password);
        }
        return false;
    }
    }

