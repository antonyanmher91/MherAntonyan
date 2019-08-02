package com.example.myapplication.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myapplication.ApiInterface;
import com.example.myapplication.R;
import com.example.myapplication.adapter.Comments_Adapter;
import com.example.myapplication.model.Comments;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Comments_Fragment extends Fragment {

    private RecyclerView recyclerView;
    private Comments_Adapter recyclerAdapter;
    private List<Comments> list;
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    public Comments_Fragment() {
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comments_, container, false);
        assert getArguments() != null;
        int id = getArguments().getInt("ID");


        recyclerView = view.findViewById(R.id.rec_view_comment);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<List<Comments>> user = apiInterface.getCommentId(id);
        user.enqueue(new Callback<List<Comments>>() {
            @Override
            public void onResponse(@NonNull Call<List<Comments>> call1, @NonNull Response<List<Comments>> response2) {
                list = response2.body();
                recyclerAdapter= new Comments_Adapter(list);
                recyclerView.setAdapter(recyclerAdapter);

            }

            @Override
            public void onFailure(@NonNull Call<List<Comments>> call1, @NonNull Throwable t) {
                Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

}
