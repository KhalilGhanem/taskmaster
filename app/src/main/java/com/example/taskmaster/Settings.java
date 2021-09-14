package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        RadioButton setTeam1=findViewById(R.id.settingTeam1RadioButton);
        RadioButton setTeam2=findViewById(R.id.settingTeam2RadioButton);
        RadioButton setTeam3=findViewById(R.id.settingTeam3RadioButton);
        Button saveButton=findViewById(R.id.SettingsSaveButton);
        saveButton.setOnClickListener((view -> {
            SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(Settings.this);
            SharedPreferences.Editor sharedPreferencesEditor=sharedPreferences.edit();
            EditText userNameFiled=findViewById(R.id.SettingsUserName);
            String userName=userNameFiled.getText().toString();
            sharedPreferencesEditor.putString("userName",userName);
            String team="";
            if (setTeam1.isChecked()){
                team="team1";
            }else if (setTeam2.isChecked()){
                team="team2";
            }else if (setTeam3.isChecked()){
                team="team3";
            }
            sharedPreferencesEditor.putString("teamName",team);
            sharedPreferencesEditor.apply();
            Toast.makeText(getApplicationContext(),"Saved",Toast.LENGTH_LONG).show();
//            Intent intent=new Intent(Settings.this,MainActivity.class);
//            startActivity(intent);
        }));
    }
}