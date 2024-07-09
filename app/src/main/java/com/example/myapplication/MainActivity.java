package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    public List<Map<String, String>> users = new ArrayList<>();




    void usersData() {
        //Login
        Intent myIntent = new Intent(MainActivity.this, MainActivity2.class);
        myIntent.putExtra("users", (Serializable) users);
        MainActivity.this.startActivity(myIntent);
        finish();
    }


    void realData(){

        DataBaseManager dbManager = new DataBaseManager(this);

        List<Map<String, String>> usersData =  dbManager.getAllUsers();

        for (Map<String, String> user : users) {
            System.out.println("ID: " + user.get("id"));
            System.out.println("Name: " + user.get("name"));
            System.out.println("Email: " + user.get("email"));
            System.out.println("Password: " + user.get("password"));
        }

        users = usersData;

    }



    public void startDelayedActivity() {
        // Use a handler to delay the start of the activity
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                realData();

                System.out.println(users);

                usersData();
            }
        }, 3000); // 3000 milliseconds = 3 seconds
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startDelayedActivity();


    }
}