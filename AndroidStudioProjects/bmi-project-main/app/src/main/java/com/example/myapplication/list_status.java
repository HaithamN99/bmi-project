package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.android.saver;
import com.example.myapplication.android.user;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class list_status extends AppCompatActivity {
    ListView status_list;
    TextView rate_status_tv, logout;
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
        firestore = FirebaseFirestore.getInstance();
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getBaseContext(), MainActivity2.class));
                finish();
            }
        });


        sAdapter = new statusAdapter(this, R.layout.custom_status_layout);
        //    rate_status_tv.setText(bmi_val+"");
        firestore.collection("user").whereEqualTo("username", saver.User.getUsername()).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                QuerySnapshot querySnapshot = task.getResult();
                if (querySnapshot != null) {
                    saver.User = querySnapshot.toObjects(user.class).get(0);
                    sAdapter.setS(saver.User.getS());
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
                int age_newRecord = age;
                intent.putExtra("age", age_newRecord);
                startActivityForResult(intent, REQ_COD_NEW_RECORD);
            }
        });
        add_food_bt.setOnClickListener(v -> {
            Intent intent = new Intent(getBaseContext(), addFood.class);
            startActivity(intent);
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_COD_NEW_RECORD && resultCode == RESULT_OK) {

            Status s1 = (Status) data.getSerializableExtra(NewRecodr.newRecord);

            sAdapter.addItem(s1);
            sAdapter.notifyDataSetChanged();

        }
    }
}