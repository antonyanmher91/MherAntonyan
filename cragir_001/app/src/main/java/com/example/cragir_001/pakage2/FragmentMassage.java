package com.example.cragir_001.pakage2;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cragir_001.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMassage extends Fragment {
    View v;
    private RecyclerView myrecyclerView;
    private List<Massange> listMassange;


    public FragmentMassage() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.main3_fragment_masagge, container, false);
        myrecyclerView =v.findViewById(R.id.recycMassange);
        RecycViewAdapterMassange recyclerViewAdapter = new RecycViewAdapterMassange(getContext(),listMassange);
        myrecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerView.setAdapter(recyclerViewAdapter);
        return  v;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listMassange = new ArrayList<>();
       listMassange.add(new Massange("Mher","inch dsfsdf chka",R.drawable.bmw,"095352505"));
       listMassange.add(new Massange("BBBBB","inch kfsdfsda chka",R.drawable.bugati,"564564"));
       listMassange.add(new Massange("AAAAAA","inch fsdfsd chka",R.drawable.ferari,"5896325"));
       listMassange.add(new Massange("CCCCCC","inch ka chka",R.drawable.ic_message,"4897"));
       listMassange.add(new Massange("DDDDDD","inch ka chka",R.drawable.ic_message,"4897"));
       listMassange.add(new Massange("EEEEEE","inch ka chka",R.drawable.ic_message,"4897"));
       listMassange.add(new Massange("CCCCCC","inch ka chka",R.drawable.ic_message,"4897"));
       listMassange.add(new Massange("CCCCCC","inch ka chka",R.drawable.ic_message,"4897"));
       listMassange.add(new Massange("CCCCCC","inch ka chka",R.drawable.ic_message,"4897"));
       listMassange.add(new Massange("CCCCCC","inch ka chka",R.drawable.ic_message,"4897"));
       listMassange.add(new Massange("CCCCCC","inch ka chka",R.drawable.ic_message,"4897"));
       listMassange.add(new Massange("CCCCCC","inch ka chka",R.drawable.ic_message,"4897"));
       listMassange.add(new Massange("CCCCCC","inch ka chka",R.drawable.ic_message,"4897"));
       listMassange.add(new Massange("CCCCCC","inch ka chka",R.drawable.ic_message,"4897"));
       listMassange.add(new Massange("CCCCCC","inch ka chka",R.drawable.ic_message,"4897"));
       listMassange.add(new Massange("CCCCCC","inch ka chka",R.drawable.ic_message,"4897"));
       listMassange.add(new Massange("CCCCCC","inch ka chka",R.drawable.ic_message,"4897"));
       listMassange.add(new Massange("CCCCCC","inch ka chka",R.drawable.ic_message,"4897"));
       listMassange.add(new Massange("CCCCCC","inch ka chka",R.drawable.ic_message,"4897"));
       listMassange.add(new Massange("CCCCCC","inch ka chka",R.drawable.ic_message,"4897"));
       listMassange.add(new Massange("FFFFF","inch ka chka",R.drawable.ic_message,"4897"));
       listMassange.add(new Massange("CCCCCC","inch ka chka",R.drawable.ic_message,"4897"));
       listMassange.add(new Massange("CCCCCC","inch ka chka",R.drawable.ic_message,"4897"));
       listMassange.add(new Massange("CCCCCC","inch ka chka",R.drawable.ic_message,"4897"));
       listMassange.add(new Massange("CCCCCC","inch ka chka",R.drawable.ic_message,"4897"));
       listMassange.add(new Massange("CCCCCC","inch ka chka",R.drawable.ic_message,"4897"));
       listMassange.add(new Massange("CCCCCC","inch ka chka",R.drawable.ic_message,"4897"));
       listMassange.add(new Massange("CCCCCC","inch ka chka",R.drawable.ic_message,"4897"));
       listMassange.add(new Massange("CCCCCC","inch ka chka",R.drawable.ic_message,"4897"));
       listMassange.add(new Massange("CCCCCC","inch ka chka",R.drawable.ic_message,"4897"));
       listMassange.add(new Massange("CCCCCC","inch ka chka",R.drawable.ic_message,"4897"));
       listMassange.add(new Massange("GGGGGG","inch ka chka",R.drawable.ic_message,"4897"));
       listMassange.add(new Massange("CCCCCC","inch ka chka",R.drawable.ic_message,"4897"));
       listMassange.add(new Massange("CCCCCC","inch ka chka",R.drawable.ic_message,"4897"));
       listMassange.add(new Massange("CCCCCC","inch ka chka",R.drawable.ic_message,"4897"));
       listMassange.add(new Massange("CCCCCC","inch ka chka",R.drawable.ic_message,"4897"));
       listMassange.add(new Massange("CCCCCC","inch ka chka",R.drawable.ic_message,"4897"));
       listMassange.add(new Massange("TTTTTT","inch ka chka",R.drawable.ic_message,"4897"));



    }


}
