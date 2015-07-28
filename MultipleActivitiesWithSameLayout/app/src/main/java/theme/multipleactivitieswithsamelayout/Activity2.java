package theme.multipleactivitieswithsamelayout;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class Activity2 extends ActionBarActivity {

    TextView text;
	  Button btn;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            text = (TextView) findViewById(R.id.textView);
			 btn = (Button) findViewById(R.id.button);
            text.setText("Activity2");
			 btn.setText("Back to Main Activity");
			   btn.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                     startActivity(intent);
                 }
             });
        }
    }
