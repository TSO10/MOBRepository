package com.group4.eventhandler;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import webservice.JSONParser;
import webservice.RestAPI;

/**
 * Created by Parsa on 09/08/2015.
 */

public class AsyncEventDetails extends AsyncTask<String, Void, Event> {

    Context context;
    iAsyncTaskCompleteListener listener;

    public AsyncEventDetails(Context ctx, iAsyncTaskCompleteListener<Event> listener) {

        this.context = ctx;
        this.listener = listener;
    }

    @Override
    protected Event doInBackground(String... params) {
        // TODO Auto-generated method stub
        Event eventDetail = null;
        RestAPI api = new RestAPI();
        try {

            JSONObject jsonObj = api.GetEventById(FeedActivity.itemid);
            JSONParser parser = new JSONParser();
            eventDetail = parser.parseEventDetails(jsonObj);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            Log.d("AsyncUserDetails", e.getMessage());

        }

        return eventDetail;
    }

    @Override
    protected void onPreExecute() {

        super.onPreExecute();

        Toast.makeText(context, "Please Wait...", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPostExecute(Event result) {
        // TODO Auto-generated method stub

        listener.onTaskComplete(result);
    }
}
