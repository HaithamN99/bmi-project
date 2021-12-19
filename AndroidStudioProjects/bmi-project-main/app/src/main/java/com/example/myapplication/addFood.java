package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class addFood extends AppCompatActivity {
    Spinner catagory_sp;
    EditText food_name,Food_cal;
    String cal,foodName;
    Button addFood_save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        catagory_sp=findViewById(R.id.add_food_catagory_sp);
        ArrayAdapter<String> catagory_adapter = new ArrayAdapter<String>(addFood.this, android.R.layout.simple_expandable_list_item_1,getResources().getStringArray(R.array.catagory));
        catagory_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        catagory_sp.setAdapter(catagory_adapter);

        food_name=findViewById(R.id.add_food_foodname_et);
        Food_cal=findViewById(R.id.add_food_foodCal_et);
        addFood_save=findViewById(R.id.addFood_save);

        foodName=food_name.getText().toString();
        cal=Food_cal.getText().toString();

        addFood_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(),list_status.class);

            }
        });

    }
}