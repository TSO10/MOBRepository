package com.group4.eventhandler;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.util.ArrayList;

import webservice.JSONParser;
import webservice.RestAPI;

/**
 * Created by Parsa on 09/08/2015.
 */
public class AsyncLoadAllEvents extends AsyncTask<Void, JSONObject, ArrayList<Event>> {
    ArrayList<Event> eventTable = null;
    private Context context;
    private iAsyncTaskCompleteListener<ArrayList<Event>> listener;

    public AsyncLoadAllEvents(Context ctx, iAsyncTaskCompleteListener<ArrayList<Event>> listener) {
        this.context = ctx;
        this.listener = listener;
    }

    @Override
    protected ArrayList<Event> doInBackground(Void... params) {
        // TODO Auto-generated method stub


        RestAPI api = new RestAPI();
        try {

            JSONObject jsonObj = api.GetAllEvents();

            JSONParser parser = new JSONParser();

            eventTable = parser.parseEvent(jsonObj);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            Log.d("AsyncLoadDeptDetails", e.getMessage());

        }

        return eventTable;
    }

    @Override
    protected void onPostExecute(ArrayList<Event> result) {
        // TODO Auto-generated method stub
        listener.onTaskComplete(result);

    }
}