package com.example.todolist_java_dvfu;

import android.app.Application;

import androidx.room.Room;

import com.example.todolist_java_dvfu.data.AppDatabase;
import com.example.todolist_java_dvfu.data.ListTaskDao;
import com.example.todolist_java_dvfu.data.TaskDao;
import com.example.todolist_java_dvfu.data.ListTaskDao;
import com.example.todolist_java_dvfu.model.ListTask;

public class App extends Application {

    private AppDatabase database;
    private TaskDao taskDao;

    private ListTaskDao taskListDao;

    private  static App instance;

    public static App getInstance(){
        return instance;
    }

    @Override
    public void onCreate(){
        super.onCreate();

        instance = this;

        database = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "TaskList_DB_31")
                .allowMainThreadQueries()
                .build();

        taskDao = database.taskDao();
        taskListDao = database.taskListDao();


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

    public ListTaskDao getTaskListDao() {
        return taskListDao;
    }
    public void setTaskListDao(ListTaskDao taskListDao) {
        this.taskListDao = taskListDao;
    }
    public void setTaskDao(TaskDao taskDao) {
        this.taskDao = taskDao;
    }
}

