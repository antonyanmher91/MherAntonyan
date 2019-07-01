package com.example.myschedule.Adapter;


import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myschedule.Constanti;
import com.example.myschedule.Fragment.Tasks_fr;
import com.example.myschedule.Models.CategoryModel;
import com.example.myschedule.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    List<CategoryModel> list;

    public CategoryAdapter(List<CategoryModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.res_view_category, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.categoryName.setText(list.get(i).getName());
        viewHolder.categoryImg.setImageResource(list.get(i).getImg());
        viewHolder.categoryName.setTextColor(list.get(i).getColor());
        viewHolder.categoryImg.setColorFilter(list.get(i).getColor());
        int color = list.get(i).getColor();
        int alpha = Math.round(Color.alpha(color) * 0.1f);
        int r = Color.red(color);
        int g = Color.green(color);
        int b = Color.blue(color);
        int newColor = Color.argb(alpha, r, g, b);

        Drawable mDrawable = ContextCompat.getDrawable(viewHolder.itemView.getContext(), R.drawable.circle_imageview);
        mDrawable.setColorFilter(new PorterDuffColorFilter(newColor, PorterDuff.Mode.MULTIPLY));
        viewHolder.categoryImg.setBackground(mDrawable);




    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.res_view_category_txt)

        TextView categoryName;
        @BindView(R.id.res_view_category_img)

        ImageView categoryImg;


        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    Tasks_fr tasks_fr = new Tasks_fr();
                    Bundle bundle = new Bundle();
                    bundle.putString(Constanti.CategoryName,list.get(getAdapterPosition()).getName());
                    tasks_fr.setArguments(bundle);
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.activityCategory, tasks_fr).addToBackStack(null).commit();
                }
            });

        }
    }
}
