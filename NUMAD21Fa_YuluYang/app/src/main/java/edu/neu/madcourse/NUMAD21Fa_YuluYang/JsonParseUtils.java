package edu.neu.madcourse.NUMAD21Fa_YuluYang;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonParseUtils {

    public static List<Weather> parseJSONwithJSONObject(String jsonData) {
        List<Weather> weatherList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray dailyJsonArray = jsonObject.getJSONArray("daily");
            Log.v("bush", dailyJsonArray.length() + "");
            for (int i = 0; i < dailyJsonArray.length(); i++) {
                JSONObject object = dailyJsonArray.getJSONObject(i);
                long time = object.getLong("dt");
                JSONObject tempObject = object.getJSONObject("temp");
                int minTemp = (int) tempObject.getDouble("min");
                int maxTemp = (int) tempObject.getDouble("max");
                JSONArray weatherArray = object.getJSONArray("weather");
                String weather = weatherArray.length() > 0 ? weatherArray.getJSONObject(0).getString("main") : "";
                String des = weatherArray.length() > 0 ? weatherArray.getJSONObject(0).getString("description") : "";
                weatherList.add(new Weather(time, minTemp, maxTemp, weather, des));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return weatherList;
    }
}
