package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.activity.UserPageActivity;
import com.example.myapplication.fragment.fragmentpageuser.ViewPagerDialogFragment;
import com.squareup.picasso.Picasso;

public class UserImagesAdapter extends RecyclerView.Adapter<UserImagesAdapter.ViewHolder> {
  private String[] list;

    public UserImagesAdapter(String[] list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recview_img_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.get().load(list[position]).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        ViewHolder(@NonNull final View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_res_view);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DialogFragment dialogFragment = new ViewPagerDialogFragment(getAdapterPosition(),list);
                    UserPageActivity activity = (UserPageActivity)itemView.getContext();
                    dialogFragment.show(activity.getSupportFragmentManager(),"fragmentManager");
                }
            });
        }
    }
}
