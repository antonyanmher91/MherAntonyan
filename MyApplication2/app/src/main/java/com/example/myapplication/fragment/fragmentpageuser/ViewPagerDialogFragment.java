package com.example.myapplication.fragment.fragmentpageuser;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.R;
import com.example.myapplication.adapter.ViewPagerAdapter;

public class ViewPagerDialogFragment extends DialogFragment {
    private int position;
    private String[] list;

    public ViewPagerDialogFragment(int position, String[] list) {
        this.position = position;
        this.list = list;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.viewpager_userimages,container,false);
        ViewPager viewPager =view.findViewById(R.id.viewpagerImages);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(list);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(position);
        return view;
    }
}
