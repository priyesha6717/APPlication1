package com.example.rajni.application1;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class profile_safetyofficer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_safetyofficer);
        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation_so);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment SF = null;
                switch (item.getItemId()){
                    case R.id.home :
                        setTitle("Home");
                        SF = new home3();
                        break;
                    case R.id.event :
                        setTitle("Event");
                        SF = new event3();
                        break;
                    case R.id.appointment :
                        setTitle("Appointment");
                        SF = new appointment3();
                        break;
                    case R.id.profile :
                        setTitle("Profile");
                        SF = new profile3();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frame3,SF).commit();
                return true;
            }
        });
    }
}
