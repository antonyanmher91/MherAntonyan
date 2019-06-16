package com.example.mymassanger.users;

public class User {
    String name;
    String surName;
    String key;


    public User(String name, String surName, String key) {
        this.name = name;
        this.surName = surName;
        this.key=key;
    }

    public User() {
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getSurName() {
        return surName;
    }

}
