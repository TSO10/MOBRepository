package com.group4.eventhandler;

/**
 * Created by Parsa on 09/08/2015.
 */
public interface iAsyncTaskCompleteListener<T> {

    public void onTaskComplete(T result);
}
