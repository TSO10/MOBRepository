package com.group4.eventhandler;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import webservice.RestAPI;

/**
 * Created by Parsa on 09/08/2015.
 */
public class AsyncCreateEvent extends AsyncTask<Event, Void, Void> {

    Context context;
    iAsyncTaskCompleteListener listener;

    public AsyncCreateEvent(Context ctx, iAsyncTaskCompleteListener<Void> listener) {

        this.context = ctx;
        this.listener = listener;
    }

    @Override
    protected Void doInBackground(Event... params) {

        RestAPI api = new RestAPI();
        try {

            api.CreateEvent(params[0].getHeadline(),
                    params[0].getDescription(), params[0].getTime(),
                    params[0].getImageUrl());

        } catch (Exception e) {
            // TODO Auto-generated catch block
            Log.d("AsyncCreateEvent", e.getMessage());

        }
        return null;
    }

    @Override
    protected void onPreExecute() {

        super.onPreExecute();

        Toast.makeText(context, "Please Wait...", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPostExecute(Void result) {

        listener.onTaskComplete(result);
    }
}