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
public class FragmentContact extends Fragment {

  View v;


    private RecyclerView myrecyclerView;
  private List<Contact> listContact;


    public FragmentContact() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.main3_fragment_contact, container, false);
        myrecyclerView =v.findViewById(R.id.contactrecyc);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(),listContact);
        myrecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerView.setAdapter(recyclerViewAdapter);
        return  v;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listContact = new ArrayList<>();
        listContact.add(new Contact("Mher","095352505",R.drawable.ic_contacts));
        listContact.add(new Contact("Mher","093456535",R.drawable.ic_contacts));
        listContact.add(new Contact("Neso","077517001",R.drawable.ic_contacts));
        listContact.add(new Contact("Gevor","094517397",R.drawable.ic_contacts));
        listContact.add(new Contact("Tigo","077493434",R.drawable.ic_contacts));
        listContact.add(new Contact("Qont","077015484",R.drawable.ic_contacts));
        listContact.add(new Contact("Mher","095352505",R.drawable.ic_contacts));
        listContact.add(new Contact("Mher","123456987",R.drawable.ic_contacts));
        listContact.add(new Contact("Mher","095352505",R.drawable.ic_contacts));
        listContact.add(new Contact("Mher","095352505",R.drawable.ic_contacts));
        listContact.add(new Contact("Mher","095352505",R.drawable.ic_contacts));
        listContact.add(new Contact("Mher","095352505",R.drawable.ic_contacts));
        listContact.add(new Contact("Mher","095352505",R.drawable.ic_contacts));
        listContact.add(new Contact("Mher","095352505",R.drawable.ic_contacts));
        listContact.add(new Contact("Mher","095352505",R.drawable.ic_contacts));
        listContact.add(new Contact("Mher","05646123",R.drawable.ic_contacts));
        listContact.add(new Contact("Mher","06844564",R.drawable.ic_contacts));
        listContact.add(new Contact("Mher","08970324",R.drawable.ic_contacts));
    }


}
