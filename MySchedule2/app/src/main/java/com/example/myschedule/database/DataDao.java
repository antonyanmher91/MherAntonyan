package com.example.myschedule.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.myschedule.Models.CategoryModel;
import com.example.myschedule.Models.TaskModel;

import java.util.List;

@Dao

public interface DataDao {
    @Insert
    void insert(CategoryModel dataModel);
    @Insert
    void insertTask(TaskModel taskModel);

    @Delete
    void delete(CategoryModel dataModel);

    @Delete
    void deletetask(TaskModel dataModel);

    @Query("SELECT * FROM CategoryModel")
    List<CategoryModel> getAllData();

    @Query("SELECT * FROM TaskModel")
    List<TaskModel> getAllDataTask();
}
