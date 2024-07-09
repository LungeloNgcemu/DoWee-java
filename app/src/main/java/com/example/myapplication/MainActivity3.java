package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity3 extends AppCompatActivity {

    private Button login;

    private EditText emailInput;
    private EditText passwordInput;

    String email;
    String password;

    String dataEmail;
    String dataPassword;
    String dataName;

    boolean isAuthenticated = false;



    private Map<String, String> user = new HashMap<>();


    public void showAlertDialog(String message) {
        new AlertDialog.Builder(MainActivity3.this)
                .setTitle("Input Error")
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }


    //Register User
    private void loginUser(String email, String password,  List<Map<String, String>> users) {

        if (email.isEmpty() || password.isEmpty()) {

            //Allert message
            showAlertDialog("Please fill in all fields.");


        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            // Show an alert dialog if email is not valid
            showAlertDialog("Please enter a valid email address.");

        } else {




            for (int i = 0; i < users.size(); i++) {

                Map<String, String> mapItem = users.get(i);

                dataEmail = mapItem.get("email");
                dataPassword = mapItem.get("password");

                System.out.println("DataEmail: " + dataEmail);
                System.out.println("DataEmail: " + dataPassword);

                if (email.equals(dataEmail) && password.equals(dataPassword)) {

                    dataName = mapItem.get("name");

                    user.put("name", dataName);
                    user.put("email", dataEmail);
                    //Push the data user to next page


                    isAuthenticated = true;
                    break;
                    //end loop
                }


            }


            //Next page
            if (isAuthenticated) {
                Intent myIntent = new Intent(MainActivity3.this, MainActivity5.class);

                myIntent.putExtra("user", (Serializable) user);

                MainActivity3.this.startActivity(myIntent);
                finish();

            }else{

                showAlertDialog("Sorry, Wrong login details");

            }
        }

    }


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        List<Map<String, String>> users = (List<Map<String, String>>) getIntent().getSerializableExtra("users");

        System.out.println("users: " + users);


        emailInput = findViewById(R.id.loginEmail);
        passwordInput = findViewById(R.id.loginPassword);

        login = findViewById(R.id.loginButton);


        login.setOnClickListener(view -> {


            email = emailInput.getText().toString();
            password = passwordInput.getText().toString();



            loginUser(email, password, users);

            System.out.println("Email: " + email);
            System.out.println("Email: " + password);


        });


    }


}