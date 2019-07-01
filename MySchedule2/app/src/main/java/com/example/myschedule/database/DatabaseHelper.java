package com.example.myschedule.database;

import android.arch.persistence.db.SupportSQLiteOpenHelper;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomDatabase;


import com.example.myschedule.Models.CategoryModel;
import com.example.myschedule.Models.TaskModel;


@Database(entities = { CategoryModel.class, TaskModel.class}, version = 1, exportSchema = false)

public abstract class DatabaseHelper extends RoomDatabase {

    public abstract DataDao getDataDao();


    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

}

