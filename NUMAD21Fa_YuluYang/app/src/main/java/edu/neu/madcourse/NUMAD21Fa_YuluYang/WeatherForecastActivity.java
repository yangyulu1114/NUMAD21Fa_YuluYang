package edu.neu.madcourse.NUMAD21Fa_YuluYang;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class WeatherForecastActivity extends AppCompatActivity implements HttpCallback {
    private static final String KEY = "2c6d3f4272a216c7a2bfcbf8c0451d03";
    private RecyclerView.LayoutManager mLayoutManger;
    private RecyclerView mRecyclerView;
    private WeatherAdapter mWeatherAdapter;
    private List<Weather> mWeatherList = new ArrayList<>();
    private ProgressBar mProgressBar;
    private Handler mHandler = new Handler();
    private String mUnits = "metric";
    private ActionBar mActionBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);
        mProgressBar = findViewById(R.id.progressBar);
        mActionBar = getSupportActionBar();
        createRecyclerView();
    }

    @Override
    public void onSuccess(String s) {
        Log.v("bush", s);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mWeatherList.clear();
                mWeatherList.addAll(JsonParseUtils.parseJSONwithJSONObject(s));
                for(Weather weather : mWeatherList) {
                    Log.v("bush", weather.toString());
                }
                mProgressBar.setVisibility(View.INVISIBLE);
                mRecyclerView.setVisibility(View.VISIBLE);
                refreshUI();
            }
        }, 400);
    }

    @Override
    public void onFail(Exception e) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mProgressBar.setVisibility(View.INVISIBLE);
                Toast toast = Toast.makeText(WeatherForecastActivity.this, null, Toast.LENGTH_SHORT);
                toast.setText(getString(R.string.request_failed));
                toast.show();
            }
        });
    }

    public void onClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Configure parameters");
        View dialogView = LayoutInflater.from(this).inflate(R.layout.configuration_dialog, null);
        builder.setView(dialogView);
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Spinner spinner = dialogView.findViewById(R.id.spinner);
                String city = spinner.getSelectedItem().toString();
                RadioGroup radioGroup = dialogView.findViewById(R.id.radio_group);
                int count = radioGroup.getChildCount();
                for(int i = 1; i < count; i++) {
                    RadioButton rb = (RadioButton) radioGroup.getChildAt(i);
                    if (rb.isChecked()) {
                        mUnits = i == 1 ? "metric" : "imperial";
                        break;
                    }
                }
                sendWeatherRequest(city, mUnits);
                mActionBar.setTitle("Weather Forecast For " + city);
                mProgressBar.setVisibility(View.VISIBLE);
                mRecyclerView.setVisibility(View.INVISIBLE);
            }
        });
        builder.setNegativeButton("Cancel", null);
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    public void sendWeatherRequest(String city, String units) {
        String urlFormat = "https://api.openweathermap.org/data/2.5/onecall?lat=%s&lon=%s&exclude=%s&appid=%s&units=%s";
        double latitude = GeoLocations.getLatitude(city);
        double longitude = GeoLocations.getLongitude(city);
        String url = String.format(urlFormat, latitude, longitude, "minutely,hourly,alerts", KEY, units);
        HttpUtils.sendGetRequest(url, this);
    }

    private void createRecyclerView() {
        mLayoutManger = new LinearLayoutManager(this);
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mWeatherAdapter = new WeatherAdapter();
        mRecyclerView.setAdapter(mWeatherAdapter);
        mRecyclerView.setLayoutManager(mLayoutManger);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mRecyclerView.setVisibility(View.VISIBLE);
    }
    public void refreshUI() {
        mWeatherAdapter.refreshData(mWeatherList, mUnits);
        mWeatherAdapter.notifyDataSetChanged();
    }
}