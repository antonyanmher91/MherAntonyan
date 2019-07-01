package com.example.myschedule.Models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class CategoryModel {


    @PrimaryKey
    @NonNull
    String name;
    int img;
    int color;

    @Ignore
    public CategoryModel(@NonNull String name, int img, int color) {
        this.name = name;
        this.img = img;
        this.color = color;

    }

    public CategoryModel() {
    }

    @NonNull
    public String getName() {
        return name;
    }

    public int getImg() {
        return img;
    }

    public int getColor() {
        return color;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public void setColor(int color) {
        this.color = color;
    }

}
