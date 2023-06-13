package com.example.todolist_java_dvfu.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.todolist_java_dvfu.model.ListTask;
import com.example.todolist_java_dvfu.model.Task;

@Database(entities = {ListTask.class, Task.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ListTaskDao taskListDao();
    public abstract TaskDao taskDao();

}
