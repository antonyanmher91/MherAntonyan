package com.example.cragir_001.pakage2;

public class Massange {
    private String name;
    private  String massage;
    private  int img;
    private String phonenumber;

    public Massange(String name, String massage, int img, String phonenumber) {
        this.name = name;
        this.massage = massage;
        this.img = img;
        this.phonenumber = phonenumber;
    }

    public Massange() {
    }

    public String getName() {
        return name;
    }

    public String getMassage() {
        return massage;
    }

    public int getImg() {
        return img;
    }

    public String getPhonenumber() {
        return phonenumber;
    }
}
