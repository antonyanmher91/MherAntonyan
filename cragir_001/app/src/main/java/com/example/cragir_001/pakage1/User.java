package com.example.cragir_001.pakage1;

public class User {
    private String name;
    private String surName;
    private int img;

    public User(String name, String surName, int img) {
        this.name = name;
        this.surName = surName;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public String getSurName() {
        return surName;
    }

    public int getImg() {
        return img;
    }
}
