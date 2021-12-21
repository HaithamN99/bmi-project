package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.android.saver;
import com.example.myapplication.android.user;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class list_status extends AppCompatActivity {
    ListView status_list;
    TextView rate_status_tv, logout, tv_hi;
    Status sta;
    int age;
    int a, b;
    double bmi_val;
    private FirebaseFirestore firestore;

    /* static double bmi(int  len,int wei,int age){

          double bmi_calculate ;
          int a,b;
          a=len*len;
          b=a/100;
          bmi_calculate=(wei/b)*age;
          return bmi_calculate;
      }*/

    final int REQ_COD_NEW_RECORD = 1;
    Button add_record_bt, add_food_bt, viewFood_bt;
    statusAdapter sAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_status);
        status_list = findViewById(R.id.status_list);
        add_record_bt = findViewById(R.id.add_record_bt);
        rate_status_tv = findViewById(R.id.statusRate_tv);
        viewFood_bt = findViewById(R.id.viewFood_bt);
        add_food_bt = findViewById(R.id.status__list_add_food_bt);
        logout = findViewById(R.id.logout);
        tv_hi = findViewById(R.id.tv_hi);
        firestore = FirebaseFirestore.getInstance();
        logout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            saver.User = new user();
            startActivity(new Intent(getBaseContext(), MainActivity2.class));
            finish();
        });


        sAdapter = new statusAdapter(this, R.layout.custom_status_layout);
        firestore.collection("user").whereEqualTo("username", saver.User.getUsername()).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                QuerySnapshot querySnapshot = task.getResult();
                if (querySnapshot != null) {
                    saver.User = querySnapshot.toObjects(user.class).get(0);
                    sAdapter.setS(saver.User.getS());
                    tv_hi.setText("Hi, " + saver.User.getName());
                    rate_status_tv.setText(saver.User.getBmi() + "");
                }
            }
        });
        // bmi_c= sta.bmi(sta.getLength(),sta.getWeight(),age);
        status_list.setAdapter(sAdapter);
        //    bmi_c=bmi(a,b,age);


        viewFood_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), food_list.class);
                startActivity(intent);
            }
        });
        add_record_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), NewRecodr.class);
                startActivity(intent);
            }
        });
        add_food_bt.setOnClickListener(v -> {
            Intent intent = new Intent(getBaseContext(), addFood.class);
            startActivity(intent);
        });

    }



    @Override
    protected void onStart() {
        super.onStart();
        sAdapter.setS(saver.User.getS() != null ? saver.User.getS() : new ArrayList<>());
        if (saver.User.getName() != null) {
            tv_hi.setText("Hi, " + saver.User.getName());
            rate_status_tv.setText(saver.User.getBmi() + "");
        }
    }

}