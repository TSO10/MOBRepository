package theme.mulitipleactivities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {

    EditText firstname;
    EditText lastname;
    Button pressBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstname = (EditText) findViewById(R.id.firstnameInput);
        lastname = (EditText) findViewById(R.id.lastnameInput);
        pressBtn = (Button) findViewById(R.id.pressBtn);



        pressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String firstnameString = firstname.getText().toString();
                final String lastnameString = lastname.getText().toString();

                Intent intent = new Intent(getApplicationContext(),InfoActivity.class);
                intent.putExtra("firstnameLabel", firstnameString);


                intent.putExtra("lastnameLabel",lastnameString);
               startActivity(intent);
            }
        });

    }

}
