package com.group4.eventhandler;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import webservice.JSONParser;
import webservice.RestAPI;

public class ServerService extends Service {

    private final IBinder mBinder = new LocalBinder();
    private Thread servicecallthread = null;

    private RestAPI restAPI = new RestAPI();
    private JSONParser jsonParser = new JSONParser();
    String userName = null;
    private boolean userAuth = false;
    public static final String RESULT_RETURNED_FROM_SERVICE = "Result_Returned_From_Service";

    public ServerService() {


    }

    public class LocalBinder extends Binder {

        ServerService getService() {

            return ServerService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
       return mBinder;
    }
    public boolean checkUserValidation ()
    {
        return userAuth;
    }

    //method for validate user on DB
    public void ValidateUser(final String usrname, final String pass) throws Exception {



        servicecallthread = new Thread() {
            RestAPI api = new RestAPI();

            public void run() {

                try {
                    // Call the User Authentication Method in API
                    JSONObject jsonObj = api.UserAuthentication(usrname,
                           pass);

                    //Parse the JSON Object to boolean
                    JSONParser parser = new JSONParser();
                    userAuth = parser.parseUserAuth(jsonObj);
                    userName = usrname;

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    Log.d("AsyncLogin", e.getMessage());

                }
//
                Intent retint = new Intent(RESULT_RETURNED_FROM_SERVICE);
                sendBroadcast(retint);
            }

        };
        this.servicecallthread.start();
    }

}
