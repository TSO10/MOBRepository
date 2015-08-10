package com.group4.eventhandler;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;


public class FeedActivity extends Activity {

    ListView listv;
    Context context;
    ArrayList<Event> eventArrayList;
    static int itemid;
    Intent intent;
    private SwipeRefreshLayout swipeContainer;
    FloatingActionButton fab;
    FeedAdapter adapter;
    public Activity CustomListView = null;
    public ArrayList<Event> CustomListViewValuesArr = new ArrayList<Event>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        CustomListView = this;
        intent = new Intent(this, CreateEventActivity.class);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getActionBar().setCustomView(R.layout.ab_layout);
        fab = (FloatingActionButton) findViewById(R.id.btn_fab);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        swipeContainer.setColorSchemeResources(android.R.color.holo_green_dark,
                android.R.color.holo_blue_light,
                android.R.color.holo_purple);


        eventArrayList = new ArrayList<Event>();
        listv = (ListView) findViewById(R.id.lv_event);
        context = this;

        Resources res = getResources();


        /**************** Create Custom Adapter *********/
        adapter = new FeedAdapter(CustomListView, CustomListViewValuesArr, res);
        listv.setAdapter(adapter);
        itemid = FeedAdapter.itemId;


        Toast.makeText(this, "Loading Please Wait..", Toast.LENGTH_SHORT).show();

        new AsyncLoadAllEvents(getApplicationContext(), new FetchAllEventsTaskCompleteListener()).execute();

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new AsyncLoadAllEvents(getApplicationContext(), new FetchAllEventsTaskCompleteListener()).execute();
            }
        });

    }

    /**
     * **************  This function used by adapter ***************
     */

    public void onItemClick(int mPosition) {
        Event event = (Event) CustomListViewValuesArr.get(mPosition);

        Intent intent = new Intent(getApplicationContext(), EventDetailsActivity.class);
        itemid = event.getId();
        startActivity(intent);

    }


    public class FetchAllEventsTaskCompleteListener implements iAsyncTaskCompleteListener<ArrayList<Event>> {
        @Override
        public void onTaskComplete(ArrayList<Event> result) {

            CustomListViewValuesArr.clear();
            eventArrayList.clear();

            for (Event event : result) {
                eventArrayList.add(event);

                CustomListViewValuesArr.add(event);
            }

            adapter.notifyDataSetChanged();
            swipeContainer.setRefreshing(false);

        }
    }
}
