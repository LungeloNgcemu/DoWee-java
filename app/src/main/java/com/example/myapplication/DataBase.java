package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "doWeeData";
    private static final int DATABASE_VERSION = 1;

    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE1 = "CREATE TABLE users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "email TEXT," +
                "password TEXT)";

        db.execSQL(CREATE_TABLE1);


        String CREATE_TABLE2 = "CREATE TABLE info (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "title TEXT," +
                "note TEXT," +
                "date TEXT," +
                "email TEXT)";

        db.execSQL(CREATE_TABLE2);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if(oldVersion < 1){

            String CREATE_TABLE1 = "CREATE TABLE users (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT," +
                    "email TEXT," +
                    "password TEXT)";

            db.execSQL(CREATE_TABLE1);


            String CREATE_TABLE2 = "CREATE TABLE info (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "title TEXT," +
                    "note TEXT," +
                    "date TEXT," +
                    "email TEXT)";

            db.execSQL(CREATE_TABLE2);

        }


    }
}
