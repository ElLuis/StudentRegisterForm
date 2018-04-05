package com.centennial.elluis.studentstaff.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DBHelper extends SQLiteOpenHelper {

    //Database constants
    public static final String DATABASE_NAME = "studentDB";
    static final int DATABASE_VERSION = 11; //upgrade database
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
        //Create Staff Table
        db.execSQL(staffTable.DATABASE_CREATE_STAFF);
        //insert staff
        db.execSQL(staffTable.CREATE_STAFF1);
        db.execSQL(staffTable.CREATE_STAFF2);
        db.execSQL(staffTable.CREATE_STAFF3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS students");
        db.execSQL("DROP TABLE IF EXISTS programs");
        db.execSQL("DROP TABLE IF EXISTS payments");
        db.execSQL("DROP TABLE IF EXISTS staff");
        onCreate(db);
    }
}
