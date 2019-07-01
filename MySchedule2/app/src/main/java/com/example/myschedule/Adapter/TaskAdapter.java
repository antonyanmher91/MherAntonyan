package com.example.myschedule.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myschedule.Models.TaskModel;
import com.example.myschedule.R;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
 List<TaskModel> list;

    public TaskAdapter(List<TaskModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.task_item,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
      TaskModel taskModel = list.get(i);
      viewHolder.name.setText(taskModel.getName());
      viewHolder.desc.setText(taskModel.getDescription());
      viewHolder.data.setText(taskModel.getData());
      viewHolder.time.setText(taskModel.getTime());
      viewHolder.itemView.setBackgroundColor(taskModel.getColorpriority());

    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name,desc,data,time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.task_img);
            name=itemView.findViewById(R.id.task_name);
            desc=itemView.findViewById(R.id.task_desc);
            data=itemView.findViewById(R.id.task_data);
            time=itemView.findViewById(R.id.task_time);

        }
    }
}
