package com.centennial.elluis.studentstaff;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void studentBtn_OnClick(View view) {
    }

    public void staffBtn_OnClick(View view) {
        Intent intent = new Intent(this, StaffLoginActivity.class);
        startActivity(intent);
    }
}
