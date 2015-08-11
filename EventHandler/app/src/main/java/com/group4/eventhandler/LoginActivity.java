package com.group4.eventhandler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;


public class LoginActivity extends Activity {

    EditText etUserName, etPassword;
    Button btnLogin;
    Button btnSign;
    Context context;
    SharedPreferences.Editor editor;
    boolean loginStatus;
    Intent intent;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getActionBar().hide();
        setContentView(R.layout.activity_login);



        // to save loginStatus
        editor = getSharedPreferences("loginStatus", MODE_PRIVATE).edit();

        //to load loginStatus
        prefs = getSharedPreferences("loginStatus", MODE_PRIVATE);

        context = this;
        etUserName = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnSign = (Button) findViewById(R.id.btn_sign_up);
        intent = new Intent(context, FeedActivity.class);

        loginStatus = prefs.getBoolean("login", false);

        if (loginStatus) {
            startActivity(intent);
            finish();
        }
        LoginClick();
        SignUpClick();
    }

    private void SignUpClick() {
        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, CreateUserActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    private void LoginClick() {
        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String username = etUserName.getText().toString();
                String password = etPassword.getText().toString();
                // Execute the login task
                new AsyncLogin(getApplicationContext(), new FetchLoginTaskCompleteListener()).execute(username, password);
            }
        });
    }

    public class FetchLoginTaskCompleteListener implements iAsyncTaskCompleteListener<Boolean> {

        @Override
        public void onTaskComplete(Boolean result) {


            editor.putBoolean("login", true);
            editor.commit();
            startActivity(intent);
            finish();
        }
    }

}


