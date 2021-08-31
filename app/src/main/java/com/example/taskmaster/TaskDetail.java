package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.String.valueOf;

public class TaskDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent=getIntent();
        String task=intent.getExtras().getString("task");
        String taskTitle=intent.getExtras().getString("title");
        String taskStateSt=intent.getExtras().getString("state");
        String taskBodySt=intent.getExtras().getString("body");
        TextView TaskName=findViewById(R.id.TaskDetailTitle);
        TextView taskBody=findViewById(R.id.TaskDetailBody);
        TextView taskState=findViewById(R.id.TaskDetailState);
        boolean test=taskTitle.isEmpty();
//        String msg=valueOf(test);
//        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG);
        if (taskTitle=="" && taskStateSt==""&& taskTitle==""){
            TaskName.setText(task);

        }else {

            TaskName.setText(taskTitle);
            taskBody.setText(taskBodySt);
            taskState.setText(taskStateSt);
        }
        Button goHome=findViewById(R.id.TaskDetailHomeButton);
        goHome.setOnClickListener(view -> {
            Intent intent1=new Intent(TaskDetail.this,MainActivity.class);
            startActivity(intent1);
        });

    }
}