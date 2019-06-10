package com.example.mymassanger;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ResViewUserAdapter extends RecyclerView.Adapter<ResViewUserAdapter.ViewHolder> {
//   HashMap<String,String> list;
ArrayList<User>list;

//    public ResViewUserAdapter(HashMap<String,String> list) {
//        this.list = list;
//    }
    public ResViewUserAdapter(ArrayList<User> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rec_vew_user,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
//     viewHolder.img.setImageResource(list.get(i).img);
     viewHolder.name.setText(list.get(i).name);

    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView name;
        TextView surNAme;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img_user);
            name=itemView.findViewById(R.id.name_user);
            surNAme=itemView.findViewById(R.id.surname_user);
        }
    }
}
