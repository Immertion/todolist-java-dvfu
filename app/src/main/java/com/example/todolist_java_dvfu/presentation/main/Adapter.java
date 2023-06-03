package com.example.todolist_java_dvfu.presentation.main;

import android.app.Activity;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;

import com.example.todolist_java_dvfu.App;
import com.example.todolist_java_dvfu.R;
import com.example.todolist_java_dvfu.model.Task;
import com.example.todolist_java_dvfu.presentation.taskFace.taskFaceActivity;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.TaskViewHolder> {

    private SortedList<Task> sortedList;

    public Adapter() {
        sortedList = new SortedList<>(Task.class, new SortedList.Callback<Task>() {
            @Override
            public int compare(Task taskUp, Task taskDown) {
                if (!taskDown.done && taskUp.done) {
                    return 1;
                }
                if (taskDown.done && !taskUp.done) {
                    return -1;
                }
                return (int) (taskDown.timestamp - taskUp.timestamp);
            }

            @Override
            public void onChanged(int position, int count) {
                notifyItemRangeChanged(position, count);
            }

            @Override
            public boolean areContentsTheSame(Task oldItem, Task newItem) {
                return oldItem.equals(newItem);
            }

            @Override
            public boolean areItemsTheSame(Task item1, Task item2) {
                return item1.uid == item2.uid;
            }

            @Override
            public void onInserted(int position, int count) {
                notifyItemRangeInserted(position, count);
            }

            @Override
            public void onRemoved(int position, int count) {
                notifyItemRangeRemoved(position, count);
            }

            @Override
            public void onMoved(int fromPosition, int toPosition) {
                notifyItemMoved(fromPosition, toPosition);
            }
        });

    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TaskViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task_list, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        holder.bind(sortedList.get(position));
    }

    @Override
    public int getItemCount() {
        return sortedList.size();
    }

    public void setItems(List<Task> tasks){
        sortedList.replaceAll(tasks);
    }

    static class TaskViewHolder extends RecyclerView.ViewHolder {

        TextView taskText;
        CheckBox done;
        View delete;

        Task task;

        boolean middleUpdate;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);

            taskText = itemView.findViewById(R.id.task_text);
            done = itemView.findViewById(R.id.done);
            delete = itemView.findViewById(R.id.delete);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    taskFaceActivity.start((Activity) itemView.getContext(), task);
                }
            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    App.getInstance().getTaskDao().deleteTask(task);
                }
            });

            done.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                    if (!middleUpdate){
                        task.done = checked;
                        App.getInstance().getTaskDao().updateTask(task);
                    }
                    crossOutTask();
                }
            });
        }

        public void bind(Task tsak) {
            this.task = task;

            taskText.setText(task.text);
            crossOutTask();

            middleUpdate = true;
            done.setChecked(task.done);
            middleUpdate = false;
            ;
        }

        private void crossOutTask(){
            if(task.done){
                taskText.setPaintFlags(taskText.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                taskText.setPaintFlags(taskText.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);

            }
        }
    }
}
