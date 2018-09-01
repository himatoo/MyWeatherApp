package com.example.like2.myweatherapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class WeatherRecyclerAdapter extends RecyclerView.Adapter<WeatherRecyclerAdapter.WeatherView>{
    List<WeatherDataModel> dataModels;
    private final ListitemclickListner listner;

    public WeatherRecyclerAdapter(List<WeatherDataModel> list, ListitemclickListner listner1) {
        dataModels = list;
        listner = listner1;
    }
    @NonNull
    @Override
    public WeatherView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        return new WeatherView(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item,viewGroup,false ));
    }
    @Override
    public void onBindViewHolder(@NonNull WeatherView weatherView, int i) {

        weatherView.textView.setText(dataModels.get(i).getWeather());
    }
    @Override
    public int getItemCount() {
        return dataModels.size();
    }

    public interface ListitemclickListner {
        void onListitemclick(int index);
    }

    class WeatherView extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;

        public WeatherView(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.tv_weatherdata);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listner.onListitemclick(getAdapterPosition());
        }
    }
}
