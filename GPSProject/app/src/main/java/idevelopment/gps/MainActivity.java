package idevelopment.gps;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    Button btn ;
    Location lastLoc;
    float dist;
    TextView tv;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.button);
        tv = (TextView) findViewById(R.id.distance);
        final LocationManager lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                if(lastLoc == null)
                {
                    lastLoc = location;
                }
                else {
                    lastLoc = lm.getLastKnownLocation(lm.GPS_PROVIDER);
                }

                dist += lastLoc.distanceTo(location);

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                Toast t = Toast.makeText(getApplicationContext(), Integer.toString(status), Toast.LENGTH_SHORT);
                t.show();
            }

            @Override
            public void onProviderEnabled(String provider) {
                Toast t = Toast.makeText(getApplicationContext(), provider, Toast.LENGTH_SHORT);
                t.show();
            }

            @Override
            public void onProviderDisabled(String provider) {
                Toast t = Toast.makeText(getApplicationContext(), provider, Toast.LENGTH_SHORT);
                t.show();
            }
        };

        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                tv.setText(String.valueOf(dist));
                Toast t = Toast.makeText(getApplicationContext(),String.valueOf(dist),Toast.LENGTH_SHORT);
                t.show();
            }
        });
    }

}
