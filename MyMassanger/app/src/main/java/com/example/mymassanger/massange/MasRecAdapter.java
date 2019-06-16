package com.example.mymassanger.massange;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mymassanger.R;

import java.util.ArrayList;
import java.util.List;

public class MasRecAdapter extends RecyclerView.Adapter<MasRecAdapter.MyViewHolder> {
    ArrayList<MassangerItem> list;

    public MasRecAdapter( ArrayList<MassangerItem> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.res_item_mass, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(v);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.name.setText(list.get(i).getName());
        myViewHolder.massange.setText(list.get(i).getMassange());
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView massange;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_mass);
            massange = itemView.findViewById(R.id.mass_txt);
        }
    }
}
