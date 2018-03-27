package com.centennial.elluis.studentstaff.database;



public class paymentTable {
    //Payment table fields
    public static final String FK_STUDENT_ID$ = "studentID";
    public static final String FK_PROGRAM_CODE$ = "programCode";
    public static final String KEY_TOTAL_AMOUNT_ = "totalAmount";
    public static final String KEY_AMOUNT_PAID = "amountPaid";
    public static final String KEY_BALANCE = "balance";
    public static final String KEY_STATUS = "status";

    //payments query
    public static final String DATABASE_CREATE_PAYMENTS =
            "create table if not exists payments (programCode integer, studentID integer," +
                    "totalAmount integer, amountPaid integer, balance integer,status text default 'In-progress', foreign key(programCode) references programs(programCode)," +
                    "foreign key(studentID) references students(username)); ";
}

