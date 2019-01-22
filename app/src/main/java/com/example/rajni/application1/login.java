package com.example.rajni.application1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class login extends AppCompatActivity {
    EditText uid,pwd;
    Button login;
    TextView reg,fpwd;
    Spanned text;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        uid = (EditText)findViewById(R.id.uid);
        pwd = (EditText)findViewById(R.id.pwd);
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
}
