package com.example.rajni.application1;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.graphics.drawable.DrawableWrapper;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class event1 extends Fragment {

    TextView v2,v1,v3,v5,v4,v7;
    LinearLayout l11;
    Button b1,b2,c1,ch1,t1,docprof;
    int i;

//    private SharedPreferences mpref;
//    private static final String PREF_Name="prefsfile";
//    SharedPreferences.Editor editor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.event1, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        v2 = (TextView) getView().findViewById(R.id.v2);
        v7 = (TextView) getView().findViewById(R.id.v7);
        v3 = (TextView) getView().findViewById(R.id.v3);
        v5 = (TextView) getView().findViewById(R.id.v4);
        v4 = (TextView) getView().findViewById(R.id.v5);
        b1 = (Button) getView().findViewById(R.id.button);
        c1 = (Button) getView().findViewById(R.id.cancle11);
        t1 = (Button) getView().findViewById(R.id.apt);
        ch1 = (Button) getView().findViewById(R.id.change11);
        b2 = (Button) getView().findViewById(R.id.b2);
        l11 =(LinearLayout)getView().findViewById(R.id.l1);
        docprof = (Button) getView().findViewById(R.id.docprof) ;
        //  im=(ImageView)getView().findViewById(R.id.img1);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                showDatePicker();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                showTimePicker();
            }
        });

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                canclefun();
            }
        });

        ch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                changefun();
            }
        });

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                b1.setVisibility(View.VISIBLE);
                b2.setVisibility(View.VISIBLE);
                t1.setVisibility(View.GONE);
                docprof.setVisibility(View.GONE);
            }
        });

    }

    private void showDatePicker()
    {
        DatePickerFragment date = new DatePickerFragment();
        /**
         * Set Up Current Date Into dialog
         */
        Calendar calender = Calendar.getInstance();
        Bundle args = new Bundle();
        args.putInt("year", calender.get(Calendar.YEAR));
        args.putInt("month", calender.get(Calendar.MONTH));
        args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
        date.setArguments(args);
        /**
         * Set Call back to capture selected date
         */
        date.setCallBack(ondate);
        date.show(getFragmentManager(), "Date Picker");
    }

    private void showTimePicker() {

        TimePickerFragment date1= new TimePickerFragment();
        /**
         * Set Up Current Date Into dialog
         */
        Calendar calender = Calendar.getInstance();
        Bundle args = new Bundle();
        args.putInt("min", calender.get(Calendar.MINUTE));
        args.putInt("hour", calender.get(Calendar.HOUR_OF_DAY));
        date1.setArguments(args);
        /**
         * Set Call back to capture selected date
         */
        date1.setCallBack1(ontime);
        date1.show(getFragmentManager(), "Time Picker");

    }

    DatePickerDialog.OnDateSetListener ondate = new DatePickerDialog.OnDateSetListener()
    {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
        {
            v3.setText("Date : " + String.valueOf(dayOfMonth) + "-" + String.valueOf(monthOfYear + 1)
                    + "-" + String.valueOf(year));
        }
    };
    TimePickerDialog.OnTimeSetListener ontime = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute)
        {
            v5.setText("Time : " + String.valueOf(hourOfDay) + ":" + String.valueOf(minute));
            v2.setVisibility(View.VISIBLE);
            v3.setVisibility(View.VISIBLE);
            v4.setVisibility(View.VISIBLE);
            v5.setVisibility(View.VISIBLE);
            v7.setVisibility(View.VISIBLE);
            b1.setVisibility(View.GONE);
            b2.setVisibility(View.GONE);
            c1.setVisibility(View.VISIBLE);
            ch1.setVisibility(View.VISIBLE);
            v4.setText("Venue : Ahmedabad ");
            l11.setBackgroundColor(Color.parseColor("#ffffff"));
        }
    };

    public void canclefun()
    {
        v2.setVisibility(View.GONE);
        v3.setVisibility(View.GONE);
        v4.setVisibility(View.GONE);
        v5.setVisibility(View.GONE);
        v7.setVisibility(View.GONE);
        t1.setVisibility(View.VISIBLE);
        docprof.setVisibility(View.VISIBLE);
        c1.setVisibility(View.GONE);
        ch1.setVisibility(View.GONE);
        l11.setBackgroundResource(R.drawable.img11);
    }

    public void changefun()
    {
        c1.setVisibility(View.GONE);
        ch1.setVisibility(View.GONE);
        b1.setVisibility(View.VISIBLE);
        b2.setVisibility(View.VISIBLE);
    }
}
//        mpref = this.getActivity().getSharedPreferences(PREF_Name, Context.MODE_PRIVATE);
//        editor=mpref.edit();
//
//        v2.setText(mpref.getString("pref_title",""));
//        v3.setText(mpref.getString("pref_date",""));
//        v4.setText(mpref.getString("pref_venu",""));
//        v5.setText(mpref.getString("pref_time",""));
//        v7.setText(mpref.getString("pref_note",""));
//
//        editor.putString("pref_title",v2.getText().toString().trim());
//        editor.putString("pref_date",v3.getText().toString().trim());
//        editor.putString("pref_venu",v4.getText().toString().trim());
//        editor.putString("pref_time",v5.getText().toString().trim());
//        editor.putString("pref_note",v7.getText().toString().trim());
//        editor.apply();