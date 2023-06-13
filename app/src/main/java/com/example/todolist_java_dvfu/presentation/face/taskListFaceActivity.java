package com.example.todolist_java_dvfu.presentation.face;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist_java_dvfu.App;
import com.example.todolist_java_dvfu.R;
import com.example.todolist_java_dvfu.model.ListTask;
import com.example.todolist_java_dvfu.model.Task;
import com.example.todolist_java_dvfu.presentation.adapter.taskAdapter;
import com.example.todolist_java_dvfu.presentation.main.MainViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
//import com.example.todolist_java_dvfu.model.Task;

public class taskListFaceActivity  extends AppCompatActivity {

    private static final String EXTRA_LIST = "taskListFaceActivity.EXTRA_LIST";

    private ListTask list;

    private RecyclerView recyclerView;

    private EditText editText;

    public static void start(Activity caller, ListTask list){
        Intent intent = new Intent(caller, taskListFaceActivity.class);
        if (list != null){
            intent.putExtra(EXTRA_LIST, list);
        }
        caller.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_face);

//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeButtonEnabled(true);

        setTitle(R.string.list_face_title);

        recyclerView = findViewById(R.id.tasks);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        final taskAdapter adapter = new taskAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.getRecycledViewPool().clear();


        editText = findViewById(R.id.title);


        if (getIntent().hasExtra(EXTRA_LIST)){
            list = getIntent().getParcelableExtra(EXTRA_LIST);
            editText.setText(list.title);
        } else{
            list = new ListTask();
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                taskFaceActivity.start(taskListFaceActivity.this, null);
            }
        });

        MainViewModel mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.getTaskLiveData().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                adapter.notifyDataSetChanged();
                adapter.setItems(tasks);

            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_task, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.action_save:
                if(editText.getText().length() > 0){
                    list.title = editText.getText().toString();
                    list.timestamp = System.currentTimeMillis();
                    if (getIntent().hasExtra(EXTRA_LIST)){
                        App.getInstance().getTaskListDao().updateList(list);
                    } else{
                        App.getInstance().getTaskListDao().insertList(list);
                    }
                    finish();
                }
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}

