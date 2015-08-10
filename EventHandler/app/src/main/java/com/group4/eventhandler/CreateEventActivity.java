package com.group4.eventhandler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;

import webservice.RestAPI;


public class CreateEventActivity extends FragmentActivity {
    EditText etHeadline, etDescription;
    Button btnCreateEvent;
    Button btnLoadImage;
    ImageView targetImage;
    Button btnDate;
    Button btnTime;
    Uri targetUri;
    Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        ctx = getApplicationContext();
        etHeadline = (EditText) findViewById(R.id.et_headline);
        etDescription = (EditText) findViewById(R.id.et_description);
        btnCreateEvent = (Button) findViewById(R.id.btn_createEvent);
        btnLoadImage = (Button) findViewById(R.id.btn_load_image);
        targetImage = (ImageView) findViewById(R.id.targetimage);
        btnDate = (Button) findViewById(R.id.btn_date);
        btnTime = (Button) findViewById(R.id.btn_time);


        CreateEventClick();

        DateClick();

        TimeClick();

        ImageClick();


    }

    private void ImageClick() {
        btnLoadImage.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 0);
            }
        });
    }

    private void TimeClick() {
        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog(v);
            }
        });
    }

    private void DateClick() {
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        });
    }

    private void CreateEventClick() {
        btnCreateEvent.setOnClickListener(new View.OnClickListener() {

                                              @Override
                                              public void onClick(View v) {
                                                  // TODO Auto-generated method stub

                                                  String headline, description, time, imageUrl;

                                                  headline = etHeadline.getText().toString();
                                                  description = etDescription.getText().toString();
                                                  time = DatePickerFragment.date + " " + TimePickerFragment.time;

                                                  if (!headline.isEmpty() && !description.isEmpty() && !time.isEmpty() && targetUri != null) {
                                                      imageUrl = targetUri.toString();

                                                      Event event = new Event(headline,
                                                              description, time, imageUrl);
                                                      new AsyncCreateEvent(ctx, new FetchCreateEventTaskCompleteListener()).execute(event);

                                                  } else {
                                                      Toast.makeText(getApplicationContext(), "Please input all data to create an Event!", Toast.LENGTH_SHORT).show();
                                                  }


                                              }
                                          }

        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            targetUri = data.getData();

            Bitmap bitmap;
            try {
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                targetImage.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public class FetchCreateEventTaskCompleteListener implements iAsyncTaskCompleteListener<Void> {

        @Override
        public void onTaskComplete(Void result) {
            Intent intent = new Intent(ctx, FeedActivity.class);
            startActivity(intent);
            finish();
        }

    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
}

