package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MainActivity5 extends AppCompatActivity {


    ListView listView;
    FloatingActionButton button;
    BaseBase customBaseAdapter;


    ImageButton exitButton;


    public static List<String> titleArray = new ArrayList<>(Arrays.asList(


    ));
    public static List<String> noteArray = new ArrayList<>(Arrays.asList(

    ));

    public static List<String> dateArray = new ArrayList<>(Arrays.asList(

    ));
    public static List<String> idArray = new ArrayList<>(Arrays.asList(

    ));

    public static Map<String, String> user;

    public void showAlertDialog(String message) {
        new AlertDialog.Builder(MainActivity5.this)
                .setTitle("Input Error")
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }


    public static void getData(Context context, Map<String, String> user) {
        String userEmail = user.get("email");
        DataBaseManager dbManager = new DataBaseManager(context); // Use MainActivity5.this as context

        List<Map<String, String>> infoData = dbManager.getAllInformation();

        // Clear arrays before populating them
        titleArray.clear();
        noteArray.clear();
        dateArray.clear();
        idArray.clear();

        for (int i = 0; i < infoData.size(); i++) {
            String emailData = infoData.get(i).get("email");

            if (userEmail.equals(emailData)) {
                String t = infoData.get(i).get("title");
                String n = infoData.get(i).get("note");
                String d = infoData.get(i).get("date");
                String id = infoData.get(i).get("id");

                titleArray.add(t);
                noteArray.add(n);
                dateArray.add(d);
                idArray.add(id);
            }
        }
    }

    public void exit() {

        Intent intent = new Intent(MainActivity5.this, MainActivity.class);
        startActivity(intent);
        finish();

    }


    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        user = (Map<String, String>) getIntent().getSerializableExtra("user");


        assert user != null;
        getData(this, user);


        listView = findViewById(R.id.listCount);
        button = findViewById(R.id.floatingButton);
        exitButton = findViewById(R.id.logoutButton);


        customBaseAdapter = new BaseBase(getApplicationContext(), titleArray, noteArray, dateArray,idArray);

        listView.setAdapter(customBaseAdapter);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MyBottomSheet bottomSheet = new MyBottomSheet();


                bottomSheet.show(getSupportFragmentManager(), bottomSheet.getTag());


            }
        });

        exitButton.setOnClickListener( view ->{

            exit();

        });



    }
}