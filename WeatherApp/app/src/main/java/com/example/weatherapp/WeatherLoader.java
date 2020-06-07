package com.example.weatherapp;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.example.weatherapp.NetworkUtils;

public class WeatherLoader extends AsyncTaskLoader<String> {
    private String apiKey;

    public WeatherLoader(@NonNull Context context, String key) {
        super(context);
        apiKey = key;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Nullable
    @Override
    public String loadInBackground() {

        return NetworkUtils.getWeatherInfo(apiKey);
    }
}
