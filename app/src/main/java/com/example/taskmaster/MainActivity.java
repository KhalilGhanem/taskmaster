package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button addTaskLink=findViewById(R.id.button);
        addTaskLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goTOAddTask =new Intent(MainActivity.this,AddTask.class);
                startActivity(goTOAddTask);
            }
        });

        Button allTasks=findViewById(R.id.button2);
        allTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotToAllTasks=new Intent(MainActivity.this,AllTasks.class);
                startActivity(gotToAllTasks);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(),"onStart..!",Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(),"onResume..!",Toast.LENGTH_LONG).show();

        // Task 1
        Button dCC=findViewById(R.id.DoCCButton);
        String task1=dCC.getText().toString();
        dCC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goToTaskDetail=new Intent(MainActivity.this,TaskDetail.class);
                goToTaskDetail.putExtra("task",task1);
                startActivity(goToTaskDetail);
            }
        });
        // Task 2
        Button addReadme=findViewById(R.id.AddReadmeButton);
        String task2=addReadme.getText().toString();
        addReadme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToTaskDetail=new Intent(MainActivity.this,TaskDetail.class);
                goToTaskDetail.putExtra("task",task2);
                startActivity(goToTaskDetail);
            }
        });
        // Task 3
        Button sleep=findViewById(R.id.SleepButton);
        String task3=sleep.getText().toString();
        sleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToTaskDetail=new Intent(MainActivity.this,TaskDetail.class);
                goToTaskDetail.putExtra("task",task3);
                startActivity(goToTaskDetail);
            }
        });
        // SettingsButton
        Button SettingButton=findViewById(R.id.SettingsButton);
        SettingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToSettingPage=new Intent(MainActivity.this,Settings.class);
                startActivity(goToSettingPage);
            }
        });
        String msg=" ’s tasks";
        //get UserName
        SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String userName=sharedPreferences.getString("userName","user");

        TextView userTasksView=findViewById(R.id.userTasksView);
        userTasksView.setText(userName+msg);


    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(getApplicationContext(),"onPause..!",Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(getApplicationContext(),"onStop..!",Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(getApplicationContext(),"onRestart..!",Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(),"onDestroy..!",Toast.LENGTH_LONG).show();

    }
}