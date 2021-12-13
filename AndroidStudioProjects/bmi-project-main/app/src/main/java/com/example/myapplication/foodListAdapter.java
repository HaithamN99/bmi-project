package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class foodListAdapter extends RecyclerView.Adapter<foodListAdapter.ViewHilder> {

    public static class ViewHilder extends RecyclerView.ViewHolder{


        TextView food_name,food_catagory,cal_number;

        public ViewHilder(@NonNull View itemView) {
            super(itemView);

            food_name = (TextView) itemView.findViewById(R.id.food_name);
            food_catagory = (TextView) itemView.findViewById(R.id.food_catagory);
            cal_number = (TextView) itemView.findViewById(R.id.cal_number);

        }
    }

    Context context;
    List<foodInfo> f;

    public foodListAdapter(Context context , List<foodInfo> f ){

        this.context=context;
        this.f=f;

    }
    @Override
    public ViewHilder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

    View v = LayoutInflater.from(context).inflate(R.layout.custom_food_info,parent,false);
        return new ViewHilder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHilder holder, int position) {
    foodInfo foods=f.get(position);
        holder.food_name.setText(foods.getFoodName());
        holder.food_catagory.setText(foods.getFoodCatagory());
        holder.cal_number.setText(foods.getCal()+"");
    }

    @Override
    public int getItemCount() {
        return f.size();
    }
}
