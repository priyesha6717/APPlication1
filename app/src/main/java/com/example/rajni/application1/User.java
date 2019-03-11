package com.example.rajni.application1;

import android.location.Geocoder;

import com.google.android.gms.common.api.GoogleApiClient;

public class User {

    private String  EmployeeID, ContactNo, EmailID, Password,Birthdate, Firstname, Middlename, Lastname,Gender;

    public User(String Fname, String Mname, String Lname, String Bdate, String gender, String EID, String EmID, String Contact, String Pwd) {
        this.Firstname = Fname;
        this.Middlename = Mname;
        this.Lastname = Lname;
        this.Birthdate = Bdate;
        this.Gender = gender;
        this.EmployeeID = EID;
        this.EmailID = EmID;
        this.ContactNo = Contact;
        this.Password = Pwd;

    }

    public String getFirstname() {
        return Firstname;
    }

    public String getMiddlename () {
        return Middlename;
    }

    public String getLastname() {
        return Lastname;
    }

    public String getBirthdate() {
        return Birthdate;
    }

    public String getGender() {
        return Gender;
    }

    public String getEmployeeID() {
        return EmployeeID;
    }

    public String getEmailID() {
        return EmailID;
    }

    public String getContactNo() {
        return ContactNo;
    }

    public String getPassword() {
        return Password;
    }


}
