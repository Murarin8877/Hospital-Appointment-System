package com.example.reservationsystem;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://reservation-system-d7712-default-rtdb.firebaseio.com/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        final EditText id = findViewById(R.id.id_login);
        final EditText password = findViewById(R.id.password_login);
        final Button loginBtn = findViewById(R.id.btn_login);
        final TextView registerNowBtn = findViewById(R.id.resigterNowBtn);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String idTxt = id.getText().toString();
                final String passwordTxt = password.getText().toString();
                if(idTxt.isEmpty() || passwordTxt.isEmpty())
                {
                    Toast.makeText(login.this, "請輸入身分證或密碼", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @RequiresApi(api = Build.VERSION_CODES.N)
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(idTxt))
                            {
                                final String getPassword= snapshot.child(idTxt).child("password").getValue(String.class);
                                if(getPassword.equals(passwordTxt))
                                {

                                    Toast.makeText(login.this,"登入成功",Toast.LENGTH_SHORT).show();
                                    Intent it =new Intent(login.this,MainActivity.class);
                                    it.putExtra("ID",idTxt);
                                    startActivity(it);
                                    finish();
                                }
                                else
                                {
                                    Toast.makeText(login.this,"密碼錯誤",Toast.LENGTH_SHORT).show();
                                }


                            }
                            else
                            {
                                Toast.makeText(login.this,"找不到此密碼或帳號",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }

            }
        });


        registerNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login.this,signup.class));
            }
        });


    }//protect的


}