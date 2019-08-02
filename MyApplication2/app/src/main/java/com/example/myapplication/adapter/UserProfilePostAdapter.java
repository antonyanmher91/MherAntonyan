package com.example.myapplication.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import com.example.myapplication.ApiInterface;
import com.example.myapplication.R;
import com.example.myapplication.activity.UserPageActivity;
import com.example.myapplication.database.DataBaseHelper;
import com.example.myapplication.fragment.Comments_Fragment;
import com.example.myapplication.model.Pictures;
import com.example.myapplication.model.Post;
import com.example.myapplication.model.User;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;

public class UserProfilePostAdapter extends RecyclerView.Adapter<UserProfilePostAdapter.ViewHolder> {
    private List<Post> list;
    private String name;
    private int id;
    private Context context;
    private DataBaseHelper db;
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private List<User> listUser;

    public UserProfilePostAdapter(List<Post> list, String name, int id, Context context) {
        this.list = list;
        this.name = name;
        this.id = id;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(list.get(position).getTitle());
        holder.body.setText(list.get(position).getBody());
        holder.userName.setText(name);
        Pictures pictures = new Pictures();
        String url = pictures.getUrl(id - 1);
        Picasso.get().load(url).into(holder.imageView);
        db = Room.databaseBuilder(context, DataBaseHelper.class, "data-database")
                .allowMainThreadQueries()
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<List<User>> user = apiInterface.getUserID(id);
        user.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(@NonNull Call<List<User>> call1, @NonNull Response<List<User>> response2) {
                listUser = response2.body();


            }

            @Override
            public void onFailure(@NonNull Call<List<User>> call1, @NonNull Throwable t) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, body, userName;
        CircleImageView imageView;
        ImageView comment, like;


        ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_post);
            body = itemView.findViewById(R.id.post_post);
            userName = itemView.findViewById(R.id.name_post);
            imageView = itemView.findViewById(R.id.img_post);
            comment = itemView.findViewById(R.id.post_coment);
            like = itemView.findViewById(R.id.like_post);
            comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    goToComentsFragment(view);
                }
            });
            like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    boolean isliked = readBool(String.valueOf(list.get(getAdapterPosition()).getId()));
                    if (isliked) {
                        saveBool(String.valueOf(list.get(getAdapterPosition()).getId()), false);
                        like.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                        db.getDataDao().delete(list.get(getAdapterPosition()));
//                        db.getDataDao().delete(listUser.get(0));
                        saveBool(name, false);
                    } else {
                        saveBool(String.valueOf(list.get(getAdapterPosition()).getId()), true);
                        like.setImageResource(R.drawable.ic_favorite_black_24dp);
                        db.getDataDao().insert(list.get(getAdapterPosition()));
                        boolean userlik=readBool(name);
                        if (!userlik){
                        saveBool(String.valueOf(list.get(getAdapterPosition()).getId()), true);

                                    db.getDataDao().insert(listUser.get(0));
                            saveBool(name, true);
                                    Log.i("==p", "onClick: ");
                                }}
                    }
            });

        }

        private void goToComentsFragment(View view) {
            Comments_Fragment cf = new Comments_Fragment();
            Bundle bundle = new Bundle();
            bundle.putInt("ID", getAdapterPosition() + 1);
            cf.setArguments(bundle);
            UserPageActivity activity = (UserPageActivity) view.getContext();
            FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager()
                    .beginTransaction().replace(R.id.LL, cf).addToBackStack(null);
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
