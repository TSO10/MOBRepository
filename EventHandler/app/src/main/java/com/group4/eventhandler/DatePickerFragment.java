package com.group4.eventhandler;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by Madsmikkelsen on 10/08/15.
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    static String date;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        int dayInt = view.getDayOfMonth();
        int monthInt = view.getMonth();
        int yearInt = view.getYear();
        String dayString = Integer.toString(dayInt);
        String monthString = Integer.toString(monthInt);
        String yearString = Integer.toString(yearInt);

        date = dayString + "/" + monthString + " " + yearString;


        // Do something with the date chosen by the user

    }

    public static String getDate()
    {
        return date;
    }

}
