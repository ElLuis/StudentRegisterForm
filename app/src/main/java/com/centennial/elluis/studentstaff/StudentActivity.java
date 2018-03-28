package com.centennial.elluis.studentstaff;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.centennial.elluis.studentstaff.database.DataSource;

public class StudentActivity extends AppCompatActivity {

    private TextView displayText;
    private DataSource db;
    private int username;
    private int programCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        db = new DataSource(this);
        username = getIntent().getIntExtra("username",-1);
        displayText = findViewById(R.id.displayInfoTv);
        programCode = getProgramCode();

    }

    public int getProgramCode()
    {
        db.open();
        Cursor c = db.getStudent(username);

        if (c.moveToFirst())
        {
            return c.getInt(5);
        }
        db.close();
        return 3419; //test
    }

    public void personalInfo_OnClick(View view) {
        //get Student info

        db.open();
        Cursor c = db.getStudent(username);
        if (c.moveToFirst())
        {
            String personalInfo = "Name: "+c.getString(1)+" "+c.getString(2)+"\nCity: "+c.getString(3)+"\nUsername: "
                    +c.getString(0)+"\nPassword: "+c.getString(4);
            displayText.setText(personalInfo);
        }
        db.close();
    }

    public void programInfo_OnClick(View view) {

        db.open();
        Cursor c = db.getProgram(programCode);
        if (c.moveToFirst())
        {
            String programInfo = "Program Name: "+c.getString(1)+"\nCode: "+c.getString(0)+"\nDuration: "
                    +c.getString(3)+"\nSemesters: "+c.getString(4);
            displayText.setText(programInfo);
        }
        db.close();
    }

    public void paymentInfo_OnClick(View view) {
        db.open();
        Cursor c = db.getPayment(username);
        if (c.moveToFirst())
        {
            String paymentInfo = "Total amount: $"+"\nAmount paid: $"+c.getString(1)+"\nBalance: $"
                    +c.getString(2)+"\nStatus: "+c.getString(3);
            displayText.setText(paymentInfo);
        }
        db.close();
    }

    public void goBackBtn(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
