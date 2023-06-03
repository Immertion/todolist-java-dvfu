package com.example.todolist_java_dvfu.data;

import androidx.room.Database;

import com.example.todolist_java_dvfu.model.Task;

@Database(entities = {Task.class}, version = 1, exportSchema = false)
public abstract class AppDatabase {
    public abstract TaskDao taskDao();

}
