package com.example.myapplication;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataBaseManager {
    private DataBase dbHelper;

    public DataBaseManager(Context context) {
        dbHelper = new DataBase(context);
    }

    // Method to insert a user into the database
    public void insertUser(String name, String email, String password) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("email", email);
        values.put("password", password);

        db.insert("users", null, values);
        db.close();
    }

    public void insertInformation(String title, String note, String date, String email) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("note", note);
        values.put("date", date);
        values.put("email", email);

        db.insert("info", null, values);
        db.close();
    }

    // Method to get all users from the database
    @SuppressLint("Range")
    public List<Map<String, String>> getAllUsers() {
        List<Map<String, String>> userList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("users", null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            Map<String, String> userMap = new HashMap<>();
            userMap.put("id", Integer.toString(cursor.getInt(cursor.getColumnIndex("id"))));
            userMap.put("name", cursor.getString(cursor.getColumnIndex("name")));
            userMap.put("email", cursor.getString(cursor.getColumnIndex("email")));
            userMap.put("password", cursor.getString(cursor.getColumnIndex("password")));
            userList.add(userMap);
        }

        cursor.close();
        db.close();
        return userList;
    }
    @SuppressLint("Range")
    public List<Map<String, String>> getAllInformation() {
        List<Map<String, String>> infoList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("info", null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            Map<String, String> infoMap = new HashMap<>();
            infoMap.put("id", Integer.toString(cursor.getInt(cursor.getColumnIndex("id"))));
            infoMap.put("title", cursor.getString(cursor.getColumnIndex("title")));
            infoMap.put("note", cursor.getString(cursor.getColumnIndex("note")));
            infoMap.put("date", cursor.getString(cursor.getColumnIndex("date")));
            infoMap.put("email", cursor.getString(cursor.getColumnIndex("email")));
            infoList.add(infoMap);
        }

        cursor.close();
        db.close();
        return infoList;
    }


    // Method to delete a user by ID
    public void deleteUserById(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("users", "id = ?", new String[]{String.valueOf(id)});
        db.close();
    }


    // Method to delete information by ID
    public void deleteInfoById(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("info", "id = ?", new String[]{String.valueOf(id)});
        db.close();


    }

}