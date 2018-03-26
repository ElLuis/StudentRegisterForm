package com.centennial.elluis.studentstaff;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    private EditText fName;
    private EditText lName;
    private EditText dob;
    private EditText username;
    private EditText password;

    private EditText[] editTexts = {fName,lName,dob,username,password};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //initializing EditTexts
        fName = findViewById(R.id.firstNameET);
        lName = findViewById(R.id.lastNameET);
        dob = findViewById(R.id.dobET);
        username = findViewById(R.id.usernameET);
        password = findViewById(R.id.passwordET);


    }

    public void submitBtn_OnClick(View view) {
        //create instance
        String _fname = fName.getText().toString();
        String _lname = lName.getText().toString();
        String _dob = dob.getText().toString();
        String _username = username.getText().toString();
        String _password = password.getText().toString();

        //add student after registering;
        Student student = new Student(_fname,_lname,_dob,_username,_password);
        student.AddStudent(student);
    }

    public void cancelBtn_OnClick(View view) {
        //reset fields
        fName.setText("");
        lName.setText("");
        dob.setText("");
        username.setText("");
        password.setText("");

    }
}
