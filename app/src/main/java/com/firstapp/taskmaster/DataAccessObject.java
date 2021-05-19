package com.firstapp.taskmaster;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DataAccessObject {
    @Query("SELECT * FROM task")
    List<TaskModel> getAllTasks();

    @Query("SELECT * FROM task WHERE id = :id")
    TaskModel loadOneById(int id);

    @Insert
    void insertOne (TaskModel task);

    @Delete
    void deleteOne(TaskModel taskMode);
}