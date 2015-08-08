package com.group4.eventhandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Parsa on 28/07/2015.
 */
public class JSONParser {

    public JSONParser() {
        super();
    }

    public ArrayList<Event> parseEvent(JSONObject object) {
        ArrayList<Event> arrayList = new ArrayList<Event>();
        try {
            JSONArray jsonArray = object.getJSONArray("Value");
            JSONObject jsonObj = null;
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObj = jsonArray.getJSONObject(i);
                arrayList.add(new Event(jsonObj.getInt("id"),jsonObj.getString("headline"), jsonObj.getString("description"),jsonObj.getString("time"),jsonObj.getString("imageUrl")));
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
           // Log.d("JSONParser => parseDepartment", e.getMessage());
        }
        return arrayList;
    }

    public boolean parseUserAuth(JSONObject object) {
        boolean userAtuh = false;
        try {
            userAtuh = object.getBoolean("Value");
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            //Log.d("JSONParser => parseUserAuth", e.getMessage());
        }

        return userAtuh;
    }

    public User parseUserDetails(JSONObject object) {
        User userDetail = new User();

        try {
            JSONObject jsonObj = object.getJSONArray("Value").getJSONObject(0);

            userDetail.setFirstName(jsonObj.getString("firstName"));
            userDetail.setLastName(jsonObj.getString("lastName"));

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            //Log.d("JSONParser => parseUserDetails", e.getMessage());
        }

        return userDetail;
    }

    public Event parseEventDetails(JSONObject object) {
        Event eventDetail = new Event();

        try {
            JSONObject jsonObj = object.getJSONArray("Value").getJSONObject(0);

            eventDetail.setId(jsonObj.getInt("id"));
            eventDetail.setHeadline(jsonObj.getString("headline"));
            eventDetail.setDescription(jsonObj.getString("description"));
            eventDetail.setTime(jsonObj.getString("time"));
            eventDetail.setImageUrl(jsonObj.getString("imageUrl"));

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            //Log.d("JSONParser => parseUserDetails", e.getMessage());
        }

        return eventDetail;
    }
}
