package theme.mulitipleactivities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


public class InfoActivity extends Activity {

    TextView firstnameLabel;
    TextView lastnameLabel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        firstnameLabel = (TextView) findViewById(R.id.firstnameLabel);
        lastnameLabel = (TextView) findViewById(R.id.lastNameLabel);
        Bundle extras = getIntent().getExtras();
        String firstname = extras.getString("firstnameLabel");
        firstnameLabel.setText(firstname);


        String lastname = extras.getString("lastnameLabel");
        lastnameLabel.setText(lastname);

    }


}
