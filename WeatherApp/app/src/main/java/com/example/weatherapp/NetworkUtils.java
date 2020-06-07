package com.example.weatherapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtils {
    public static String getWeatherInfo(String apiKey) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String weatherJSONString = null;

        try {
            urlConnection = (HttpURLConnection) new URL("http://api.openweathermap.org/data/2.5/find?lat=1.7294&lon=103.8992&cnt=10&APPID="+apiKey).openConnection();

            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder builder = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append("\n");
                if (builder.length() == 0) {
                    return null;
                }
            }
            weatherJSONString = builder.toString();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return weatherJSONString;
    }
}
