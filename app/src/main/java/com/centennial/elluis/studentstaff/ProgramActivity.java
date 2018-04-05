package com.centennial.elluis.studentstaff;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.centennial.elluis.studentstaff.database.DataSource;

import java.util.ArrayList;

public class ProgramActivity extends AppCompatActivity {

    private DataSource db;
    private Spinner programSpinner;
    private TextView programInfoTV;
    private int username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program);

        Intent intent = getIntent();
        username = Integer.parseInt(intent.getStringExtra("username"));
        programInfoTV = findViewById(R.id.programInfoTV);
        programSpinner = findViewById(R.id.programSpinner);
        ArrayList<String> PROGRAM_CODES = new ArrayList<>();

        db  = new DataSource(this);
        db.open();
        //get Programs
        Cursor c = db.getAllPrograms();
        if (c.moveToFirst())
        {
            do {
                //PROGRAM_CODES.add(c.getString(1));
                Toast.makeText(this, c.getString(1), Toast.LENGTH_LONG).show();
                PROGRAM_CODES.add(c.getString(1));
            } while (c.moveToNext());
        }
        db.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, PROGRAM_CODES);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        programSpinner.setAdapter(adapter);

        programSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position)
                {
                    case 0:
                        //Display info for 3419
                        DisplayProgramInfo(3419);
                        break;
                    case 1:
                        //Display info for code = 3408
                        DisplayProgramInfo(3408);
                        break;
                    case 2: //Display info for code = 3506
                        DisplayProgramInfo(3506);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                programInfoTV.setText("");
            }
        });
    }

    public void addProgramToStudent(int programCode)
    {

        db.open();
        db.updateStudentProgram(username,programCode);
        db.close();
    }

    //display info of program after selection
    public void DisplayProgramInfo(int programCode)
    {
        db.open();
        Cursor c = db.getProgram(programCode);
        db.close();
        if (c.moveToFirst())
        {
            programInfoTV.setText(String.format("Program code: %s\nTuition Fee: $%d\nDuration: %d\nSemesters: %d", c.getString(1), c.getInt(2), c.getInt(3), c.getInt(4)));
        }
    }

    //get Tuition Fee
    public int getTuitionFee(int programCode)
    {
        //db.open();
        Cursor c = db.getProgramTuitionFee(programCode);
        if(c.moveToFirst())
        {
            return c.getInt(0);
        }
        return 0;
    }

    public void InsertPaymentAmount(int programCode)
    {
        db.open();
        long c = db.insertProgramTotalAmount(username,programCode,getTuitionFee(programCode));
        db.close();
    }

    public void registerBtn_OnClick(View view) {
        //Register program
        int programSelected = programSpinner.getSelectedItemPosition();

        switch (programSelected)
        {
            case 0: // alter programCode = 3419
                addProgramToStudent(3419);
                //Insert Payment info
                InsertPaymentAmount(3419);
                //Go to Payment Activity after adding balance
                Intent intent = new Intent(ProgramActivity.this,PaymentActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
                break;
            case 1:
                //program code = 3408
                addProgramToStudent(3408);
                InsertPaymentAmount(3408);
                //Go to Payment Activity after adding balance
                Intent intent1 = new Intent(ProgramActivity.this,PaymentActivity.class);
                intent1.putExtra("username",username);
                startActivity(intent1);
                break;
            case 2: //programCode = 3506
                addProgramToStudent(3506);
                InsertPaymentAmount(3506);
                //Go to Payment Activity after adding balance
                Intent intent2 = new Intent(ProgramActivity.this,PaymentActivity.class);
                intent2.putExtra("username",username);
                startActivity(intent2);
                break;
        }

    }

    public void cancelRegisterBtn_OnClick(View view) {
        programSpinner.setSelected(false);
        programInfoTV.setText("");
    }
}
