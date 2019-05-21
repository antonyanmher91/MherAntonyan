package com.example.cragir_001;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<Auto> getData = dataSource();
        viewPager = findViewById(R.id.viewpager);
        VpAdapter vpadapter = new VpAdapter(this, getData);
        viewPager.setAdapter(vpadapter);
    }

    private List<Auto> dataSource() {
        List<Auto> data = new ArrayList<>();
        data.add(new Auto(R.drawable.ferari, "Ferrari"));
        data.add(new Auto(R.drawable.bmw, "Bmw"));
        data.add(new Auto(R.drawable.bugati, "Bugatti"));
        return data;

    }
}