package com.centennial.elluis.studentstaff;


public class Program extends Student {

    private String programName;
    private String programCode;

    public Program(String firstName, String lastName, String dateOfBirth, String username, String password, String nameOfProgram, String codeOfProgram) {
        super(firstName, lastName, dateOfBirth, username, password);

        this.programName = nameOfProgram;
        this.programCode = codeOfProgram;

    }

    public void setProgramName(String value)
    {
        programName = value;
    }
    public String getProgramName()
    {
        return programName;
    }
    public void setProgramCode(String value)
    {
        programCode = value;
    }
    public String getProgramCode()
    {
        return programCode;
    }



}
