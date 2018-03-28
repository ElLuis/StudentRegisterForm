package com.centennial.elluis.studentstaff.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DBHelper extends SQLiteOpenHelper {

    //Database constants
    public static final String DATABASE_NAME = "studentDB";
    static final int DATABASE_VERSION = 8; //upgrade database
    static final String TAG = "DBHelper";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(studentTable.DATABASE_CREATE_STUDENTS);
        db.execSQL(programTable.DATABASE_CREATE_PROGRAMS);
        //insert programs
        db.execSQL(programTable.CREATE_PROGRAM_1);
        db.execSQL(programTable.CREATE_PROGRAM_2);
        db.execSQL(programTable.CREATE_PROGRAM_3);
        //Create Payment Table
        db.execSQL(paymentTable.DATABASE_CREATE_PAYMENTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS students");
        db.execSQL("DROP TABLE IF EXISTS programs");
        db.execSQL("DROP TABLE IF EXISTS payments");
        onCreate(db);
    }
}
