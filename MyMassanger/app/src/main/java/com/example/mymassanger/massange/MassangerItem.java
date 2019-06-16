package com.example.mymassanger.massange;

public class MassangerItem {
    String name;
    String mass;

    public MassangerItem() {
    }

    public MassangerItem(String name, String massange) {
        this.name = name;
        this.mass = massange;
    }

    public String getName() {
        return name;
    }

    public String getMassange() {
        return mass;
    }
}
