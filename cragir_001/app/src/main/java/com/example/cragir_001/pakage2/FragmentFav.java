package com.example.cragir_001.pakage2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cragir_001.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentFav extends Fragment {
  View v;

    public FragmentFav() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       v=inflater.inflate(R.layout.main3_fragment_fav, container, false);
       return v;
    }

}
