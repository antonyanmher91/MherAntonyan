package com.example.cragir_001.pakage2;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.cragir_001.R;

public class Main3Activity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        tabLayout = findViewById(R.id.tablayut);
        viewPager = findViewById(R.id.vp1);
        adapter= new ViewPagerAdapter(getSupportFragmentManager());



        adapter.AppFragment(new FragmentMassage(),"Massage");
        adapter.AppFragment(new FragmentContact(),"Contats");
        adapter.AppFragment(new FragmentFav(),"Fav");


        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_message);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_group);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_star);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setElevation(0);

    }
}