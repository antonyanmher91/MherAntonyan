package com.example.myapplication.database;


import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.example.myapplication.model.Post;
import com.example.myapplication.model.User;
@Database(entities = {Post.class, User.class}, version = 1, exportSchema = false)
public abstract class DataBaseHelper extends RoomDatabase {
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
