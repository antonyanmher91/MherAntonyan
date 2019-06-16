package com.example.mymassanger.users;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mymassanger.R;
import com.example.mymassanger.massange.MassangerActivity;

import java.util.ArrayList;

public class ResViewUserAdapter extends RecyclerView.Adapter<ResViewUserAdapter.ViewHolder> {

    ArrayList<User> list;


    public ResViewUserAdapter(ArrayList<User> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rec_vew_user, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.name.setText(list.get(i).name);
        viewHolder.surNAme.setText(" "+list.get(i).getSurName());

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name;
        TextView surNAme;
        LinearLayout ll;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_user);
            name = itemView.findViewById(R.id.name_user);
            surNAme = itemView.findViewById(R.id.surname_user);
            ll=itemView.findViewById(R.id.llmass);
            ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), MassangerActivity.class);
                    intent.putExtra("key",list.get(getAdapterPosition()).key);
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }

}
