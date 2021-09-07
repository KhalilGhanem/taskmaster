package com.example.taskmaster;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.Todo;

import java.util.ArrayList;
import java.util.List;
 //RecyclerView.Adapter<StudentAdapter.StudentViewHolder>{
public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    List<Todo> allTasks=new ArrayList<Todo>();
    Context context;

    public TaskAdapter(List<Todo> allTasks, Context context) {
        this.allTasks = allTasks;
        this.context=context;
    }


     public static class TaskViewHolder extends RecyclerView.ViewHolder {
        public Todo task;

        View itemView;

        public TaskViewHolder(@NonNull View itemView){
            super(itemView);
            this.itemView=itemView;
        }
    }

     @NonNull
     @Override
     public TaskViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_task,parent,false);
        TaskViewHolder taskViewHolder=new TaskViewHolder(view);
        //action listeners
         // view.setOnClickListener(); --> have an intent
         return taskViewHolder;
     }

     @Override
     public void onBindViewHolder(@NonNull TaskAdapter.TaskViewHolder holder, int position) {
       holder.task=allTasks.get(position);

         TextView taskTitle=holder.itemView.findViewById(R.id.taskTitleInFragment);
//         TextView taskBody=holder.itemView.findViewById(R.id.taskBodyInFragment);
         TextView taskState=holder.itemView.findViewById(R.id.taskStateInFragment);

         taskTitle.setText(holder.task.getTaskTitle());
//         taskBody.setText(holder.task.body);
         taskState.setText(holder.task.getTaskState());

         holder.itemView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent=new Intent(context,TaskDetail.class);
                 intent.putExtra("title",holder.task.getTaskTitle());
                 intent.putExtra("body",holder.task.getTaskBody());
                 intent.putExtra("state",holder.task.getTaskState());
                 context.startActivity(intent);
             }
         });

     }

     @Override
     public int getItemCount() {
         return allTasks.size();
     }
}
