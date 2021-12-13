package com.example.myapplication;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class addFood extends AppCompatActivity {
    Spinner catagory_sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        catagory_sp=findViewById(R.id.add_food_catagory_sp);
        ArrayAdapter<String> catagory_adapter = new ArrayAdapter<String>(addFood.this, android.R.layout.simple_expandable_list_item_1,getResources().getStringArray(R.array.catagory));
        catagory_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        catagory_sp.setAdapter(catagory_adapter);

    }
}