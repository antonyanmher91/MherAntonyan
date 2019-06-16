package com.example.grikamzangi.User;

public class User {
    private String name;
    private String surName;
    private String number;


    public User(String name, String surName, String number) {
        this.name = name;
        this.surName = surName;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public String getSurName() {
        return surName;
    }

    public String getNumber() {
        return number;
    }

}
