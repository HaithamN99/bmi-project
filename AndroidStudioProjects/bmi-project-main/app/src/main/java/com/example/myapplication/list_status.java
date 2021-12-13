package com.example.myapplication;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class list_status extends AppCompatActivity {
            ListView status_list;
            TextView rate_status_tv;
            status sta;
             int age;
             int a,b;
             double bmi_val;

    static double bmi(int  len,int wei,int age){

        double bmi_calculate ;
        int a,b;
        a=len*len;
        b=a/100;
        bmi_calculate=(wei/b)*age;
        return bmi_calculate;
    }
    ArrayList<status> s = new ArrayList<status>();

          final int REQ_COD_NEW_RECORD =1;
            Button add_record_bt , add_food_bt ,viewFood_bt;
    statusAdapter sAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_status);
        status_list =findViewById(R.id.status_list);
        add_record_bt = findViewById(R.id.add_record_bt);
        rate_status_tv = findViewById(R.id.statusRate_tv);
        viewFood_bt=findViewById(R.id.viewFood_bt);
        add_food_bt=findViewById(R.id.status__list_add_food_bt);

        Intent intent_age = getIntent();
       age = intent_age.getIntExtra("SEND_VALUE_NAME",0);
       Intent bmi_v=getIntent();
        bmi_val=   bmi_v.getDoubleExtra("bmi",1);
       rate_status_tv.setText(bmi_val+"");

         sAdapter  = new statusAdapter(this,R.layout.custom_status_layout,s);
        // bmi_c= sta.bmi(sta.getLength(),sta.getWeight(),age);
        status_list.setAdapter(sAdapter);
    //    bmi_c=bmi(a,b,age);


        viewFood_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getBaseContext(),food_list.class);
                startActivity(intent);
            }
        });
        add_record_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getBaseContext(),NewRecodr.class);
                int age_newRecord=age;
                intent.putExtra("age",age_newRecord);
                startActivityForResult(intent,REQ_COD_NEW_RECORD);
            }
        });
        add_food_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getBaseContext(),addFood.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_COD_NEW_RECORD && resultCode == RESULT_OK) {

           status s1 = (status) data.getSerializableExtra(NewRecodr.newRecord);

            sAdapter.addItem(s1);
             sAdapter.notifyDataSetChanged();

        }
    }
}