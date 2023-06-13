package com.example.todolist_java_dvfu.presentation.main;

import android.os.Bundle;

import com.example.todolist_java_dvfu.R;
import com.example.todolist_java_dvfu.model.ListTask;
import com.example.todolist_java_dvfu.model.Task;
import com.example.todolist_java_dvfu.presentation.adapter.listTaskAdapter;


import com.example.todolist_java_dvfu.presentation.adapter.taskAdapter;
import com.example.todolist_java_dvfu.presentation.face.taskFaceActivity;
import com.example.todolist_java_dvfu.presentation.face.taskListFaceActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
//import com.example.todolist_java_dvfu.presentation.main.taskAdapter;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);

//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);




        recyclerView = findViewById(R.id.main_lists);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        final listTaskAdapter adapter = new listTaskAdapter();
        recyclerView.setAdapter(adapter);



        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                taskListFaceActivity.start(MainActivity.this, null);
            }
        });

        MainViewModel mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.getListLiveData().observe(this, new Observer<List<ListTask>>() {
            @Override
            public void onChanged(List<ListTask> lists) {
                adapter.notifyDataSetChanged();
                adapter.setItems(lists);

            }
        });
    }



}