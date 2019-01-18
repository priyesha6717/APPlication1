package com.example.rajni.application1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.*;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {
    private static int Splash_time_out = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
          new Handler().postDelayed(new Runnable(){
              @Override
              public void run()
              {
                  Intent i = new Intent(MainActivity.this,Main2Activity.class);
                  startActivity(i);
                  finish();
              }

          },Splash_time_out);


    }


}
