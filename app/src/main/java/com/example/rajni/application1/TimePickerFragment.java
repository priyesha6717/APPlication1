package com.example.rajni.application1;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class TimePickerFragment extends DialogFragment {
    TimePickerDialog.OnTimeSetListener ontimeSet;
    private int  min , hour ;

    public TimePickerFragment() {}

    public void setCallBack1(TimePickerDialog.OnTimeSetListener ontime)
    {
        ontimeSet = ontime;
    }

    @SuppressLint("NewApi")
    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        min = args.getInt("min");
        hour = args.getInt("hour");
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), ontimeSet, hour, min,true);
        return timePickerDialog;
    }
}