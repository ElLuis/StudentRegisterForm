package com.centennial.elluis.studentstaff.database;


public class programTable {

    //Program table fields
    public static final String PK_PROGRAM_CODE = "programCode";
    public static final String KEY_PROGRAM_NAME = "programName";
    public static final String KEY_TUITION_FEE = "tuitionFee";
    public static final String KEY_DURATION = "duration";
    public static final String KEY_SEMESTER = "semester";

    //programs query
    public static final String DATABASE_CREATE_PROGRAMS =
            "create table if not exists programs (programCode integer primary key, programName text not null, tuitionFee integer not null, duration integer not null," +
                    "semester integer);";
    //programs values
    public static final String CREATE_PROGRAM_1 =
            "INSERT INTO programs (programCode, programName, tuitionFee, duration, semester)" +
                    "VALUES (3419, 'Software Engineering Technology', 25000,3,9);";
    public static final String CREATE_PROGRAM_2 =
            "INSERT INTO programs (programCode, programName, tuitionFee, duration, semester)" +
                    "VALUES (3408, 'Software Engineering Technician', 24000,3,9);";
    public static final String CREATE_PROGRAM_3 =
            "INSERT INTO programs (programCode, programName, tuitionFee, duration, semester)" +
                    "VALUES (3506, 'Medical Laboratory Technician', 20000,2,6);";
    
}
