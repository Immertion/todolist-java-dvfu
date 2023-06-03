package com.example.todolist_java_dvfu.presentation.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.todolist_java_dvfu.App;
import com.example.todolist_java_dvfu.model.Task;

import java.util.List;

public class MainViewModel extends ViewModel {
    private LiveData<List<Task>> taskLiveData = App.getInstance().getTaskDao().getAllLiveDataTask();

    public LiveData<List<Task>> getTaskLiveData(){
        return taskLiveData;
    }
}
