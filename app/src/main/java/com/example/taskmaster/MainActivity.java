package com.example.taskmaster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.Callback;
import com.amazonaws.mobile.client.UserStateDetails;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.pinpoint.PinpointConfiguration;
import com.amazonaws.mobileconnectors.pinpoint.PinpointManager;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.auth.cognito.activities.HostedUIRedirectActivity;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Team;
import com.amplifyframework.datastore.generated.model.Todo;
import com.amplifyframework.storage.s3.AWSS3StoragePlugin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.android.gms.tasks.Task;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
//    AppDatabase appDatabase;
public static final String TAG = MainActivity.class.getSimpleName();

    private static PinpointManager pinpointManager;

    public static PinpointManager getPinpointManager(final Context applicationContext) {
        if (pinpointManager == null) {
            final AWSConfiguration awsConfig = new AWSConfiguration(applicationContext);
            AWSMobileClient.getInstance().initialize(applicationContext, awsConfig, new Callback<UserStateDetails>() {
                @Override
                public void onResult(UserStateDetails userStateDetails) {
                    Log.i("INIT", userStateDetails.getUserState().toString());
                }

                @Override
                public void onError(Exception e) {
                    Log.e("INIT", "Initialization error.", e);
                }
            });

            PinpointConfiguration pinpointConfig = new PinpointConfiguration(
                    applicationContext,
                    AWSMobileClient.getInstance(),
                    awsConfig);

            pinpointManager = new PinpointManager(pinpointConfig);

            FirebaseMessaging.getInstance().getToken()
                    .addOnCompleteListener(new OnCompleteListener<String>() {
                        @Override
                        public void onComplete(@NonNull com.google.android.gms.tasks.Task<String> task) {
                            if (!task.isSuccessful()) {
                                Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                                return;
                            }
                            final String token = task.getResult();
                            Log.d(TAG, "Registering push notifications token: " + token);
                            pinpointManager.getNotificationClient().registerDeviceToken(token);
                        }
                    });
        }
        return pinpointManager;
    }

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            // Add these lines to add the AWSApiPlugin plugins
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
//            Amplify.addPlugin(new AWSS3StoragePlugin());
            Amplify.configure(getApplicationContext());
            getPinpointManager(getApplicationContext());
            Log.i("MyAmplifyApp", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
        }
        Amplify.Auth.signInWithWebUI(
                this,
                result -> Log.i("AuthQuickStart", result.toString()),
                error -> Log.e("AuthQuickStart", error.toString())
        );



        // sign up do it in a new activity and redirect us into confirm sign up activity.
//        AuthSignUpOptions options = AuthSignUpOptions.builder()
//                .userAttribute(AuthUserAttributeKey.email(), "khalil_ghanem7@eyahoo.com")
//                .build();
//        Amplify.Auth.signUp("KG", "10521", options,
//                result -> Log.i("AuthQuickStart", "Result: " + result.toString()),
//                error -> Log.e("AuthQuickStart", "Sign up failed", error)
//        );
        // confirm sign up
//        Amplify.Auth.confirmSignUp(
//                "username",
//                "the code you received via email",
//                result -> Log.i("AuthQuickstart", result.isSignUpComplete() ? "Confirm signUp succeeded" : "Confirm sign up not complete"),
//                error -> Log.e("AuthQuickstart", error.toString())
//        );
//        add teams
//        team1 id:13e28201-9359-4c3e-8ce0-9e919145d9e1
//        team2 id:42ea03d4-4cd8-4130-88f1-736353286b24
//        team3 id:f290173a-ec7b-413c-9fa6-a7ba448a6581
//        for (int i=1;i<4;i++) {
//            Team team = Team.builder()
//                    .name("team"+i)
//                    .build();
//            Amplify.API.mutate(
//                    ModelMutation.create(team),
//                    response -> Log.i("MyAmplifyApp", "Added team  with id: " + response.getData().getId()),
//                    error -> Log.e("MyAmplifyApp", "Create failed", error)
//            );
//        }
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

        // recyclerview
