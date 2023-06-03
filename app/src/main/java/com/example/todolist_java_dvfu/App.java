package com.example.todolist_java_dvfu;

import android.app.Application;

import androidx.room.Room;

import com.example.todolist_java_dvfu.data.AppDatabase;
import com.example.todolist_java_dvfu.data.TaskDao;

public class App extends Application {

    private AppDatabase database;
    private TaskDao taskDao;

    private  static App instance;

    public static App getInstance(){
        return instance;
    }

    @Override
    public void onCreate(){
        super.onCreate();

        database = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "DB_Task")
                .allowMainThreadQueries()
                .build();

        taskDao = database.taskDao();
    }

    public AppDatabase getDatabase() {
        return database;
    }

    public void setDatabase(AppDatabase database) {
        this.database = database;
    }

    public TaskDao getTaskDao() {
        return taskDao;
    }

    public void setTaskDao(TaskDao taskDao) {
        this.taskDao = taskDao;
    }
}

