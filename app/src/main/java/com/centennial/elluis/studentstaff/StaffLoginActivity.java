package com.centennial.elluis.studentstaff;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class StaffLoginActivity extends AppCompatActivity {

    private static final String MY_GLOBAL_PREFS = "my_global_prefs";
    private EditText emailET;
    private EditText passwordET;
    private Button loginBtn;
    private String[] staffEmails;
    private String[] staffPasswords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_login);

        emailET = findViewById(R.id.emailTV);
        passwordET = findViewById(R.id.passwordTV);
        loginBtn = findViewById(R.id.loginBtn);
        staffEmails = getResources().getStringArray(R.array.staffEmails);
        staffPasswords = getResources().getStringArray(R.array.staffPasswords);

        //retrieving email from shared preferences
        SharedPreferences prefs =
                getSharedPreferences(MY_GLOBAL_PREFS,MODE_PRIVATE);

        String email = prefs.getString("email_key","");
        if(!TextUtils.isEmpty(email))
            emailET.setText(email);

    }

    public void cancelBtn_OnClick(View view) {
        emailET.setText(null);
        passwordET.setText(null);
    }

    public void loginBtn_OnClick(View view) {

        String _email = emailET.getText().toString();
        String _password = passwordET.getText().toString();

        if(validate(_email,_password))
        {
            SharedPreferences.Editor editor =
                    getSharedPreferences(MY_GLOBAL_PREFS, MODE_PRIVATE).edit();
            editor.putString("email_key",_email);
            editor.apply();

            //view staff activity
            Toast.makeText(this, "valid", Toast.LENGTH_SHORT).show();
        }
        else
        {
            //throw error
            Toast.makeText(this, "invalid", Toast.LENGTH_SHORT).show();
        }

    }
    public boolean validate(String email, String password)
    {
        for (int i = 0; i <= staffEmails.length;i++)
        {
                if(Objects.equals(staffEmails[i], email) && Objects.equals(staffPasswords[i], password))
                {
                    return true;
                }
            }
        return false;
    }
}
