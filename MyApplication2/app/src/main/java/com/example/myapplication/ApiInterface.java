package com.example.myapplication;


import com.example.myapplication.model.Comments;
import com.example.myapplication.model.Post;
import com.example.myapplication.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {


    @GET("posts")
    Call<List<Post>> getPost();
    @GET("users")
    Call<List<User>> getUser();
    @GET("comments")
    Call<List<Comments>> getCommentId(@Query("postId") int id);
    @GET("posts")
    Call<List<Post>> getPostUser(@Query("userId") int id);
    @GET("users")
    Call<List<User>> getUserID(@Query("id") int id);
}
