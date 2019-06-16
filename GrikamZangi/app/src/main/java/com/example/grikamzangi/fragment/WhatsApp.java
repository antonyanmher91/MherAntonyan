package com.example.grikamzangi.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.grikamzangi.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class WhatsApp extends Fragment {


    public WhatsApp() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_whats_app, container, false);
    }

}
