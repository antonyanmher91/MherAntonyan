package com.example.cragir_001.pakage2;

public class Contact {
    private  String name;
    private String phone;
    private  int img;

    public Contact(String name, String phone, int img) {
        this.name = name;
        this.phone = phone;
        this.img = img;
    }

    public Contact() {
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public int getImg() {
        return img;
    }
}
