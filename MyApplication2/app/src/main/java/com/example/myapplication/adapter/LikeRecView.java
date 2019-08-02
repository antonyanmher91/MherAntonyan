package com.example.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import com.example.myapplication.R;
import com.example.myapplication.activity.MainActivity;
import com.example.myapplication.activity.UserPageActivity;
import com.example.myapplication.database.DataBaseHelper;
import com.example.myapplication.fragment.Comments_Fragment;
import com.example.myapplication.model.Pictures;
import com.example.myapplication.model.Post;
import com.example.myapplication.model.User;
import com.squareup.picasso.Picasso;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Context.MODE_PRIVATE;

public class LikeRecView extends RecyclerView.Adapter<LikeRecView.ViewHolder> {
    private List<Post> modelArrayList;
    private List<User> userlist;
    private DataBaseHelper db;
    private Context context;

    public LikeRecView(List<Post> modelArrayList, List<User> userlist, Context context) {
        this.modelArrayList = modelArrayList;
        this.userlist = userlist;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_item, parent, false);
        db = Room.databaseBuilder(parent.getContext(), DataBaseHelper.class, "data-database")
                .allowMainThreadQueries()
                .build();
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(modelArrayList.get(position).getTitle());
        holder.body.setText(modelArrayList.get(position).getBody());
        int id = modelArrayList.get(position).getUserID();
        String url;
        for (int i = 0; i < userlist.size(); i++) {
            if (id == userlist.get(i).getId()) {
                holder.name.setText(userlist.get(i).getName());
                Pictures pictures = new Pictures();
                url = pictures.getUrl(userlist.get(i).getId()-1);
                saveBool(String.valueOf(userlist.get(userlist.get(i).getId()-1).getName()),false);
                Picasso.get().load(url).into(holder.imageView);
                break;
            }
        }
        holder.like.setImageResource(R.drawable.ic_favorite_black_24dp);
    }
    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title, body, name;
        CircleImageView imageView;
        ImageView comment, like;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            like = itemView.findViewById(R.id.like_post);
            title = itemView.findViewById(R.id.title_post);
            body = itemView.findViewById(R.id.post_post);
            name = itemView.findViewById(R.id.name_post);
            imageView = itemView.findViewById(R.id.img_post);
            comment = itemView.findViewById(R.id.post_coment);
            comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    goToComentsFragment(view);
                }
            });
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    goToUserPageActivity();
                }
            });
            like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Post post =  modelArrayList.get(getAdapterPosition());

                        like.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                        db.getDataDao().delete(modelArrayList.get(getAdapterPosition()));
                          int id = modelArrayList.get(getAdapterPosition()).getUserID();
                           for (int i = 0; i < userlist.size(); i++) {
                            if (id == userlist.get(i).getId()) {
                                saveBool(String.valueOf(modelArrayList.get(getAdapterPosition()).getId()), false);
                                db.getDataDao().delete(userlist.get(i));
                                modelArrayList.remove(post);
                                notifyDataSetChanged();
                                break;
                            }
                        }

                    }
            });





        }
        private void goToUserPageActivity() {
            Intent intent = new Intent(itemView.getContext(), UserPageActivity.class);
            intent.putExtra("ID", modelArrayList.get(getAdapterPosition()).getUserID());
            intent.putExtra("KEY", name.getText().toString());
            itemView.getContext().startActivity(intent);
        }
        private void goToComentsFragment(View view) {
            Comments_Fragment cf = new Comments_Fragment();
            Bundle bundle = new Bundle();
            bundle.putInt("ID", getAdapterPosition() + 1);
            cf.setArguments(bundle);
            MainActivity activity = (MainActivity) view.getContext();
            FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager()
                    .beginTransaction().replace(R.id.container, cf).addToBackStack(null);
            fragmentTransaction.commit();
        }
    }
    private void saveBool(String str, Boolean bool) {
        SharedPreferences.Editor editor = context.getSharedPreferences(str, MODE_PRIVATE).edit();
        editor.putBoolean(str, bool);
        editor.apply();
    }
}
