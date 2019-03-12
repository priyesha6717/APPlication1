package com.example.rajni.application1;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class profile_admin1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_admin1);
        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation_admin);
        loadFragment(new home4());
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment SF = null;
                switch (item.getItemId()){
                    case R.id.home :
                        setTitle("Home");
                        SF = new home4();
                        break;
                    case R.id.event :
                        setTitle("Event");
                        SF = new event4();
                        break;
                    case R.id.appointment :
                        setTitle("Appointment");
                        SF = new appointment4();
                        break;
                    case R.id.profile :
                        setTitle("Profile");
                        SF = new profile4();
                        break;
                }

                return loadFragment(SF);
            }
        });
    }
    private boolean loadFragment(Fragment fragment)
    {
        if(fragment!=null){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame4,fragment).commit();
            return true;
        }
        return false;
    }
}
