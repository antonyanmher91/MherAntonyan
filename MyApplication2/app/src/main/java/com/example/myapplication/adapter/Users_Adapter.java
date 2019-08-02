package com.example.myapplication.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.activity.UserPageActivity;
import com.example.myapplication.model.Pictures;
import com.example.myapplication.model.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Users_Adapter extends RecyclerView.Adapter<Users_Adapter.ViewHolder> implements Filterable {
   private List<User> list;
   private List<User> listfiltr;

    public Users_Adapter(List<User> list) {
        this.list = list;
        this.listfiltr = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.users_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(listfiltr.get(position).getName());
        Pictures pictures = new Pictures();
        Picasso.get().load(pictures.getUrl(listfiltr.get(position).getId()-1)).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return listfiltr.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    listfiltr = list;
                }else {
                    List<User> filtredList= new ArrayList<>();
                    for (User s:list) {
                        if (s.getName().toLowerCase().contains(charString.toLowerCase())){
                            filtredList.add(s);
                        }

                    }
                    listfiltr =filtredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = listfiltr;
                filterResults.count=listfiltr.size();
                return  filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults results) {
                listfiltr = (ArrayList<User>) results.values;
                notifyDataSetChanged();
            }
        };
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView img;
        TextView name;

        ViewHolder(@NonNull final View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_user);
            name = itemView.findViewById(R.id.name_user);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent= new Intent(itemView.getContext(), UserPageActivity.class);
                    intent.putExtra("KEY", list.get(getAdapterPosition()).getName());
                    intent.putExtra("ID",list.get(getAdapterPosition()).getId());
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}