//        ArrayList<Task> allTasksList=new ArrayList<Task>();
//        allTasksList.add(new Task("Football","football, also called association football or soccer, game in which two teams of 11 players, using any part of their bodies except their hands and arms, try to maneuver the ball into the opposing team???s goal. Only the goalkeeper is permitted to handle the ball and may do so only within the penalty area surrounding the goal. The team that scores more goals wins.","new"));
//        allTasksList.add(new Task("Do Labs","Working on it...","in progress"));
//        allTasksList.add(new Task("Workout","planning for...","assigned"));
//
//        RecyclerView allTasksRecyclerView=findViewById(R.id.TasksListRecyclerView);
//        allTasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        allTasksRecyclerView.setAdapter(new TaskAdapter(allTasksList,this));



    }

    @Override
    protected void onStart() {
        super.onStart();
//        Toast.makeText(getApplicationContext(),"onStart..!",Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onResume() {
        super.onResume();
        //Room
//        appDatabase =  Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "task").allowMainThreadQueries().build();
//        List<Task>allTasks=appDatabase.taskDao().getAll();

        SharedPreferences sharedPreferences1=PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String taskTeam=sharedPreferences1.getString("teamName","team");

        //amplify
        Handler handler=new Handler(Looper.getMainLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message message) {
                recyclerView.getAdapter().notifyDataSetChanged();
                return false;
            }
        });

        List<Todo>allTasks=new ArrayList<Todo>();
        List<Team>allTeams=new ArrayList<>();

        recyclerView=findViewById(R.id.TasksListRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new TaskAdapter(allTasks,this));

        Amplify.API.query(
                ModelQuery.list(com.amplifyframework.datastore.generated.model.Team.class),
                response -> {
                    for (Team team : response.getData()) {
                        Log.i("MyAmplifyApp get team", team.getName());
                        allTeams.add(team);
                    }
                    for (int i=0;i<allTeams.size();i++){
                        System.out.println("test loop xx"+i);
                        System.out.println(allTeams.get(i).getName());
                        System.out.println("test cond" + taskTeam + "::team");

                        System.out.println(allTeams.get(i).getName()==taskTeam);
                        if (allTeams.get(i).getName().equals(taskTeam)){
                            allTasks.addAll(allTeams.get(i).getTasks());
                            Todo task=allTasks.get(0);
                            System.out.println("test task.....+ "+task.getTaskTitle());
                            break;
                        }
                    }
                    handler.sendEmptyMessage(1);
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );

//        Toast.makeText(getApplicationContext(),"onResume..!",Toast.LENGTH_LONG).show();

        // Hardcode Task 1
//        Button dCC=findViewById(R.id.DoCCButton);
//        String task1=dCC.getText().toString();
//        dCC.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent goToTaskDetail=new Intent(MainActivity.this,TaskDetail.class);
//                goToTaskDetail.putExtra("task",task1);
//                startActivity(goToTaskDetail);
//            }
//        });
        // Task 2
//        Button addReadme=findViewById(R.id.AddReadmeButton);
//        String task2=addReadme.getText().toString();
//        addReadme.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent goToTaskDetail=new Intent(MainActivity.this,TaskDetail.class);
//                goToTaskDetail.putExtra("task",task2);
//                startActivity(goToTaskDetail);
//            }
//        });
        // Task 3
//        Button sleep=findViewById(R.id.SleepButton);
//        String task3=sleep.getText().toString();
//        sleep.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent goToTaskDetail=new Intent(MainActivity.this,TaskDetail.class);
//                goToTaskDetail.putExtra("task",task3);
//                startActivity(goToTaskDetail);
//            }
//        });

        // SettingsButton
        Button SettingButton=findViewById(R.id.SettingsButton);
        SettingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToSettingPage=new Intent(MainActivity.this,Settings.class);
                startActivity(goToSettingPage);
            }
        });
        String msg=" ???s tasks";
        //get UserName
        SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String userName=sharedPreferences.getString("userName","user");

        TextView userTasksView=findViewById(R.id.userTasksView);
        userTasksView.setText(com.amazonaws.mobile.client.AWSMobileClient.getInstance().getUsername()+msg);


//        System.out.println(Amplify.Auth.getCurrentUser().getUsername() +"tesssssssssssssst user name from congito");
//        if (Amplify.Auth.getCurrentUser().getUsername()==null){
//            userTasksView.setText(userName+msg);
//        }else {
//            userTasksView.setText(Amplify.Auth.getCurrentUser().getUsername()+msg);
//        }



//        Amplify.Auth.fetchAuthSession(
//                result ->{ Log.i("AmplifyQuickstart", result.toString());
//                if ("true".equals(result.toString())){
//                    userTasksView.setText(Amplify.Auth.getCurrentUser().getUsername()+msg);
//                }else {
//                    userTasksView.setText("Guest "+msg);
//                }
//                },
//                error -> Log.e("AmplifyQuickstart", error.toString())
//        );

        //logout Button
        Button logoutButton=findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(view -> {
            Amplify.Auth.signOut(
                    () ->{ Log.i("AuthQuickstart", "Signed out successfully");
                        Amplify.Auth.signInWithWebUI(
                                this,
                                result -> {Log.i("AuthQuickStart", result.toString());
                                    finish();
                                    startActivity(getIntent());
                                }
                                ,
                                error -> Log.e("AuthQuickStart", error.toString())
                        );

                    }
                    ,
                    error -> Log.e("AuthQuickstart", error.toString())
            );

//            Intent goToLogin=new Intent(this, HostedUIRedirectActivity.class);
//            startActivity(goToLogin);
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
//        Toast.makeText(getApplicationContext(),"onPause..!",Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onStop() {
        super.onStop();
//        Toast.makeText(getApplicationContext(),"onStop..!",Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
//        Toast.makeText(getApplicationContext(),"onRestart..!",Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(),"onDestroy..!",Toast.LENGTH_LONG).show();

    }
}