package com.qa.data;

import com.qa.common.ConfigPropertReader;

public class User {
    public String userName;
    public String password;
    public String firstName;
    public String lastName;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public User(String firstName, String lastName, String bikename) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User() {

        this.userName = "";
        this.password = "";
    }

    public static User getUserData() {
        User user = null;
        if (ConfigPropertReader.getProperty("platform").equalsIgnoreCase("ios")) {
            //user = new User();
            user = new User("cc", "*nmn");
        } else if (ConfigPropertReader.getProperty("platform").equalsIgnoreCase("android")) {
            user = new User("jhjh", "jhhj*");
        }
        return user;
    }





}
