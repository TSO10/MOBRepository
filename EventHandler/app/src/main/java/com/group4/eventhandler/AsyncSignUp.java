package com.group4.eventhandler;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import webservice.RestAPI;

/**
 * Created by Parsa on 09/08/2015.
 */
public class AsyncSignUp extends AsyncTask<User, Void, Void> {
    private Context context;
    private iAsyncTaskCompleteListener<Void> listener;

    public AsyncSignUp(Context ctx, iAsyncTaskCompleteListener<Void> listener) {
        this.context = ctx;
        this.listener = listener;
    }

    @Override
    protected Void doInBackground(User... params) {

        RestAPI api = new RestAPI();
        try {

            api.CreateNewAccount(params[0].getFirstName(),
                    params[0].getLastName(), params[0].getUserName(),
                    params[0].getPassword());

        } catch (Exception e) {
            // TODO Auto-generated catch block
            Log.d("AsyncCreateUser", e.getMessage());

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

        Toast.makeText(context, "Welcome to Event Handler", Toast.LENGTH_SHORT).show();
    }
}

