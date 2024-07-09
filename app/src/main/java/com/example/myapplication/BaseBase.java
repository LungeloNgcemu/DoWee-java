package com.example.myapplication;

import android.content.Intent;
import android.os.Handler;
import android.widget.BaseAdapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class BaseBase extends BaseAdapter {


    Context context;
    String listTitle[];
    String listNote[];
    String listDate[];
    String listId[];


    private List<String> list;


    LayoutInflater inflater;



    public  BaseBase(Context context, List<String> title, List<String> note, List<String> date, List<String> id){

        this.context = context;
        this.listDate = date.toArray(new String[0]);
        this.listNote = note.toArray(new String[0]);
        this.listTitle = title.toArray(new String[0]);
        this.listId = id.toArray(new String[0]);

        inflater = LayoutInflater.from(context);

    }


    @Override
    public int getCount() {
        return listDate.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = inflater.inflate(R.layout.activity_custom_card,null);
        TextView titles =(TextView) convertView.findViewById(R.id.tittle);
        TextView dates =(TextView) convertView.findViewById(R.id.date);
        TextView notes =(TextView) convertView.findViewById(R.id.note);
        ImageButton deleteButton = convertView.findViewById(R.id.deleteButton);

        titles.setText(listTitle[position]);
        notes.setText(listNote[position]);
        dates.setText(listDate[position]);




        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DataBaseManager dbManager = new DataBaseManager(context);
                // Get the ID of the item to delete
                String idToDelete = listId[position];
                // Call the deleteById method in your adapter
                deleteById(idToDelete);
                dbManager.deleteInfoById(Integer.parseInt(idToDelete));
                Toast.makeText(context, "Item deleted", Toast.LENGTH_SHORT).show();

            }
        });


        return convertView;
    }


    // Method to delete an item by ID
    public void deleteById(String id) {
        int index = -1;
        for (int i = 0; i < listId.length; i++) {
            if (listId[i].equals(id)) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            listId = removeElement(listId, index);
            listTitle = removeElement(listTitle, index);
            listNote = removeElement(listNote, index);
            listDate = removeElement(listDate, index);
            notifyDataSetChanged();
        }
    }

    // Helper method to remove an element from an array
    private String[] removeElement(String[] array, int index) {
        if (array == null || index < 0 || index >= array.length) {
            return array;
        }
        String[] newArray = new String[array.length - 1];
        for (int i = 0, k = 0; i < array.length; i++) {
            if (i == index) {
                continue;
            }
            newArray[k++] = array[i];
        }
        return newArray;
    }


}



