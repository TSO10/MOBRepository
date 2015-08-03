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
    Location currentLoc;
    float dist;
    TextView tv;
    LocationManager locationmanager;
    List<Location> locationList = new ArrayList<>();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.button);
        tv = (TextView) findViewById(R.id.distance);
        locationmanager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                currentLoc = location;

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
         /*       Toast t = Toast.makeText(getApplicationContext(), Integer.toString(status), Toast.LENGTH_SHORT);
                t.show();*/
            }

            @Override
            public void onProviderEnabled(String provider) {
                Toast t = Toast.makeText(getApplicationContext(), provider + "enabled", Toast.LENGTH_SHORT);
                t.show();
            }

            @Override
            public void onProviderDisabled(String provider) {
                Toast t = Toast.makeText(getApplicationContext(), provider + "disabled", Toast.LENGTH_SHORT);
                t.show();
            }
        };

        locationmanager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, locationListener);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(currentLoc != null){

                    locationList.add(currentLoc);

                    if (locationList.size() > 1)
                    {
                        dist += currentLoc.distanceTo(locationList.get(locationList.size()-2));

                    }

                    String distance = String.valueOf(dist);
                    tv.setText(distance);
                    Toast t = Toast.makeText(getApplicationContext(),distance,Toast.LENGTH_SHORT);
                    t.show();
                }
                else {
                    locationList.clear();
                    Toast t = Toast.makeText(getApplicationContext(),"No gps coordinates",Toast.LENGTH_SHORT);
                    t.show();
                }
            }
        });
    }

}
