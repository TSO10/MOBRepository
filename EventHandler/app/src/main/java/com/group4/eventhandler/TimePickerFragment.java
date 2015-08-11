package com.group4.eventhandler;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.Button;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by Madsmikkelsen on 10/08/15.
 */
public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    static String time;
    Context context;
    Button btnTime;

    public TimePickerFragment(Context context) {
        this.context = context;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        btnTime = (Button) ((Activity) context).findViewById(R.id.btn_time);
        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));

    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Do something with the time chosen by the user
        int hourInt = view.getCurrentHour();
        int minuteInt = view.getCurrentMinute();
        String hourString = Integer.toString(hourInt);
        String minuteString = Integer.toString(minuteInt);
        if (minuteInt == 0) {
            time = hourString + ":" + minuteString + "0";
            btnTime.setText(time);
        } else {
            time = hourString + ":" + minuteString;
            btnTime.setText(time);
        }
    }

}
