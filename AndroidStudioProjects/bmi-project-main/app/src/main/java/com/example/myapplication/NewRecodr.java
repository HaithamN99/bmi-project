package com.example.myapplication;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NewRecodr extends AppCompatActivity {
    @RequiresApi(api = Build.VERSION_CODES.O)
        static double bmi(int kgw,int m,int age){
        double a,b,c;
        a=m/100;
        b=a*a;
        c=(kgw/m)*age;
        return c;
    }
        Button b;
       static final String newRecord="newRecord";
        EditText date_text,time_text;
        String date = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(new Date());
                String time;
        TextView cm_plus,cm_minus,kg_plus,kg_minus,kg_tv,cm_tv;
        int kg=60 ,cm=100;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_recodr);

         b=findViewById(R.id.saveDate_new_record);
        cm_plus=findViewById(R.id.new_record_cm_plus_tv);
        cm_minus=findViewById(R.id.new_record_cm_minus_tv);
        kg_plus=findViewById(R.id.new_record_kg_plus_tv);
        kg_minus=findViewById(R.id.new_record_kg_minus_tv);
        kg_tv=findViewById(R.id.new_record_kg_tv);
        cm_tv=findViewById(R.id.new_record_cm_tv);
        date_text=findViewById(R.id.new_record_date_et);
        time_text=findViewById(R.id.new_record_time_et);
        date = date_text.getText().toString();

        time = time_text.getText().toString();
        Intent i =getIntent();
        int age_p=i.getIntExtra("age",15);
        kg_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (kg==0){

                    kg=0;
                }
               else{
                kg=kg-1;
                kg_tv.setText(kg+"");
            }}
        });
        kg_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kg=kg+1;
                kg_tv.setText(kg+"");
            }
        });
        cm_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cm==0){

                    cm=0;
                }
                else {
                cm=cm-1;
                cm_tv.setText(cm+"");
            }}
        });
        cm_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cm=cm+1;
                cm_tv.setText(cm+"");
            }
        });




        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status s = new status(date_text.getText().toString(),kg,time_text.getText().toString(),cm);
                double bmi_value=bmi(kg,cm,age_p);
                Intent intent = new Intent();
                intent.putExtra(newRecord,s);
                intent.putExtra("bmi",bmi_value);
                setResult(RESULT_OK,intent);
                finish();
            }
        });


    }
}