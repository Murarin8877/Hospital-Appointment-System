package com.example.reservationsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CheckRegistered extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference datebase;
    MyAdapter myAdapter;
    ArrayList<UserRegistered> list;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();


        setContentView(R.layout.activity_check_registered);






        recyclerView = findViewById(R.id.userregisteredlist);

        datebase = FirebaseDatabase.getInstance().getReference();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        myAdapter = new MyAdapter(this,list);
        recyclerView.setAdapter(myAdapter);


        datebase.child("registered").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    Intent it = getIntent();
                    String intent_id = it.getStringExtra("ID");
                    //Toast.makeText(CheckRegistered.this,dataSnapshot.getKey(),Toast.LENGTH_SHORT).show();
                    String pushkey = dataSnapshot.getKey().substring(0,10);
                    if (intent_id.equals(pushkey))
                    {
                        UserRegistered userRegistered = dataSnapshot.getValue(UserRegistered.class);
                        list.add(userRegistered);
                    }

                }
                myAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


    }
    public void goback(View v)
    {
        finish();
    }
}