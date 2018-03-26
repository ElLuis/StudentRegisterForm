package com.centennial.elluis.studentstaff;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    private EditText fName;
    private EditText lName;
    private EditText city;
    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //initializing EditTexts
        fName = findViewById(R.id.firstNameET);
        lName = findViewById(R.id.lastNameET);
        city = findViewById(R.id.dobET);
        username = findViewById(R.id.usernameET);
        password = findViewById(R.id.passwordET);


    }

    public void submitBtn_OnClick(View view) {
        //create instance
        String _fname = fName.getText().toString();
        String _lname = lName.getText().toString();
        String _city = city.getText().toString();
        String _username = username.getText().toString();
        String _password = password.getText().toString();

        //add student after registering;

        //Go to login activity after registering
        //pass result back to login
        getIntent().putExtra("fname_key",_fname);
        getIntent().putExtra("lname_key",_lname);
        getIntent().putExtra("dob_key",_city);
        getIntent().putExtra("username_key",_username);
        getIntent().putExtra("password_key",_password);
        setResult(RESULT_OK,getIntent());
        finish();
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
