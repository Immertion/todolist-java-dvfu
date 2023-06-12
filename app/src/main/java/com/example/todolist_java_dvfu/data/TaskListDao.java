package com.example.todolist_java_dvfu.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.todolist_java_dvfu.model.ListTask;
import com.example.todolist_java_dvfu.model.Task;

import java.util.List;

@Dao
public interface TaskListDao {
    @Query("SELECT * FROM ListTask")
    List<Task> getAllList();

    @Query("SELECT * FROM ListTask")
    LiveData<List<ListTask>> getAllLiveDataList();

    @Query("SELECT * FROM ListTask WHERE uid = :uid LIMIT 1")
    Task findByIdList(int uid);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertList(ListTask listTask);

    @Update
    void updateList(ListTask listTask);

    @Delete
    void deleteList(ListTask listTask);

}
