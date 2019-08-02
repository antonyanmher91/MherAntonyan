package com.example.myapplication.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import com.example.myapplication.R;
import com.example.myapplication.adapter.LikeRecView;
import com.example.myapplication.database.DataBaseHelper;
import com.example.myapplication.model.Post;
import com.example.myapplication.model.User;
import java.util.List;
import java.util.Objects;


public class Like_Fragment extends Fragment {

    public Like_Fragment() {
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_like, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.res_view_like);
        DataBaseHelper db = Room.databaseBuilder(Objects.requireNonNull(getContext()), DataBaseHelper.class, "data-database")
                .allowMainThreadQueries()
                .build();
        List<Post> postList = db.getDataDao().getAllPosts();
        List<User> userList = db.getDataDao().getAllUser();
        LikeRecView likeRecView = new LikeRecView(postList, userList,getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(likeRecView);
        return view;
    }

}
