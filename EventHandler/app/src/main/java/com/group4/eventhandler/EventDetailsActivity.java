package com.group4.eventhandler;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;

import webservice.JSONParser;
import webservice.RestAPI;


public class EventDetailsActivity extends ActionBarActivity {

    TextView tvid, tvheadline, tvdescription, tvtime, tvimageUrl;
    Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        tvid = (TextView) findViewById(R.id.tv_id);
        tvheadline = (TextView) findViewById(R.id.tv_headline);
        tvdescription = (TextView) findViewById(R.id.tv_description);
        tvtime = (TextView) findViewById(R.id.tv_time);
        tvimageUrl = (TextView) findViewById(R.id.tv_imageUrl);

        Intent i = getIntent();
        String id = i.getStringExtra("id");

        new AsyncEventDetails(getApplicationContext(), new FetchEventTaskCompleteListener()).execute();
    }

    public class FetchEventTaskCompleteListener implements iAsyncTaskCompleteListener<Event> {
        @Override
        public void onTaskComplete(Event result) {

            tvid.setText(Integer.toString(result.getId()));
            tvheadline.setText(result.getHeadline());
            tvdescription.setText(result.getDescription());
            tvtime.setText(result.getTime());
            tvimageUrl.setText(result.getImageUrl());
        }
    }


}
