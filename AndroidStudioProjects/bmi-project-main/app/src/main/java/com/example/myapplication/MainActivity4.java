package com.example.myapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.android.saver;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;

public class MainActivity4 extends AppCompatActivity {


    public double bmi_calc(int kg, int cm, int age) {

        return (kg / Math.pow((cm / 100), 2)) * age;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    static public int getAge(int year, int month, int dayOfMonth) {
        return Period.between(
                LocalDate.of(year, month, dayOfMonth),
                LocalDate.now()
        ).getYears();
    }

    FirebaseFirestore f;
    Button b2_kginc, b1_kgdec;
    Button b2_cminc, b1_cmdec;
    Button save_data;
    TextView tv_kg, tv_cm;
    int kg=60, cm=100, age;
    DatePickerDialog datepicker;
    EditText eText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        b1_kgdec = findViewById(R.id.b_kgdec);
        b2_kginc = findViewById(R.id.b2_kginc);
        b1_cmdec = findViewById(R.id.b1_cmdec);
        b2_cminc = findViewById(R.id.b2_cminc);
        tv_kg = findViewById(R.id.tv_kg);
        tv_cm = findViewById(R.id.tv_cm);
        save_data = findViewById(R.id.save_data);
        eText = findViewById(R.id.editText1);
        eText.setInputType(InputType.TYPE_NULL);
        f = FirebaseFirestore.getInstance();


        eText.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                datepicker = new DatePickerDialog(MainActivity4.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                eText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                age = getAge(year, monthOfYear, dayOfMonth);
                            }
                        }, year, month, day);

                datepicker.show();
            }
        });

        b1_kgdec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kg = kg - 1;
                tv_kg.setText(kg + "");
            }
        });
        b2_kginc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kg = kg + 1;
                tv_kg.setText(kg + "");
            }
        });
        b1_cmdec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cm = cm - 1;
                tv_cm.setText(cm + "");
            }
        });
        b2_cminc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cm = cm + 1;
                tv_cm.setText(cm + "");
            }
        });
        save_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              int a=age;
              int k=kg;
              int c=cm;
                saver.User.setAge(a);
                saver.User.setBmi(bmi_calc(k, c, a));
//                List<foodInfo> fI = new ArrayList<>();
//                fI.add(new foodInfo("test1", "test", 1, "image"));
//                fI.add(new foodInfo("test2", "test", 2, "image"));
//                fI.add(new foodInfo("test3", "test", 3, "image"));
//                fI.add(new foodInfo("test4", "test", 4, "image"));
//                saver.User.setF(fI);
//
//                List<Status> st = new ArrayList<>();
//                st.add(new Status("1205/21/20165", 1, "5.4", 15));
//                st.add(new Status("1205/21/20165", 2, "5.4", 15));
//                st.add(new Status("1205/21/20165", 3, "5.4", 15));
//                st.add(new Status("1205/21/20165", 4, "5.4", 15));
//                saver.User.setS(st);
                f.collection("user").document(saver.Id).set(saver.User).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(getBaseContext(), "The data is saved", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getBaseContext(), list_status.class);
                            startActivity(intent);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getBaseContext(), "no Internet connection", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });
    }
}