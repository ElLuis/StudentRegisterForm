package com.centennial.elluis.studentstaff;


import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Student {

    private String fName;
    private String lName;
    private String dob;
    private String username;
    private String password;
    private String[] usernames;
    private String[]passwords;

    private int counter = 0;

    private ArrayList<Student> students;

    //Properties
    public void setFirstName(String value)
    {
        fName = value;
    }
    public String getFirstName()
    {
        return fName;
    }
    public void setLastName(String value)
    {
        lName = value;
    }
    public String getLastName()
    {
        return lName;
    }
    public void setDob(String value)
    {
        dob = value;
    }
    public String getDob()
    {
        return dob;
    }
    public void setUsername(String value)
    {
        username = value;
    }
    public String getUsername()
    {
        return username;
    }
    public void setPassword(String value)
    {
        password = value;
    }
    public String getPassword()
    {
        return password;
    }

    //parameterless constructor
    public Student()
    {}

    //constructor
    public Student(String firstName, String lastName, String dateOfBirth, String username, String password)
    {
        this.fName = firstName;
        this.lName = lastName;
        this.dob = dateOfBirth;
        this.username = username;
        this.password = password;

        //increase counter
        counter++;

        //Save username and passwords
        usernames[counter] = username;
        passwords[counter] = password;
    }

    public void AddStudent(Student newStudent)
    {
        students = new ArrayList<Student>() {

        };

        students.add(newStudent);
        }

    public Student getAllStudents() {
        for (Student s : students
                ) {
            return s;
        }
        return null;
    }

    //Find username and passwords
    public String[] FindUsername() {
        return usernames;
    }

        public String[] FindPasswords()
    {
        return passwords;
    }
 }


