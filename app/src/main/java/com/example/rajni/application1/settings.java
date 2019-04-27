package com.example.rajni.application1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class settings extends AppCompatActivity {
    String iteams[]=new String[] {"My Security Setting","My Profile Setting","Notification","Privacy Policy","FAQs","Choose Language","Terms & Condition","Rate Us"};
    Integer[] symbol = {R.drawable.ic_lock_black_24dp,R.drawable.ic_person_black_24dp,R.drawable.ic_notifications_active_black_24dp,R.drawable.ic_border_color_black_24dp,R.drawable.ic_assignment_black_24dp,R.drawable.ic_g_translate_black_24dp,R.drawable.ic_import_contacts_black_24dp,R.drawable.ic_star_half_black_24dp};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ListView listView = (ListView) findViewById(R.id.listview);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,iteams);
        listView.setAdapter(adapter);
    }
}
