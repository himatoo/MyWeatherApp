package com.example.like2.myweatherapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class WeatherRecyclerAdapter extends RecyclerView.Adapter<WeatherRecyclerAdapter.WeatherView>{
    public WeatherRecyclerAdapter() {
    }
    @NonNull
    @Override
    public WeatherView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        return new WeatherView(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item,viewGroup,false ));
    }
    @Override
    public void onBindViewHolder(@NonNull WeatherView weatherView, int i) {

        weatherView.textView.setText("");



    }
    @Override
    public int getItemCount() {
        return 0;
    }
    class WeatherView extends RecyclerView.ViewHolder {
        TextView textView;

        public WeatherView(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.tv_weatherdata);
        }
    }
}
