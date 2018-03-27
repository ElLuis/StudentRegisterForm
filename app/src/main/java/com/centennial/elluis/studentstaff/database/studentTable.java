package com.centennial.elluis.studentstaff.database;


public class studentTable {

    //Student table fields
     public static final String KEY_FIRST_NAME = "firstName";
    public static final String KEY_LAST_NAME = "lastName";
    public static final String KEY_CITY = "city";
    public static final String PK_USERNAME = "username";
    public static final String FK_PROGRAM_CODE = "programCode";
    public static final String KEY_PASSWORD = "password";

    // Create students query

    //TEST
    /*public static final String DATABASE_CREATE_STUDENTS =
            "create table students (username integer primary key not null," //username is int
                    + "firstName text not null, lastName text not null, city text, password text not null);";*/
    //TARGET
    public static final String DATABASE_CREATE_STUDENTS =
            "create table if not exists students (username integer primary key not null, programCode integer, " //username is int
                    + "firstName text not null, lastName text not null, city text, password text not null,foreign key(programCode) references programs(programCode));";

    //Update Student with corresponding programCode

}
