package com.example.weatherapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    private ArrayList<CityDetail> mCities = new ArrayList<>();

    private final String apiKey="dad8d166d1de93038e825130e6e34f25";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getSupportLoaderManager().getLoader(0)!=null){
            getSupportLoaderManager().initLoader(0,null,this);
        }

        Bundle queryBundle = new Bundle();
        getSupportLoaderManager().restartLoader(0, queryBundle, this);
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        return new WeatherLoader(this, apiKey);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        JSONObject response = null;
        try {
            response = new JSONObject(data);
            if (response.has("list")) {
                JSONArray city=response.getJSONArray("list");

                for (int i = 0; i < city.length(); i++) {
                    JSONObject listRow = city.getJSONObject(i);
                    JSONObject mainObj = listRow.getJSONObject("main");
                    JSONArray weatherArr = listRow.getJSONArray("weather");
                    JSONObject weatherRow = weatherArr.getJSONObject(0);

                    CityDetail cd=new CityDetail();
                    cd.setName(listRow.getString("name"));
                    cd.setWeather(weatherRow.getString("main"));
                    cd.setTemperature(mainObj.getDouble("temp"));
                    cd.setPressure(mainObj.getInt("pressure"));
                    cd.setHumidity(mainObj.getInt("humidity"));
                    mCities.add(cd);
                }

                final RecyclerView recyclerView = findViewById(R.id.recyclerViewCity);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                CityRecyclerAdapter adapter = new CityRecyclerAdapter(this, mCities);
                recyclerView.setAdapter(adapter);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}
