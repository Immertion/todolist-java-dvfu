package com.example.todolist_java_dvfu.presentation.face;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.todolist_java_dvfu.App;
import com.example.todolist_java_dvfu.R;
import com.example.todolist_java_dvfu.model.Task;

public class taskFaceActivity  extends AppCompatActivity {

    private static final String EXTRA_TASK = "taskFaceActivity.EXTRA_TASK";

    private Task task;

    private EditText editText;

    public static void start(Activity caller, Task task){
        Intent intent = new Intent(caller, taskFaceActivity.class);
        if (task != null){
            intent.putExtra(EXTRA_TASK, task);
        }
        caller.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_task_face);

//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeButtonEnabled(true);

        setTitle(R.string.task_face_title);

        editText = findViewById(R.id.text);

        if (getIntent().hasExtra(EXTRA_TASK)){
            task = getIntent().getParcelableExtra(EXTRA_TASK);
            editText.setText(task.text);
        } else{
            task = new Task();
        }
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
                    task.text = editText.getText().toString();
                    task.done = false;
                    task.important = false;
                    task.timestamp = System.currentTimeMillis();
                    if (getIntent().hasExtra(EXTRA_TASK)){
                        App.getInstance().getTaskDao().updateTask(task);
                    } else{
                        App.getInstance().getTaskDao().insertTask(task);
                    }
                    finish();
                }
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
