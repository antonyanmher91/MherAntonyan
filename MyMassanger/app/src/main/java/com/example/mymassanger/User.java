package com.example.mymassanger;

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

    public String getName() {
        return name;
    }

    public String getSurName() {
        return surName;
    }

}
