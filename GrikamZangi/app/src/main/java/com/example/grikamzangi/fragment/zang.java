package com.example.grikamzangi.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.grikamzangi.R;
import com.example.grikamzangi.User.User;
import com.example.grikamzangi.adaptr.RycecAdapter;

import java.util.List;


public class zang extends Fragment {
    static  final  String Key="key";



    public zang() {
        // Required empty public constructor
    }
    public static zang newInstance(int page) {
        zang pageFragment = new zang();
        Bundle arguments = new Bundle();
        arguments.putInt(Key, page);
        pageFragment.setArguments(arguments);
        return pageFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view =inflater.inflate(R.layout.fragment_zang, container, false);

        return view;
    }


}
