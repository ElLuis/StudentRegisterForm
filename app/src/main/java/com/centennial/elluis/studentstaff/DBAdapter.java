package com.centennial.elluis.studentstaff;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DBAdapter {
    //Student table fields
    static final String KEY_FIRST_NAME = "firstName";
    static final String KEY_LAST_NAME = "lastName";
    static final String KEY_CITY = "city";
    static final String KEY_USERNAME = "username";
    static final String KEY_PASSWORD = "password";

    //Program table fields
    static final String PK_PROGRAM_CODE = "programCode";
    static final String KEY_PROGRAM_NAME = "programName";
    static final String KEY_TUITION_FEE = "tuitionFee";
    static final String KEY_DURATION = "duration";
    static final String KEY_SEMESTER = "semester";

    //Payment table fields
    static final String FK_STUDENT_ID = "studentID";
    static final String FK_PROGRAM_CODE = "programCode";
    static final String KEY_TOTAL_AMOUNT_ = "totalAmount";
    static final String KEY_AMOUNT_PAID = "amountPaid";
    static final String KEY_BALANCE = "balance";
    static final String KEY_STATUS = "status";

    static final String TAG = "DBAdapter";
    static final String DATABASE_NAME = "MyDB";
    static final String STUDENT_TABLE = "students";
    static final String PROGRAM_TABLE = "programs";
    static final String PAYMENT_TABLE = "payments";
    static final int DATABASE_VERSION = 1; //upgrade database

    //students query
    static final String DATABASE_CREATE_STUDENTS =
            "create table students (username integer primary key not null, " //username is int
                    + "firstName text not null, lastName text not null, city text, password text not null);";

    //programs query
    static final String DATABASE_CREATE_PROGRAMS =
            "create table programs (programCode integer primary key, programName text not null, tuitionFee integer not null, duration integer not null," +
                    "semester integer);";

    //payments query
    static final String DATABASE_CREATE_PAYMENTS =
            "create table payments (studentID integer foreign key references students(username), programCode integer foreign key references programs(programCode)," +
                    "totalAmount integer, amountPaid integer, balance integer,status text default 'In-Process'); ";
    final Context context;
    DatabaseHelper DBHelper;
    SQLiteDatabase db;
    public DBAdapter(Context ctx)
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }
    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db)
        {
            try {
                db.execSQL(DATABASE_CREATE_STUDENTS);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS contacts");
            onCreate(db);
        }
    }
    //---opens the database---
    public DBAdapter open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }
    //---closes the database---
    public void close()
    {
        DBHelper.close();
    }
    //---insert a contact into the database---
    public long insertStudent(String username, String firstName, String lastName, String city, String password)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_USERNAME, username);
        initialValues.put(KEY_FIRST_NAME, firstName);
        initialValues.put(KEY_LAST_NAME, lastName);
        initialValues.put(KEY_CITY, city);
        initialValues.put(KEY_PASSWORD, password);
        return db.insert(STUDENT_TABLE, null, initialValues);
    }

    //---retrieves a particular contact---
    public Cursor getStudent(long id_username) throws SQLException
    {
        Cursor mCursor =
                db.query(true, STUDENT_TABLE, new String[] {KEY_USERNAME,
                                KEY_FIRST_NAME, KEY_LAST_NAME, KEY_CITY},
                        KEY_USERNAME + "=" + id_username, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public Cursor validateStudentPassword(long id_username) throws SQLException
    {
        Cursor mCursor =
                db.query(true, STUDENT_TABLE, new String[] {KEY_PASSWORD},
                        KEY_USERNAME + "=" + id_username, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
    //---updates a contact---
/*    public boolean updateContact(long rowId, String name, String email)
    {
        ContentValues args = new ContentValues();
        args.put(KEY_NAME, name);
        args.put(KEY_EMAIL, email);
        return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }*/
}
