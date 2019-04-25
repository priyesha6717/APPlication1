package com.example.rajni.application1;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
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
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends Activity {
    String[] names = {"Employee","Doctor","Safety Officer","Admin"};
    ArrayAdapter<String> adapter;
    TextView location,AboutUs,contactus;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        location=(TextView)findViewById(R.id.location) ;
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Main2Activity.this,MapsActivity.class);
                startActivity(intent);
            }
        });

    }
    public  void open(View v)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        View row=getLayoutInflater().inflate(R.layout.activity_popup,null);
        ListView l1=(ListView)row.findViewById(R.id.listview);

        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,names);
        l1.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        builder.setView(row);
        final AlertDialog dialog=builder.create();
        dialog.show();
        l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
              String selecteditem = (String)adapterView.getItemAtPosition(i);
//                Intent intent = new Intent(Main2Activity.this,login.class);
//                startActivity(intent);
                if(i == 0) {
                    Intent intent = new Intent(Main2Activity.this, login.class);
                    startActivity(intent);
                }
                if(i == 1) {
                    Intent intent = new Intent(Main2Activity.this, login1.class);
                    startActivity(intent);
                }
                if(i == 2) {
                    Intent intent = new Intent(Main2Activity.this, login2.class);
                    startActivity(intent);
                }
                if(i == 3) {
                    Intent intent = new Intent(Main2Activity.this, login3.class);
                    startActivity(intent);
                }
                dialog.cancel();
            }
        });
        contactus = (TextView)findViewById(R.id.contactus);
        contactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main2Activity.this, activity_contactus.class);
                startActivity(intent);

            }
        });
        AboutUs = (TextView)findViewById(R.id.aboutus);
        AboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main2Activity.this, AboutUs.class);
                startActivity(intent);
            }
        });
    }
}
