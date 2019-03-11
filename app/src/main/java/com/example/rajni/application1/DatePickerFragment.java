package com.example.rajni.application1;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class DatePickerFragment extends DialogFragment {
    DatePickerDialog.OnDateSetListener ondateSet;
    TimePickerDialog.OnTimeSetListener ontimeSet;
    private int year, month, day , min , hour ;

    public DatePickerFragment() {}

    public void setCallBack(DatePickerDialog.OnDateSetListener ondate)
    {
        ondateSet = ondate;
    }

    public void setCallBack1(TimePickerDialog.OnTimeSetListener ontime)
    {
        ontimeSet = ontime;
    }

    @SuppressLint("NewApi")
    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        year = args.getInt("year");
        month = args.getInt("month");
        day = args.getInt("day");
    }
    public void setArguments1(Bundle args) {
        super.setArguments(args);
        min = args.getInt("min");
        hour = args.getInt("hour");
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new DatePickerDialog(getActivity(), ondateSet, year, month, day);
    }
}