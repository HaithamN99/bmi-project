package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class food_list extends AppCompatActivity {

    private RecyclerView recyclv;
    private foodListAdapter foodAdapter;
    private ArrayList<foodInfo> food_info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);
        recyclv=findViewById(R.id.recycle_food_list);
        food_info = new ArrayList<>();

        for (int i =0; i<10;i++){

            food_info.add(new foodInfo("foodName","food catagory",i,""));

        }
        foodAdapter = new foodListAdapter(this,food_info);

        recyclv.setLayoutManager(new LinearLayoutManager(this));

        recyclv.setAdapter(foodAdapter);

    }
}