package com.example.todolist_java_dvfu.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.todolist_java_dvfu.model.Task;

@Database(entities = {Task.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();

}
