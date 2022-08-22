package com.example.reservationsystem;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

@RequiresApi(api = Build.VERSION_CODES.N)
public class MainActivity extends AppCompatActivity  {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        TextView login_id = findViewById(R.id.login_id);
        Intent it = getIntent();
        String id = it.getStringExtra("ID");
        login_id.setText("身分證為:"+id);



    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
    public void gor_egistered(View v)
    {
        Intent it = getIntent();
        String id = it.getStringExtra("ID");
        Intent it2 = new Intent(this,registered.class);
        it2.putExtra("ID",id);
        startActivity(it2);
    }
    public  void signout(View v){startActivity(new Intent(this,login.class));}
    public void go_checkregistered(View v){
        Intent it = getIntent();
        String id1 = it.getStringExtra("ID");

        Intent it9 = new Intent(this,CheckRegistered.class);
        String idid = id1;
        it9.putExtra("ID",idid);
        startActivity(it9);
    }
}