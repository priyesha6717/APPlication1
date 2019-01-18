package com.example.rajni.application1;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ClipDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends Activity {

    TextView utype ;
    private LinearLayout l;
    private  PopupWindow p;
    TextView t1,t2,t3,t4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
         l = (LinearLayout)findViewById(R.id.r1);
        utype = (TextView)findViewById(R.id.ut1);
        utype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = (LayoutInflater) Main2Activity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View customView = layoutInflater.inflate(R.layout.activity_popup,null);

                p = new PopupWindow(customView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                p.setOutsideTouchable(true);
                p.setFocusable(true);
                p.setAnimationStyle(R.anim.myanim);
                t1 = (TextView)customView.findViewById(R.id.em);
                t1.setOnClickListener(new View.OnClickListener() {
            @Override
                 public void onClick(View view) {
                Intent i = new Intent(Main2Activity.this,login.class);
                startActivity(i);
            }
        });
                t2 = (TextView)customView.findViewById(R.id.d1);
                t2.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("ResourceAsColor")
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(Main2Activity.this,login.class);
                        startActivity(i);

                    }
                });
                t3 = (TextView)customView.findViewById(R.id.sa);
                t3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(Main2Activity.this,login.class);
                        startActivity(i);
                    }
                });
                t4 = (TextView)customView.findViewById(R.id.ad);
                t4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(Main2Activity.this,login.class);
                        startActivity(i);
                    }
                });


                p.showAtLocation(l, Gravity.CENTER,0,0);


            }
        });


    }
}
