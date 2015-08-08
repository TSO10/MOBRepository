package com.group4.eventhandler;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class CreateUserActivity extends Activity {

    EditText etfirstName, etLastName, etUsername, etPassword;
    Button btnCreateUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);


        etfirstName = (EditText) findViewById(R.id.et_fisrtname);
        etLastName = (EditText) findViewById(R.id.et_lastname);
        etUsername = (EditText) findViewById(R.id.et_cu_username);
        etPassword = (EditText) findViewById(R.id.et_cu_password);
        btnCreateUser = (Button) findViewById(R.id.btn_createuser);

        btnCreateUser.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                String firstname, lastname, username, password;

                firstname = etfirstName.getText().toString();
                lastname = etLastName.getText().toString();
                username = etUsername.getText().toString();
                password = etPassword.getText().toString();

                User userDetail = new User(firstname,
                        lastname, username, password);

                new AsyncCreateUser().execute(userDetail);

            }
        });
    }
      protected class AsyncCreateUser extends
                AsyncTask<User, Void, Void> {

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
          protected void onPostExecute(Void result) {

              Intent i = new Intent(CreateUserActivity.this, LoginActivity.class);
              startActivity(i);
          }
      }

    }



