package com.group4.eventhandler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import webservice.RestAPI;


public class CreateEventActivity extends Activity {
    EditText etHeadline, etDescription, etTime, etImageUrl;
    Button btnCreateEvent;
    Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        ctx = getApplicationContext();
        etHeadline = (EditText) findViewById(R.id.et_headline);
        etDescription = (EditText) findViewById(R.id.et_description);
        etTime = (EditText) findViewById(R.id.et_time);
        etImageUrl = (EditText) findViewById(R.id.et_imageUrl);
        btnCreateEvent = (Button) findViewById(R.id.btn_createEvent);

        btnCreateEvent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                String headline, description, time, imageUrl;

                headline = etHeadline.getText().toString();
                description = etDescription.getText().toString();
                time = etTime.getText().toString();
                imageUrl = etImageUrl.getText().toString();

                Event event = new Event(headline,
                        description, time, imageUrl);


                new AsyncCreateEvent(ctx, new FetchCreateEventTaskCompleteListener()).execute(event);

            }
        });
    }

    public class FetchCreateEventTaskCompleteListener implements iAsyncTaskCompleteListener<Void> {

        @Override
        public void onTaskComplete(Void result) {
            Intent intent = new Intent(ctx, FeedActivity.class);
            startActivity(intent);
            finish();
        }
    }
}

