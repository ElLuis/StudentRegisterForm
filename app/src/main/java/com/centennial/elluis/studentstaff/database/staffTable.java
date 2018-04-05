package com.centennial.elluis.studentstaff.database;

public class staffTable {
    public static final String KEY_FIRST_NAME = "firstName";
    public static final String KEY_LAST_NAME = "lastName";
    public static final String PK_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";

    public static final String DATABASE_CREATE_STAFF =
            "create table if not exists staff (username text primary key not null, password text not null, firstName text, lastName text);";

    public static final String CREATE_STAFF1 = "INSERT INTO staff (username, password, firstName, lastName)" +
            "VALUES ('vvaithi3user', 'vvaithi3', 'Vinay','Vaithilingam');";

    public static final String CREATE_STAFF2 = "INSERT INTO staff (username, password, firstName, lastName)" +
            "VALUES ('ismithuser', 'ismith', 'Ingrid','Smith');";

    public static final String CREATE_STAFF3 = "INSERT INTO staff (username, password, firstName, lastName)" +
            "VALUES ('fishiiuser', 'fishii', 'Ishii','Frances');";
}
