package com.example.todolist_java_dvfu.presentation.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.todolist_java_dvfu.App;
import com.example.todolist_java_dvfu.model.ListTask;
import com.example.todolist_java_dvfu.model.Task;

import java.util.List;

public class MainViewModel extends ViewModel {
    private LiveData<List<Task>> taskLiveData = App.getInstance().getTaskDao().getAllLiveDataTask();

    private LiveData<List<ListTask>> listLiveData = App.getInstance().getTaskListDao().getAllLiveDataList();

    public LiveData<List<ListTask>> getListLiveData(){
        return listLiveData;
    }
    public LiveData<List<Task>> getTaskLiveData(){
        return taskLiveData;
    }
}
