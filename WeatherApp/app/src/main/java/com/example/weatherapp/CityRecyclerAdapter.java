package com.example.weatherapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.R;

import java.util.ArrayList;

public class CityRecyclerAdapter extends RecyclerView.Adapter<CityRecyclerAdapter.ViewHolder>
{

    private ArrayList<CityDetail> mCities ;
    private Context mContext;

    public CityRecyclerAdapter(Context context, ArrayList<CityDetail> cities)
    {
        mCities = cities;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_city,parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }



    public void onBindViewHolder(@NonNull CityRecyclerAdapter.ViewHolder holder, final int position)
    {
        holder.city.setText(mCities.get(position).getName());
        holder.city.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(mContext, WeatherDetail.class);
                intent.putExtra("CITYNAME", mCities.get(position).getName());
                intent.putExtra("WEATHER", mCities.get(position).getWeather());
                intent.putExtra("TEMPERATURE", mCities.get(position).getTemperature().toString());
                intent.putExtra("PRESSURE", mCities.get(position).getPressure());
                intent.putExtra("HUMIDITY", mCities.get(position).getHumidity());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return mCities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView city;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);

            city=itemView.findViewById(R.id.city_btn);

        }
    }
}

