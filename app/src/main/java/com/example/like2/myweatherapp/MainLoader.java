package com.example.like2.myweatherapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
import java.util.List;

public class MainLoader extends AsyncTaskLoader<List<WeatherDataModel>> {
    private List<WeatherDataModel> list;

    MainLoader(@NonNull Context context) {
        super(context);
    }

    @Nullable
    @Override
    public List<WeatherDataModel> loadInBackground() {
        return NetworkUtils.Fetchdata();
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        if (list != null) {
            deliverResult(list);
        } else {
            forceLoad();
        }
    }
    @Override
    public void deliverResult(@Nullable List<WeatherDataModel> data) {
        list = data;
        super.deliverResult(data);
    }
}
