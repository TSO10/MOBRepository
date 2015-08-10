package com.group4.eventhandler;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import webservice.JSONParser;
import webservice.RestAPI;


public class EventDetailsActivity extends Activity {

    TextView tvheadline, tvdescription, tvtime;
    ImageView imageView;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        tvheadline = (TextView) findViewById(R.id.tv_headline_detail);
        tvdescription = (TextView) findViewById(R.id.tv_desc_detail);
        tvtime = (TextView) findViewById(R.id.tv_time_detail);
        imageView = (ImageView) findViewById(R.id.iv_photo_detail);


        new AsyncEventDetails(getApplicationContext(), new FetchEventTaskCompleteListener()).execute();

    }

    public class FetchEventTaskCompleteListener implements iAsyncTaskCompleteListener<Event> {
        @Override
        public void onTaskComplete(Event result) {

            tvheadline.setText(result.getHeadline());
            tvdescription.setText(result.getDescription());
            tvtime.setText(result.getTime());

            imageUri = Uri.parse(result.getImageUrl());

            Bitmap bitmap;
            try {
                bitmap = BitmapFactory.decodeStream(getApplicationContext().getContentResolver().openInputStream(imageUri));
                imageView.setImageBitmap(bitmap);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }
}
