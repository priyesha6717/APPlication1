package com.example.rajni.application1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login1 extends AppCompatActivity implements TextWatcher,CompoundButton.OnCheckedChangeListener {
    EditText uid1,pwd1;
    Button login1;
    TextView reg1,fpwd1;
    Spanned text1;
    CheckBox cb1;
    private SharedPreferences mpref1;
    private static final String PREF_Name="prefsfile1";
    SharedPreferences.Editor editor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);
        uid1 = (EditText)findViewById(R.id.uid1);
        pwd1 = (EditText)findViewById(R.id.pwd1);
        cb1 = (CheckBox)findViewById(R.id.rm1);
        mpref1 = getSharedPreferences(PREF_Name,MODE_PRIVATE);
        editor= mpref1.edit();
        if(mpref1.getBoolean("pref_check",false))
            cb1.setChecked(true);
        else
            cb1.setChecked(false);
        uid1.setText(mpref1.getString("pref_name",""));
        pwd1.setText(mpref1.getString("pref_pwd",""));
        uid1.addTextChangedListener( this);
        pwd1.addTextChangedListener(this);
        cb1.setOnCheckedChangeListener( this);

        login1 = (Button)findViewById(R.id.login1);
        reg1 = (TextView)findViewById(R.id.reg1);
        fpwd1 = (TextView)findViewById(R.id.fpwd1);


        text1= Html.fromHtml("<a href='https://www.google.co.in//'>Forget your password?</a>");
        fpwd1.setMovementMethod(LinkMovementMethod.getInstance());
        fpwd1.setText(text1);
        reg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(login1.this,registration_doctor.class);
                startActivity(i1);
            }
        });
        login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(uid1.getText().toString().trim().length()==0){
                    uid1.setError("Enter your userID");
                    uid1.requestFocus();
                }
                if(pwd1.getText().toString().trim().length()==0){
                    pwd1.setError("Enter your password");
                    pwd1.requestFocus();
                }
                else{
                    Intent intent1=new Intent(login1.this,profile_doctor.class);
                    startActivity(intent1);
                }
            }
        });
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        abc();;
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        abc();
    }
    private void abc(){
        if(cb1.isChecked()){
            editor.putString("pref_name",uid1.getText().toString().trim());
            editor.putString("pref_pwd",pwd1.getText().toString().trim());
            editor.putBoolean("pref_check",true);
            editor.apply();
//            Toast.makeText(getApplicationContext(),"Setting have been saved",Toast.LENGTH_SHORT).show();

        }else{
            editor.remove("pref_name");
            editor.remove("pref_pwd");
            editor.putBoolean("pref_check",false);
            editor.apply();

        }
    }
}