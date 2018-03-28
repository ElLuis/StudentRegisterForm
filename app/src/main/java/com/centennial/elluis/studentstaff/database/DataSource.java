package com.centennial.elluis.studentstaff.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataSource {
    private Context mContext;
    private SQLiteDatabase mDatabase;
    SQLiteOpenHelper mDBHelper;


    //Name of tables
    static final String STUDENT_TABLE = "students";
    static final String PROGRAM_TABLE = "programs";
    static final String PAYMENT_TABLE = "payments";

    public DataSource(Context context)
    {
        this.mContext = context;
        mDBHelper = new DBHelper(mContext);
        mDatabase = mDBHelper.getWritableDatabase();
    }

    public void open()
    {
        mDatabase= mDBHelper.getWritableDatabase();
    }
    public void close()
    {
        mDBHelper.close();
    }

    /*                                                              STUDENTS                                         */

    //---insert a student into the database---
    public long insertStudent(String username, String firstName, String lastName, String city, String password)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(studentTable.PK_USERNAME, Integer.parseInt(username));
        initialValues.put(studentTable.FK_PROGRAM_CODE,3419); //testing FK
        initialValues.put(studentTable.KEY_FIRST_NAME, firstName);
        initialValues.put(studentTable.KEY_LAST_NAME, lastName);
        initialValues.put(studentTable.KEY_CITY, city);
        initialValues.put(studentTable.KEY_PASSWORD, password);
        return mDatabase.insert(STUDENT_TABLE, null, initialValues);
    }

    //---retrieves a particular student---
    public Cursor getStudent(int id_username) throws SQLException
    {
        Cursor mCursor =
                mDatabase.query(true, STUDENT_TABLE, new String[] {studentTable.PK_USERNAME,
                                studentTable.KEY_FIRST_NAME, studentTable.KEY_LAST_NAME, studentTable.KEY_CITY,studentTable.KEY_PASSWORD,studentTable.FK_PROGRAM_CODE},
                        studentTable.PK_USERNAME + "=" + id_username, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }


    //check username and password match
    public Cursor validateStudentPassword(Integer id_username) throws SQLException
    {
        Cursor mCursor =
                mDatabase.query(true, STUDENT_TABLE, new String[] {studentTable.KEY_PASSWORD},
                        studentTable.PK_USERNAME + "=" + id_username, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
    //---updates a student---
    public boolean updateStudentProgram(int username, int programCode)
    {
        ContentValues args = new ContentValues();
        args.put(studentTable.FK_PROGRAM_CODE, programCode);
        return mDatabase.update(STUDENT_TABLE, args, studentTable.PK_USERNAME + "=" + username, null) > 0;
    }


                      /*                                              PROGRAMS                                              */

    //---retrieves all the programs---
    public Cursor getAllPrograms()
    {
        return mDatabase.query(PROGRAM_TABLE, new String[] {programTable.PK_PROGRAM_CODE, programTable.KEY_PROGRAM_NAME,
                        programTable.KEY_TUITION_FEE,programTable.KEY_DURATION,programTable.KEY_SEMESTER},
                null, null, null, null, null);
    }

    public Cursor getProgram(int program_code) throws SQLException
    {
        Cursor mCursor =
                mDatabase.query(true, PROGRAM_TABLE, new String[] {programTable.PK_PROGRAM_CODE,
                                programTable.KEY_PROGRAM_NAME, programTable.KEY_TUITION_FEE, programTable.KEY_DURATION, programTable.KEY_SEMESTER},
                        programTable.PK_PROGRAM_CODE + "=" + program_code, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public Cursor getProgramTuitionFee(int program_code) throws SQLException
    {
        Cursor mCursor =
                mDatabase.query(true, PROGRAM_TABLE, new String[] {programTable.KEY_TUITION_FEE},
                        programTable.PK_PROGRAM_CODE + "=" + program_code, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }


    /*                                                 PAYMENTS                                                                             */

    //---insert a student into the database---
    public long insertProgramTotalAmount(int username,int programCode, int totalAmount)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(paymentTable.FK_STUDENT_ID$, username);
        initialValues.put(paymentTable.FK_PROGRAM_CODE$,programCode); //testing FK
        initialValues.put(paymentTable.KEY_TOTAL_AMOUNT_, totalAmount);
        initialValues.put(paymentTable.KEY_BALANCE, totalAmount);
        return mDatabase.insert(PAYMENT_TABLE, null, initialValues);
    }

    //---Update Payment---
    public boolean updatePaymentBalance(int username, int amountPaid, int balance)
    {
        ContentValues args = new ContentValues();
        args.put(paymentTable.KEY_AMOUNT_PAID, amountPaid);
        args.put(paymentTable.KEY_BALANCE, balance);
        return mDatabase.update(PAYMENT_TABLE, args, paymentTable.FK_STUDENT_ID$ + "=" + username, null) > 0;
    }

    //---Update Payment Status---
    public boolean grantAdmission(int username)
    {
        ContentValues args = new ContentValues();
        args.put(paymentTable.FK_STUDENT_ID$, username);
        args.put(paymentTable.KEY_STATUS, "Admission Granted");
        return mDatabase.update(PAYMENT_TABLE, args, paymentTable.FK_STUDENT_ID$ + "=" + username, null) > 0;
    }

    //retrieves all students in progress
    public Cursor getAllStudentsInProgress() {
        return mDatabase.query(PAYMENT_TABLE, new String[]{paymentTable.FK_STUDENT_ID$},
                paymentTable.KEY_STATUS + "="+"'In-progress'", null, null, null, null);
    }

    //---Get Payment Info---
    public Cursor getPayment(int username) throws SQLException
    {
        Cursor mCursor =
                mDatabase.query(true, PAYMENT_TABLE, new String[] {paymentTable.KEY_TOTAL_AMOUNT_,
                                paymentTable.KEY_AMOUNT_PAID, paymentTable.KEY_BALANCE, paymentTable.KEY_STATUS},
                        paymentTable.FK_STUDENT_ID$ + "=" + username, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }



}
