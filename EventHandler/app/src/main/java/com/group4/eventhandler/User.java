package com.group4.eventhandler;

/**
 * Created by Parsa on 28/07/2015.
 */
public class User {

    String firstName, lastName, userName, password;

    public User(String firstName, String lastName, String userName,
                String password) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
    }

    public User() {
        super();
        this.firstName = null;
        this.lastName = null;
        this.userName = null;
        this.password = null;

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}