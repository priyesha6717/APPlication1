package com.example.rajni.application1;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;

public class registration2 extends AppCompatActivity {
    EditText employeeid,contactno,emailid,password2,cpassword2;
    Button submit,cancle2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration2);
        employeeid=(EditText)findViewById(R.id.eid);
        emailid=(EditText)findViewById(R.id.email);
        contactno=(EditText)findViewById(R.id.phnno);
        password2=(EditText)findViewById(R.id.password);
        cpassword2=(EditText)findViewById(R.id.cpassword);
        submit=(Button) findViewById(R.id.submit);
        cancle2=(Button) findViewById(R.id.cancle2);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(employeeid.getText().toString().trim().length()==0){
                    employeeid.setError("Enter your ID");
                    employeeid.requestFocus();
                }
                if(emailid.getText().toString().trim().length()==0){
                    emailid.setError("Enter your EmailID");
                    emailid.requestFocus();
                }
                if(contactno.getText().toString().trim().length()==0){
                    contactno.setError("Enter your Contact No.");
                    contactno.requestFocus();
                }
                if(password2.getText().toString().trim().length()==0){
                    password2.setError("Enter your Password");
                    password2.requestFocus();
                }
                if(cpassword2.getText().toString().trim().length()==0){
                    cpassword2.setError("Confirm your Password");
                    cpassword2.requestFocus();
                }
                else{
                    Intent intent=new Intent(registration2.this,profile.class);
                    startActivity(intent);
                }
            }
        });
        cancle2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailid.setText("");
                employeeid.setText("");
                contactno.setText("");
                password2.setText("");
                cpassword2.setText("");
            }
        });

    }

}
