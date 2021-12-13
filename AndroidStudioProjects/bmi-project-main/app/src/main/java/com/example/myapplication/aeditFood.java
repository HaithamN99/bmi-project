package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.Nullable;

public class aeditFood extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        Spinner edit_catagory_sp;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_food);

        edit_catagory_sp=findViewById(R.id.edit_food_catagory_sp);
        ArrayAdapter<String> catagory_adapter = new ArrayAdapter<String>(aeditFood.this, android.R.layout.simple_expandable_list_item_1,getResources().getStringArray(R.array.catagory));
        catagory_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        edit_catagory_sp.setAdapter(catagory_adapter);

    }
}
