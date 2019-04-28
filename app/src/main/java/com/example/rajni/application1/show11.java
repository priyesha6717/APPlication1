package com.example.rajni.application1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class show11 extends AppCompatActivity {

    TextView name11, employee11, email11, contact11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show11);

        name11 = (TextView)findViewById(R.id.name12);
        email11 = (TextView)findViewById(R.id.email12);
        employee11 = (TextView)findViewById(R.id.employee12);
        contact11 = (TextView)findViewById(R.id.phone12);

        User user = SharedPrefManager.getInstance(getApplicationContext()).getUser();

        //setting the values to the textviews
        name11.setText(user.getFirstname());
        employee11.setText(user.getEmployeeID());
        email11.setText(user.getEmailID());
        contact11.setText(user.getContactNo());

    }
}
