package com.example.todolist_java_dvfu.data;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.todolist_java_dvfu.model.Task;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM task")
    List<Task> getAllTask();

    @Query("SELECT * FROM task")
    LiveData<List<Task>> getAllLiveDataTask();

    @Query("SELECT * FROM task WHERE uid IN (:taskIds)")
    List<Task> loadAllByIdsTask(int[] taskIds);

    @Query("SELECT * FROM task WHERE uid = :uid")
    Task findByIdTask(int uid);

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insertTask(Task tasks);

    @Update
    void updateTask(Task task);

    @Delete
    void deleteTask(Task task);

}