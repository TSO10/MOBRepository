package com.group4.eventhandler;

/**
 * Created by Parsa on 28/07/2015.
 */
public class Event {

    int id;
    String headline, description, time, imageUrl;

    public Event(String headline, String description, String time,
                 String imageUrl) {
        super();
        this.headline = headline;
        this.description = description;
        this.time = time;
        this.imageUrl = imageUrl;
    }

    public Event(int id, String headline, String description, String time,
                 String imageUrl) {
        super();
        this.id = id;
        this.headline = headline;
        this.description = description;
        this.time = time;
        this.imageUrl = imageUrl;
    }

    public Event() {
        super();
        this.headline = null;
        this.description = null;
        this.time = null;
        this.imageUrl = null;

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}