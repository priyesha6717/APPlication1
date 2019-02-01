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

public class login extends AppCompatActivity implements TextWatcher,CompoundButton.OnCheckedChangeListener {
    EditText uid,pwd;
    Button login;
    TextView reg,fpwd;
    Spanned text;
    CheckBox cb;
    private SharedPreferences mpref;
    private static final String PREF_Name="prefsfile";
    SharedPreferences.Editor editor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        uid = (EditText)findViewById(R.id.uid);
        pwd = (EditText)findViewById(R.id.pwd);
        cb = (CheckBox)findViewById(R.id.rm);
        mpref = getSharedPreferences(PREF_Name,MODE_PRIVATE);
         editor=mpref.edit();
        if(mpref.getBoolean("pref_check",false))
            cb.setChecked(true);
        else
            cb.setChecked(false);
       uid.setText(mpref.getString("pref_name",""));
        pwd.setText(mpref.getString("pref_pwd",""));
        uid.addTextChangedListener( this);
        pwd.addTextChangedListener(this);
        cb.setOnCheckedChangeListener( this);

        login = (Button)findViewById(R.id.login);
        reg = (TextView)findViewById(R.id.reg);
        fpwd = (TextView)findViewById(R.id.fpwd);


        text=Html.fromHtml("<a href='https://www.google.co.in//'>Forget your password?</a>");
        fpwd.setMovementMethod(LinkMovementMethod.getInstance());
        fpwd.setText(text);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(login.this,registration.class);
                startActivity(i);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(uid.getText().toString().trim().length()==0){
                    uid.setError("Enter your userID");
                    uid.requestFocus();
                }
                if(pwd.getText().toString().trim().length()==0){
                    pwd.setError("Enter your password");
                    pwd.requestFocus();
                }
                else{
                    Intent intent=new Intent(login.this,profile.class);
                    startActivity(intent);
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
        if(cb.isChecked()){
            editor.putString("pref_name",uid.getText().toString().trim());
            editor.putString("pref_pwd",pwd.getText().toString().trim());
            editor.putBoolean("pref_check",true);
            editor.apply();
            Toast.makeText(getApplicationContext(),"Setting have been saved",Toast.LENGTH_SHORT).show();

        }else{
            editor.remove("pref_name");
            editor.remove("pref_pwd");
            editor.putBoolean("pref_check",false);
            editor.apply();

        }
    }
}
