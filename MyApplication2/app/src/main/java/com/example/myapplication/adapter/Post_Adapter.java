package com.example.myapplication.adapter;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.PopupMenu;
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

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Context.MODE_PRIVATE;

public class Post_Adapter extends RecyclerView.Adapter<Post_Adapter.MyViewHolder> implements Filterable {
    private ArrayList<Post> modelArrayList;
    private ArrayList<User> userlist;
    private List<Post> listfiltr;
    private Context context;
    private DataBaseHelper db;

    public Post_Adapter(List<Post> modelArrayList, List<User> userlist, Context context) {
        this.modelArrayList = (ArrayList<Post>) modelArrayList;
        this.userlist = (ArrayList<User>) userlist;
        this.listfiltr = modelArrayList;
        this.context = context;


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(listfiltr.get(position).getTitle());
        holder.body.setText(listfiltr.get(position).getBody());
        int id = listfiltr.get(position).getUserID();
        db = Room.databaseBuilder(context, DataBaseHelper.class, "data-database")
                .allowMainThreadQueries()
                .build();
        for (int i = 0; i < userlist.size(); i++) {
            if (id == userlist.get(i).getId()) {
                holder.name.setText(userlist.get(i).getName());
                Pictures pictures = new Pictures();
                String url = pictures.getUrl(i);

                Picasso.get().load(url).into(holder.imageView);

                break;
            }
        }

        boolean isliked = readBool(String.valueOf(listfiltr.get(position).getId()));
        if (isliked) {
            holder.like.setImageResource(R.drawable.ic_favorite_black_24dp);

        } else {
            holder.like.setImageResource(R.drawable.ic_favorite_border_black_24dp);
        }


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
                    listfiltr = modelArrayList;
                } else {
                    List<Post> filtredList = new ArrayList<>();
                    for (Post s : modelArrayList) {
                        if (s.getTitle().toLowerCase().contains(charString.toLowerCase())) {
                            filtredList.add(s);
                        }

                    }
                    listfiltr = filtredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = listfiltr;
                filterResults.count = listfiltr.size();
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults results) {
                listfiltr = (ArrayList<Post>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, body, name, popapmenu;
        CircleImageView imageView;
        ImageView comment, like;

        MyViewHolder(View view) {
            super(view);
            like = view.findViewById(R.id.like_post);
            title = view.findViewById(R.id.title_post);
            body = view.findViewById(R.id.post_post);
            name = view.findViewById(R.id.name_post);
            imageView = view.findViewById(R.id.img_post);
            comment = view.findViewById(R.id.post_coment);
            popapmenu = view.findViewById(R.id.textViewOptions);
            popapmenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getPopupMeniu(view,context, getAdapterPosition());
                }
            });
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
                    boolean isliked = readBool(String.valueOf(listfiltr.get(getAdapterPosition()).getId()));
                    if (isliked) {
                        saveBool(String.valueOf(listfiltr.get(getAdapterPosition()).getId()), false);
                        like.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                        db.getDataDao().delete(listfiltr.get(getAdapterPosition()));
                        int id = listfiltr.get(getAdapterPosition()).getUserID();
                        for (int i = 0; i < userlist.size(); i++) {
                            if (id == userlist.get(i).getId()) {
                                saveBool(String.valueOf(userlist.get(i).getName()), false);
                                db.getDataDao().delete(userlist.get(i));
                                break;
                            }
                        }

                    } else {
                        saveBool(String.valueOf(listfiltr.get(getAdapterPosition()).getId()), true);
                        like.setImageResource(R.drawable.ic_favorite_black_24dp);
                        db.getDataDao().insert(listfiltr.get(getAdapterPosition()));
                        int id = listfiltr.get(getAdapterPosition()).getUserID();
                        for (int i = 0; i < userlist.size(); i++) {
                            if (id == userlist.get(i).getId()) {
                                boolean userlik = readBool(String.valueOf(userlist.get(i).getName()));
                                if (!userlik) {
                                    db.getDataDao().insert(userlist.get(i));
                                    saveBool(String.valueOf(userlist.get(i).getName()), true);
                                    Log.i("==p", "onClick: ");
                                }

                                break;
                            }
                        }
                    }
                }
            });


        }

        void getPopupMeniu(View view, Context context, final int i) {
            PopupMenu popup = new PopupMenu(context, view);
            popup.inflate(R.menu.popup);
            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.menu1:
                        listfiltr.remove(i);
                        notifyDataSetChanged();
                            return true;
                                  default:
                            return false;
                    }
                }
            });
            popup.show();

        }

        private void goToUserPageActivity() {
            Intent intent = new Intent(itemView.getContext(), UserPageActivity.class);
            intent.putExtra("ID", listfiltr.get(getAdapterPosition()).getUserID());
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

    private boolean readBool(String str) {
        SharedPreferences prefs = context.getSharedPreferences(str, MODE_PRIVATE);
        if (prefs != null) {
            return prefs.getBoolean(str, false);
        }
        return false;
    }


}