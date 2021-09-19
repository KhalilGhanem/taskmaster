package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Team;
import com.amplifyframework.datastore.generated.model.Todo;

import java.util.ArrayList;
import java.util.List;

public class AddTask extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
//    AppDatabase appDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        // Lab41-Intent Filters receive data from another app
        Intent intent = getIntent();
        String type = intent.getType();
        if(type!=null && type.equals("text/plain")){
            EditText taskDescription=findViewById(R.id.editTextTextPersonName2);
            taskDescription.setText(intent.getStringExtra(Intent.EXTRA_TEXT));
        }


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

        RadioButton team1Button=findViewById(R.id.team1RadioButton);
        RadioButton team2Button=findViewById(R.id.team2RadioButton);
        RadioButton team3Button=findViewById(R.id.team3RadioButton);

//        Spinner taskTeam=findViewById(R.id.teamSpinner);
//        ArrayAdapter<CharSequence> taskTeamAdapter=ArrayAdapter.createFromResource(this,R.array.taskTeam,R.layout.support_simple_spinner_dropdown_item);
//        taskTeam.setAdapter(taskTeamAdapter);
//        taskTeam.setOnItemSelectedListener(this);

        //Get team data
        List<Team>allTeams=new ArrayList<Team>();
        Amplify.API.query(
                ModelQuery.list(Team.class ),
                response -> {
                    for (Team team : response.getData()) {
                        Log.i("MyAmplifyApp", team.getName());
                        allTeams.add(team);
                    }
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );


//        appDatabase =  Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "task").allowMainThreadQueries().build();
//        TaskDao taskDao=appDatabase.taskDao();
//
//        List<Task>allTasks=appDatabase.taskDao().getAll();
//        String total="Total Tasks: ";
//        TextView totalTasks=findViewById(R.id.totalTasks);
//        totalTasks.setText(total+allTasks.size());



        Button addTaskButton=findViewById(R.id.addTaskButton);
        addTaskButton.setOnClickListener(view -> {

            // using room
//            Task newTask=new Task(taskTitle.getText().toString(),taskBody.getText().toString(),taskState.getSelectedItem().toString());
//            taskDao.insertAll(newTask);

            // create team object
            Team newTeam=null;
            if (team1Button.isChecked()){
                newTeam=allTeams.get(2);
            }else if (team2Button.isChecked()){
                newTeam=allTeams.get(1);
            }else if (team3Button.isChecked()){
                newTeam=allTeams.get(0);
            }
            // try with Spinner
//            String teamStr=taskTeam.getSelectedItem().toString();
//            String teamStr="";
//            System.out.println("spinner team:  +  "+ teamStr +"");
//            System.out.println("cond");
//            System.out.println("condition+  "+ teamStr=="team1");
//            if (teamStr=="team1"){
//                newTeam=allTeams.get(2);
//            }else if (teamStr=="team2"){
//                newTeam=allTeams.get(1);
//            }else if (teamStr=="team3"){
//                newTeam=allTeams.get(0);
//            }
//            System.out.println("test cond"+ taskTeam.getSelectedItem().toString()==allTeams.get(0).getName());
//            if (teamStr==allTeams.get(0).getName()){
//                newTeam=allTeams.get(0);
//            }else if (teamStr==allTeams.get(1).getName()){
//                newTeam=allTeams.get(1);
//            }else if (teamStr==allTeams.get(2).getName()){
//                newTeam=allTeams.get(2);
//            }
//            for (int i=0;i<allTeams.size();i++){
//                System.out.println(i+" "+allTeams.get(i));
//                System.out.println(teamStr+"  "+i);
//                System.out.println(allTeams.get(i).getName()+" test team array");
//                System.out.println("last condition");
//                System.out.println(teamStr=="team1");
//
////                if(teamStr==allTeams.get(i).getName());{
//                    newTeam=allTeams.get(2);
////                    System.out.println("its woooooooook "+i);
////                }
//            }

            Todo todo = Todo.builder()
                    .taskTitle(taskTitle.getText().toString())
                    .taskBody(taskBody.getText().toString())
                    .taskState(taskState.getSelectedItem().toString())
                    .team(newTeam)
                    .build();

            Amplify.API.mutate(
                    ModelMutation.create(todo),
                    response -> Log.i("MyAmplifyApp", "Added Todo with id: " + response.getData().getId()),
                    error -> Log.e("MyAmplifyApp", "Create failed", error)
            );

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