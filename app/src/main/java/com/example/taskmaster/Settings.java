package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Button saveButton=findViewById(R.id.SettingsSaveButton);
        saveButton.setOnClickListener((view -> {
            SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(Settings.this);
            SharedPreferences.Editor sharedPreferencesEditor=sharedPreferences.edit();
            EditText userNameFiled=findViewById(R.id.SettingsUserName);
            String userName=userNameFiled.getText().toString();
            sharedPreferencesEditor.putString("userName",userName);
            sharedPreferencesEditor.apply();
            Toast.makeText(getApplicationContext(),"Saved",Toast.LENGTH_LONG).show();
//            Intent intent=new Intent(Settings.this,MainActivity.class);
//            startActivity(intent);
        }));
    }
}