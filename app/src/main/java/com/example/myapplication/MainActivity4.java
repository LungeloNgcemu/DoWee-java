package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity4 extends AppCompatActivity {


    private Button register;

    private EditText nameInput;
    private EditText emailInput;
    private EditText passwordInput;

    private String name;
    private String email;
    private String password;

    private Map<String, String> user = new HashMap<>();


    private void showAlertDialog(String message) {
        new AlertDialog.Builder(MainActivity4.this)
                .setTitle("Input Error")
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }


    //Register User
    private void registerUser(String name, String email, String password) {

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {

            //Allert message
            showAlertDialog("Please fill in all fields.");


        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            // Show an alert dialog if email is not valid
            showAlertDialog("Please enter a valid email address.");

        } else {

            try {
                List<Map<String, String>> users = (List<Map<String, String>>) getIntent().getSerializableExtra("users");

                DataBaseManager dbManager = new DataBaseManager(this);

                dbManager.insertUser(name, email, password);

                //Next page

                Intent myIntent = new Intent(MainActivity4.this, MainActivity5.class);

                user.put("name", name);
                user.put("email", email);

                myIntent.putExtra("user", (Serializable) user);

                //Only push one user

                MainActivity4.this.startActivity(myIntent);
                finish();

            } catch (Exception error) {

                showAlertDialog("OOPS : " + error.getMessage());
            }

        }

    }


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);


        register = findViewById(R.id.registerButton);

        nameInput = findViewById(R.id.registerName);
        emailInput = findViewById(R.id.registerEmail);
        passwordInput = findViewById(R.id.registerPassword);





        register.setOnClickListener(view -> {

            name = nameInput.getText().toString();
            email = emailInput.getText().toString();
            password = passwordInput.getText().toString();

            //If statments

            System.out.println(""+name+""+email+""+password);

           registerUser(name, email, password);


        });


    }
}