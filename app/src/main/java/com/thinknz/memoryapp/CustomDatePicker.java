package com.thinknz.memoryapp;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

/**
 * Created by liamtubs on 27/07/2016.
 */
public class CustomDatePicker extends DatePickerDialog{

    public CustomDatePicker(Context context, int theme,OnDateSetListener callBack,
                                 int year, int monthOfYear, int dayOfMonth) {
        super(context, theme, callBack, year, monthOfYear, dayOfMonth);

        setButton(BUTTON_POSITIVE, ("Ok"), this);
        setButton(BUTTON_NEUTRAL, ("Today's Date"), this);
        setButton(BUTTON_NEGATIVE, ("Cancel"), this);
    }
}
