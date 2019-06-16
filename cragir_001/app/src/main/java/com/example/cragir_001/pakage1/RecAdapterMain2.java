package com.example.cragir_001.pakage1;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cragir_001.R;

import java.util.List;

public class RecAdapterMain2 extends RecyclerView.Adapter<RecAdapterMain2.ViewHolder> {
    List<User> userList;

    public RecAdapterMain2(List<User> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rec_main2, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final User user = userList.get(i);
        viewHolder.img.setImageResource(user.getImg());
        viewHolder.name.setText(user.getName());
        viewHolder.surName.setText(user.getSurName());

    }

    @Override
    public int getItemCount() {
        return userList==null?0:userList.size() ;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name;
        TextView surName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.imgmain2);
            name=itemView.findViewById(R.id.Tv1_main2);
            surName=itemView.findViewById(R.id.Tv2_main2);
        }
    }
}
