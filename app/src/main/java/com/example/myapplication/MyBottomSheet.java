package com.example.myapplication;

import static com.example.myapplication.MainActivity5.dateArray;
import static com.example.myapplication.MainActivity5.noteArray;
import static com.example.myapplication.MainActivity5.titleArray;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.io.Serializable;
import java.util.Map;

public class MyBottomSheet extends BottomSheetDialogFragment {

    private Button insertButton;
    private ProgressBar progressBar;
    EditText titleInput;
    EditText noteInput;
    EditText dateInput;

    String title;
    String note;
    String date;
    String email;

    Map<String, String> user;

    public void showAlertDialog(String message) {
        new AlertDialog.Builder(getContext())
                .setTitle("Input Error")
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }

    void insertData(String title, String note, String day) {
        try {
            user = MainActivity5.user;
            email = user.get("email");
            DataBaseManager dbManager = new DataBaseManager(getContext());
            dbManager.insertInformation(title, note, day, email);
        } catch (Exception error) {
            showAlertDialog("Data wasn't created");
        }
    }

    private void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void startDelayedActivity() {
        // Use a handler to delay the start of the activity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Restart MainActivity5
                Intent intent = new Intent(getActivity(), MainActivity5.class);
                intent.putExtra("user", (Serializable) user);
                startActivity(intent);
                getActivity().finish();
            }
        }, 2000);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_bottom_sheet, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Adjust BottomSheet height to match parent
        View parentView = (View) view.getParent();
        if (parentView != null) {
            parentView.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
        }

        // Initialize UI components
        insertButton = view.findViewById(R.id.create);
        titleInput = view.findViewById(R.id.tittleEntry);
        noteInput = view.findViewById(R.id.noteEntry);
        dateInput = view.findViewById(R.id.dateEntry);
        progressBar = view.findViewById(R.id.progressBar);

        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgressBar();
                insertButton.setEnabled(false);

                title = titleInput.getText().toString();
                note = noteInput.getText().toString();
                date = dateInput.getText().toString();

                insertData(title, note, date);
                startDelayedActivity();
            }
        });


    }
}
