package com.example.myschedule.Models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity

public class TaskModel {
    @PrimaryKey
    @NonNull

    String name;
    String data;
    String time;
    String description;
    int colorpriority;



    @Ignore
    public TaskModel(String name, String description, int colorpriority, String data, String time) {

        this.name = name;
        this.data = data;
        this.time = time;
        this.description = description;
        this.colorpriority = colorpriority;
    }

    public TaskModel() {
    }
    @NonNull

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public int getColorpriority() {
        return colorpriority;
    }

    public void setColorpriority(int colorpriority) {
        this.colorpriority = colorpriority;
    }

}
