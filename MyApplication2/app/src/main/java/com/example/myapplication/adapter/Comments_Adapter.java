package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.R;
import com.example.myapplication.model.Comments;
import java.util.List;

public class Comments_Adapter  extends RecyclerView.Adapter<Comments_Adapter.ViewHolder> {
   private List<Comments> list;

    public Comments_Adapter(List<Comments> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comment_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       holder.txt.setText(list.get(position).getName());
       holder.body.setText(list.get(position).getBody());
       holder.email.setText(list.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends  RecyclerView.ViewHolder{
        TextView txt,body,email;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            email= itemView.findViewById(R.id.email_id);
            txt= itemView.findViewById(R.id.name_comment);
            body = itemView.findViewById(R.id.txt_comment);
        }
    }
}
