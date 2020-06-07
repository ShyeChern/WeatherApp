package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class WeatherDetail extends AppCompatActivity {
    private TextView mCityName;
    private TextView mWeather;
    private TextView mTemperature;
    private TextView mPressure;
    private TextView mHumidity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_detail);

        mCityName=findViewById(R.id.city_txt);
        mWeather=findViewById(R.id.weather_txt);
        mTemperature=findViewById(R.id.temperature_txt);
        mPressure=findViewById(R.id.pressure_txt);
        mHumidity=findViewById(R.id.humidity_txt);

        Intent intent = getIntent();
        mCityName.setText(intent.getStringExtra("CITYNAME"));
        mWeather.setText("Weather: "+intent.getStringExtra("WEATHER"));
        mTemperature.setText("Temperature: "+intent.getStringExtra("TEMPERATURE")+" Kelvin");
        mPressure.setText("Pressure: "+intent.getIntExtra("PRESSURE",0)+" hPa");
        mHumidity.setText("Humidity: "+intent.getIntExtra("HUMIDITY",0)+" %");
    }
}
