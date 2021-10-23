package edu.neu.madcourse.NUMAD21Fa_YuluYang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class WeatherForecastActivity extends AppCompatActivity implements HttpCallback {
    private static final String KEY = "2c6d3f4272a216c7a2bfcbf8c0451d03";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);
        String urlFormat = "https://api.openweathermap.org/data/2.5/onecall/timemachine?lat=%s&lon=%s&dt=%s&appid=%s";

        String url = String.format(urlFormat, 33.44, -94.04, System.currentTimeMillis()/1000, KEY);
        HttpUtils.sendGetRequest(url, this);
    }

    @Override
    public void onSuccess(String s) {
        Log.v("bush", s);
    }

    @Override
    public void onFail(Exception e) {
        Log.v("bush", "", e);
    }
}