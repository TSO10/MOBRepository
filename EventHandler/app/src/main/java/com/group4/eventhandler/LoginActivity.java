package com.group4.eventhandler;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;


public class LoginActivity extends Activity {

    EditText etUserName, etPassword;
    Button btnLogin;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context=this;
        etUserName = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);
        btnLogin = (Button) findViewById(R.id.btn_Login);

        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                String username = etUserName.getText().toString();
                String password = etPassword.getText().toString();

                // Execute the AsyncLogin class
                new AsyncLogin().execute(username, password);

            }
        });
    }

    protected class AsyncLogin extends AsyncTask<String, JSONObject, Boolean> {

        String userName = null;

        @Override
        protected Boolean doInBackground(String... params) {

            RestAPI api = new RestAPI();
            boolean userAuth = false;
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
                Intent i = new Intent(LoginActivity.this,
                        FeedActivity.class);
                i.putExtra("username", userName);
                startActivity(i);
            } else {
                Toast.makeText(context, "Not valid username/password ", Toast.LENGTH_SHORT).show();
            }

        }
    }

    }

