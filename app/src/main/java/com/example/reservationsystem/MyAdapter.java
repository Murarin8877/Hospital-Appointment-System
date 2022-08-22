package com.example.reservationsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter  extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<UserRegistered> list;

    public MyAdapter(Context context, ArrayList<UserRegistered> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        UserRegistered userre = list.get(position);

        holder.dp.setText(userre.getDp());
        holder.date.setText(userre.getDate());
        holder.time.setText(userre.getTime());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView id,dp,date,time;


        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);

            dp = itemView.findViewById(R.id.dp_registered);
            date = itemView.findViewById(R.id.date_registered);
            time = itemView.findViewById(R.id.time_registered);

        }
    }

}
