package com.example.rajni.application1;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import android.widget.TextView;

import java.util.Calendar;

public class registration extends AppCompatActivity {
    TextView lg;
    Button next,cancle;
    private  static final String tag="dob";
    private EditText dob,fname,mname,lname;
    Spinner gender;
    private DatePickerDialog.OnDateSetListener onDateSetListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        dob=(EditText) findViewById(R.id.dob);
        fname=(EditText) findViewById(R.id.fname);
        mname=(EditText) findViewById(R.id.mname);
        lname=(EditText) findViewById(R.id.lname);
        gender=(Spinner) findViewById(R.id.gender);
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year= cal.get(Calendar.YEAR);
                int month= cal.get(Calendar.MONTH);
                int day= cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog=new DatePickerDialog(registration.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,onDateSetListener,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });
        onDateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1 = i1 +1;
                Log.d(tag,"onDataset:mm/dd/yyyy:"+i1+"/"+i2+"/"+i);
                String date = i1+"/"+i2+"/"+i;
                dob.setText(date);
            }
        };
        lg=(TextView)findViewById(R.id.login);
        lg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(registration.this,login.class);
                startActivity(i);
            }
        });
        next=(Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fname.getText().toString().trim().length()==0){
                    fname.setError("Enter your First Name");
                    fname.requestFocus();
                }
                if(mname.getText().toString().trim().length()==0){
                    mname.setError("Enter your Middle Name");
                    mname.requestFocus();
                }
                if(lname.getText().toString().trim().length()==0){
                    lname.setError("Enter your Last Name");
                    lname.requestFocus();
                }
                if(dob.getText().toString().trim().length()==0){
                    dob.setError("Enter your BirthDate");
                    dob.requestFocus();
                }
                if(gender==null){
                    dob.setError("Select Gender");
                    dob.requestFocus();
                }
                else{
                    Intent intent=new Intent(registration.this,registration2.class);
                    startActivity(intent);
                }
            }
        });
        cancle=(Button) findViewById(R.id.cancle);
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fname.setText("");
                mname.setText("");
                lname.setText("");
                dob.setText("");
            }
        });

    }
}
