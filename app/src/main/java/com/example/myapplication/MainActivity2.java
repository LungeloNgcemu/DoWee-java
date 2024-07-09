package com.example.myapplication;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity {


    private Button login;
    private Button register;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        List<Map<String, String>> users = (List<Map<String, String>>) getIntent().getSerializableExtra("users");

        System.out.println("from new : " +users);


        login = findViewById(R.id.login);
        register = findViewById(R.id.register);


        login.setOnClickListener( view -> {

            Intent myIntent = new Intent(MainActivity2.this, MainActivity3.class);
            myIntent.putExtra("users", (Serializable) users);
            MainActivity2.this.startActivity(myIntent);
            finish();

        });


        register.setOnClickListener( view -> {

            Intent myIntent = new Intent(MainActivity2.this, MainActivity4.class);
            myIntent.putExtra("users", (Serializable) users);
            MainActivity2.this.startActivity(myIntent);
            finish();


        });



    }
}