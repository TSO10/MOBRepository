package com.group4.eventhandler;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.group4.eventhandler.iAsyncTaskCompleteListener;

import org.json.JSONObject;

import webservice.JSONParser;
import webservice.RestAPI;

/**
 * Created by Parsa on 08/08/2015.
 */
public class AsyncLogin extends AsyncTask<String, JSONObject, Boolean> {


    String userName = null;
    private Context context;
    private iAsyncTaskCompleteListener<Boolean> listener;
    boolean userAuth;

    public AsyncLogin(Context ctx, iAsyncTaskCompleteListener<Boolean> listener) {
        this.context = ctx;
        this.listener = listener;
    }

    @Override
    protected Boolean doInBackground(String... params) {

        RestAPI api = new RestAPI();
        userAuth = false;
        try {

            // Call the User Authentication Method in API
            JSONObject jsonObj = api.UserAuthentication(params[0],
                    params[1]);

            //Parse the JSON Object to boolean
            JSONParser parser = new JSONParser();
            userAuth = parser.parseUserAuth(jsonObj);
            userName = params[0];
        } catch (Exception e) {
            // TODO Auto-generated catch block
            Log.d("AsyncLogin", e.getMessage());

        }
        return userAuth;
    }

    @Override
    protected void onPreExecute() {

        super.onPreExecute();

        Toast.makeText(context, "Please Wait...", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPostExecute(Boolean result) {
        // TODO Auto-generated method stub

        //Check user validity
        if (result) {
            listener.onTaskComplete(userAuth);

        } else {
            Toast.makeText(context,"Wrong username or Password!",Toast.LENGTH_SHORT).show();
        }

    }



}
