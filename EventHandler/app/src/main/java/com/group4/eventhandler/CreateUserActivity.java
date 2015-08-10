package com.group4.eventhandler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class CreateUserActivity extends Activity {

    EditText etfirstName, etLastName, etUsername, etPassword;
    Button btnCreateUser;
    Context context;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        context = this;
        etfirstName = (EditText) findViewById(R.id.et_fisrtname);
        etLastName = (EditText) findViewById(R.id.et_lastname);
        etUsername = (EditText) findViewById(R.id.et_cu_username);
        etPassword = (EditText) findViewById(R.id.et_cu_password);
        btnCreateUser = (Button) findViewById(R.id.createSignUpBtn);

        intent = new Intent(context, LoginActivity.class);

        SignUpClick();
    }

    private void SignUpClick() {
        btnCreateUser.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                String firstname, lastname, username, password;

                firstname = etfirstName.getText().toString();
                lastname = etLastName.getText().toString();
                username = etUsername.getText().toString();
                password = etPassword.getText().toString();

                if (!firstname.isEmpty() && !lastname.isEmpty() && !username.isEmpty() && !password.isEmpty()) {
                    User userDetail = new User(firstname,
                            lastname, username, password);

                    new AsyncSignUp(context, new FetchSignUpTaskCompleteListener()).execute(userDetail);
                } else if (firstname.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "First Name is required!",
                            Toast.LENGTH_SHORT).show();
                } else if (lastname.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Last Name is required!",
                            Toast.LENGTH_SHORT).show();
                } else if (username.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Username is required!",
                            Toast.LENGTH_SHORT).show();
                } else if (password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Password is required",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public class FetchSignUpTaskCompleteListener implements iAsyncTaskCompleteListener<Void> {

        @Override
        public void onTaskComplete(Void result) {
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(intent);
        finish();

    }
}



