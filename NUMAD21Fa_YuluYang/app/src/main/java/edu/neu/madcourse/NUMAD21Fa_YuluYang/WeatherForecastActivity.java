package edu.neu.madcourse.NUMAD21Fa_YuluYang;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.List;

public class WeatherForecastActivity extends AppCompatActivity implements HttpCallback {
    private static final String KEY = "2c6d3f4272a216c7a2bfcbf8c0451d03";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);
    }

    @Override
    public void onSuccess(String s) {
        Log.v("bush", s);
        List<Weather> weatherList = JsonParseUtils.parseJSONwithJSONObject(s);
        for(Weather weather : weatherList) {
            Log.v("bush", weather.toString());
        }
    }

    @Override
    public void onFail(Exception e) {
        Log.v("bush", "", e);
    }

    public void onClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Please choose city and units");
        View dialogView = LayoutInflater.from(this).inflate(R.layout.configuration_dialog, null);
        builder.setView(dialogView);
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Spinner spinner = dialogView.findViewById(R.id.spinner);
                String city = spinner.getSelectedItem().toString();
                String units = "metric";
                RadioGroup radioGroup = dialogView.findViewById(R.id.radio_group);
                int count = radioGroup.getChildCount();
                for(int i = 1; i < count; i++) {
                    RadioButton rb = (RadioButton) radioGroup.getChildAt(i);
                    if (rb.isChecked()) {
                        units = i == 1 ? "metric" : "imperial";
                        break;
                    }
                }
                sendWeatherRequest(city, units);
            }
        });
        builder.setNegativeButton("Cancel", null);
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    public void sendWeatherRequest(String city, String units) {
        String urlFormat = "https://api.openweathermap.org/data/2.5/onecall?lat=%s&lon=%s&exclude=%s&appid=%s&units=%s";
        double latitude = GeoLocations.getLatitude(city);
        double longitude = GeoLocations.getLongitude(city);
        String url = String.format(urlFormat, latitude, longitude, "minutely,hourly,alerts", KEY, units);
        HttpUtils.sendGetRequest(url, this);
    }
}