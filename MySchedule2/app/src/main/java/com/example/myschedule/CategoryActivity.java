package com.example.myschedule;

import android.arch.persistence.room.Room;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.myschedule.Fragment.CategoryFragment;
import com.example.myschedule.database.DatabaseHelper;

public class CategoryActivity extends AppCompatActivity {
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    CategoryFragment categoryFragment;
    private static CategoryActivity instance;
    private DatabaseHelper db;

    public static CategoryActivity getInstance() {
        return instance;
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        instance = this;
        db = Room.databaseBuilder(getApplicationContext(), DatabaseHelper.class, "data-database")
                .allowMainThreadQueries()
                .build();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        categoryFragment = new CategoryFragment();
        fragmentTransaction.replace(R.id.activityCategory, categoryFragment).commit();
    }

    public DatabaseHelper getDatabaseInstance() {

        return db;

    }


}
