package com.example.reservationsystem;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class registered<create_registered> extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://reservation-system-d7712-default-rtdb.firebaseio.com");

    String date,time,dd;
    Calendar c = Calendar.getInstance();
    TextView txDate;
    TextView txTime;
    boolean date_bool,time_bool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);
        getSupportActionBar().hide();

        txDate = findViewById(R.id.date_text);
        txTime = findViewById(R.id.time_text);
        Button create_registered = findViewById(R.id.create_registered_btn);
        txDate.setOnClickListener(this);
        txTime.setOnClickListener(this);
        Spinner dp =findViewById(R.id.spinner);
        String[] dps = getResources().getStringArray(R.array.Medical_Department);
        int index = dp.getSelectedItemPosition();
        date_bool=false;
        time_bool=false;

          create_registered.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    //Toast.makeText(registered.this, "1", Toast.LENGTH_SHORT).show();
                    String[] dps = getResources().getStringArray(R.array.Medical_Department);
                    int index = dp.getSelectedItemPosition();
                    Intent it = getIntent();
                    String id = it.getStringExtra("ID");
                    String str_date=dd;
                    String str_time=time;
                    String str_dp=dps[index];
                    String child_id=id+"_"+dps[index]+"_"+str_date+"_"+str_time;
                    if(date_bool && time_bool) {

                        databaseReference.child("registered").child(child_id).child("id").setValue(id);
                        databaseReference.child("registered").child(child_id).child("dp").setValue(dps[index]);
                        databaseReference.child("registered").child(child_id).child("date").setValue(date);
                        databaseReference.child("registered").child(child_id).child("time").setValue(time);
                        finish();

                    }
                    else
                    {
                        Toast.makeText(registered.this, "請選擇科別、日期、時間", Toast.LENGTH_SHORT).show();
                    }
                }
            });
    }
    @Override
    public void onClick(View v) {
        if(v == txDate) //按的是日期文字
        {
            //建立日期選擇交談窗，並傳入設定完成時的監聽物件
            new DatePickerDialog(this,this,c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH)).show();
        }
        else if(v == txTime)
        {
            new TimePickerDialog(this,this,c.get(Calendar.HOUR_OF_DAY),c.get(Calendar.MINUTE),true).show();
        }
    }

    @Override
    public void onDateSet(DatePicker v, int y, int m, int d) {
        txDate.setText("日期："+y+"/"+(m+1)+"/"+d);
        date = y+"/" + (m+1) + "/" + d;
        dd = y+"-"+(m+1)+"-"+d;
        if(!dd.isEmpty())
        {
            date_bool=true;
        }
        else
        {
            date_bool=false;
        }
    }


    @Override
    public void onTimeSet(TimePicker timePicker, int h, int m) {
        txTime.setText("時間：" + h + "："+m);
        time = h+"："+m;
        if (!dd.isEmpty())
        {
            time_bool=true;
        }
        else
        {
            time_bool=false;
        }
    }
}