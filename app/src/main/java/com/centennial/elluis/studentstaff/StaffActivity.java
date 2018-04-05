package com.centennial.elluis.studentstaff;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.centennial.elluis.studentstaff.database.DataSource;

import java.util.ArrayList;

public class StaffActivity extends AppCompatActivity {

    private int username;
    private DataSource db;
    private ListAdapter adapter;
    private ArrayList<Integer > studentsArray;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);
        //list view creation
        db = new DataSource(this);
        listView  = findViewById(R.id.studentsListView);
        studentsArray = new ArrayList<>();

        populateListView();
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, studentsArray);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                username = (int) parent.getItemAtPosition(position); // username instance
            }
        });
    }

    public void populateListView()
    {
        db.open();
        Cursor c = db.getAllStudentsInProgress();
        if (c.moveToFirst())
        {
            do {
                //PROGRAM_CODES.add(c.getString(1));
                studentsArray.add(c.getInt(0));
            } while (c.moveToNext());
        }
        db.close();
    }


    public void admitBtn_OnClick(View view) {
        db.open();
        db.grantAdmission(username);
        db.close();
        Toast.makeText(this, "Admission granted to:"+username, Toast.LENGTH_SHORT).show();
    }
}
