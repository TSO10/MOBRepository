package com.group4.eventhandler;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by Madsmikkelsen on 10/08/15.
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    static String date;
    Button btnDate;
    Context context;

    public DatePickerFragment(Context context){
        this.context=context;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        btnDate = (Button) ((Activity)context).findViewById(R.id.btn_date);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        int dayInt = view.getDayOfMonth();
        int monthInt = view.getMonth();
        int yearInt = view.getYear();
        String dayString = Integer.toString(dayInt);
        String monthString = Integer.toString(monthInt + 1);
        String yearString = Integer.toString(yearInt);

        date = dayString + "/" + monthString + "/" + yearString;
        btnDate.setText(date);

        // Do something with the date chosen by the user

    }

    public static String getDate() {
        return date;
    }

}
