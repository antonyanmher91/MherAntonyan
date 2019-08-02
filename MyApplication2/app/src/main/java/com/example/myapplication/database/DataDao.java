package com.example.myapplication.database;



import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.myapplication.model.Post;
import com.example.myapplication.model.User;

import java.util.List;


@Dao
public interface DataDao {
    @Insert
    void insert(Post post);
    @Insert
    void insert(User user);
    @Delete
    void delete(Post post);
    @Delete
    void delete(User user);
    @Query("SELECT * FROM post")
    List<Post> getAllPosts();
    @Query("SELECT * FROM user")
    List<User> getAllUser();


}
