package edu.neu.madcourse.NUMAD21Fa_YuluYang;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherHolder>{
    private final List<Weather> mLinkList;
    private final String mUnits;

    public WeatherAdapter(List<Weather> linkList, String units) {
        mLinkList = linkList;
        mUnits = units;
    }

    public void refreshData(List<Weather> linkList) {
        mLinkList.clear();
        mLinkList.addAll(linkList);
    }

    @NonNull
    @Override
    public WeatherHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_item, parent, false);
        return new WeatherHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherHolder holder, int position) {
        Weather currentItem = mLinkList.get(position);
        String date = DateFormat.format("dd-MM-yyyy", currentItem.getTime() * 1000).toString();
        holder.mDate.setText(date);
        holder.mDes.setText(currentItem.getDes());
        String tempUnits = mUnits == "metric" ? "°C" : "°F";
        holder.mTemp.setText(currentItem.getMinTemp() + tempUnits + " ~ " + currentItem.getMaxTemp() + tempUnits);
        String weather = currentItem.getWeather();
        switch (weather) {
            case "Clear" :
                holder.mImage.setImageResource(R.drawable.sunny);
                break;
            case "Clouds" :
                holder.mImage.setImageResource(R.drawable.cloudy);
                break;
            case "Snow" :
                holder.mImage.setImageResource(R.drawable.snow);
                break;
            case "Rain" :
                holder.mImage.setImageResource(R.drawable.rain);
                break;
            case "Drizzle" :
                holder.mImage.setImageResource(R.drawable.drizzle);
                break;
            case  "Thunderstorm" :
                holder.mImage.setImageResource(R.drawable.thunderstorm);
                break;
            default:
                holder.mImage.setImageResource(R.drawable.mist);
        }
    }

    @Override
    public int getItemCount() {
        return mLinkList.size();
    }
}
