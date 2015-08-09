package com.group4.eventhandler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;

import webservice.JSONParser;
import webservice.RestAPI;


public class FeedActivity extends Activity {

    ArrayAdapter<String> adapter;
    ListView listv;
    Context context;
    ArrayList<Event> data;
    static int itemid;
    ArrayList<String> test;
    Button btnCreateEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        btnCreateEvent = (Button) findViewById(R.id.btn_new_event);
        data = new ArrayList<Event>();
        test = new ArrayList<String>();
        listv = (ListView) findViewById(R.id.lv_event);
        context = this;

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, test);
        listv.setAdapter(adapter);

        Toast.makeText(this, "Loading Please Wait..", Toast.LENGTH_SHORT).show();

        new AsyncLoadAllEvents(getApplicationContext(), new FetchAllEventsTaskCompleteListener()).execute();

        listv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemPosition = position;
                Object itemValue = listv.getItemAtPosition(position);
                String test = itemValue.toString();
                String kir = test.substring(0, 1);
                itemid = Integer.parseInt(kir);
                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
                        .show();

                Intent i = new Intent(getApplicationContext(), EventDetailsActivity.class);
                startActivity(i);

            }
        });

        btnCreateEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CreateEventActivity.class);
                startActivity(i);
            }
        });
    }
    public class FetchAllEventsTaskCompleteListener implements iAsyncTaskCompleteListener<ArrayList<Event>>{
        @Override
        public void onTaskComplete(ArrayList<Event> result) {
            for (Event event : result)
            {
                data.add(event);
                test.add(event.getId() + " "+ event.getHeadline() + " " + event.description);
            }

            adapter.notifyDataSetChanged();
        }
    }
}
