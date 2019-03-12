package com.example.rajni.application1;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class event3 extends Fragment {
    TextView v2,v1,v3,v5,v4,v7;
    LinearLayout l11;
    Button b1,b2,c1,ch1,t1;
    int i;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanseState){
        return inflater.inflate(R.layout.event3,container,false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        v2 = (TextView) getView().findViewById(R.id.v2_so);
        v7 = (TextView) getView().findViewById(R.id.v7_so);
        v3 = (TextView) getView().findViewById(R.id.v3_so);
        v5 = (TextView) getView().findViewById(R.id.v4_so);
        v4 = (TextView) getView().findViewById(R.id.v5_so);
        b1 = (Button) getView().findViewById(R.id.button_so);
        c1 = (Button) getView().findViewById(R.id.cancle11_so);
        t1 = (Button) getView().findViewById(R.id.apt_so);
        ch1 = (Button) getView().findViewById(R.id.change11_so);
        b2 = (Button) getView().findViewById(R.id.b2_so);
        l11 =(LinearLayout)getView().findViewById(R.id.l1_so);
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
            v4.setText("Venu : Ahmedabad ");
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
