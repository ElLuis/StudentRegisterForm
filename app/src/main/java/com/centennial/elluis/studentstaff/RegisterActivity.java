package com.centennial.elluis.studentstaff;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.centennial.elluis.studentstaff.database.DataSource;

public class RegisterActivity extends AppCompatActivity {

    private EditText fName;
    private EditText lName;
    private EditText city;
    private EditText username;
    private EditText password;
    private DataSource db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //initializing EditTexts
        fName = findViewById(R.id.firstNameET);
        lName = findViewById(R.id.lastNameET);
        city = findViewById(R.id.cityET);
        username = findViewById(R.id.usernameET);
        password = findViewById(R.id.passwordET);

        //initialize Database adapter
        db = new DataSource(this);

    }

    public void submitBtn_OnClick(View view) {
        //create instance
        String _fname = fName.getText().toString();
        String _lname = lName.getText().toString();
        String _city = city.getText().toString();
        String _username = username.getText().toString();
        String _password = password.getText().toString();

        //insert student after registering;
        db.open();
        db.insertStudent(_username,_fname,_lname,_city,_password);
        db.close();

        Intent intent = new Intent(this, StudentLoginActivity.class);
        startActivity(intent);
        //pass username to Student page
    }

    public void cancelBtn_OnClick(View view) {
        //reset fields
        fName.setText("");
        lName.setText("");
        city.setText("");
        username.setText("");
        password.setText("");

    }
}
