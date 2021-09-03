package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class AddTask extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    AppDatabase appDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        Button addTask=findViewById(R.id.addTaskButton);
        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"submitted!",Toast.LENGTH_LONG).show();

            }
        });
        EditText taskTitle=findViewById(R.id.editTextTextPersonName);
        EditText taskBody=findViewById(R.id.editTextTextPersonName2);

        Spinner taskState=findViewById(R.id.taskspinner);
        ArrayAdapter<CharSequence> taskStatesAdapter= ArrayAdapter.createFromResource(this,R.array.taskState,R.layout.support_simple_spinner_dropdown_item);
        taskState.setAdapter(taskStatesAdapter);
        taskState.setOnItemSelectedListener(this);


        appDatabase =  Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "task").allowMainThreadQueries().build();
        TaskDao taskDao=appDatabase.taskDao();

        List<Task>allTasks=appDatabase.taskDao().getAll();
        String total="Total Tasks: ";
        TextView totalTasks=findViewById(R.id.totalTasks);
        totalTasks.setText(total+allTasks.size());



        Button addTaskButton=findViewById(R.id.addTaskButton);
        addTaskButton.setOnClickListener(view -> {
            Task newTask=new Task(taskTitle.getText().toString(),taskBody.getText().toString(),taskState.getSelectedItem().toString());
            taskDao.insertAll(newTask);


            Intent goToHome=new Intent(this,MainActivity.class);
            startActivity(goToHome);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}