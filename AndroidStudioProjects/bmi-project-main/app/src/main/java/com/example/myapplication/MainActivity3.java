package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity3 extends AppCompatActivity {
Button b2;
TextView logIn , username,pass,name;
ProgressBar progressBar_regester;
    FirebaseAuth fauth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        logIn =findViewById(R.id.Login_tv_ac2);
    b2 =findViewById(R.id.b_creatacc);
    fauth =FirebaseAuth.getInstance();
    username=findViewById(R.id.username_regester);
        pass=findViewById(R.id.password_regester);
        name=findViewById(R.id.name_regester);
        progressBar_regester=findViewById(R.id.progressBar_regester);
        if (fauth.getCurrentUser() != null){

            finish();

        }
        logIn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            startActivity(  new Intent(getBaseContext(),MainActivity2.class));
        }
    });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = username.getText().toString();
                String password = pass.getText().toString();

                if (TextUtils.isEmpty(email)){

                    username.setError("Email is Regesterd");
                    return;

                }
                if (TextUtils.isEmpty(password)){

                    pass.setError("Email is Requierd");
                    return;

                }

                if (password.length() < 6){

                    pass.setError("password must be at least 6 characters");
                return;
                }
                progressBar_regester.setVisibility(View.VISIBLE);
                fauth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            Toast.makeText(MainActivity3.this, "user created", Toast.LENGTH_SHORT).show();

                           startActivity(new Intent(getBaseContext(),MainActivity4.class));

                        }
                  
                   else{

                            Toast.makeText(MainActivity3.this, "user created"+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                  
                    }
                   
                });



            }
        });

    }
}