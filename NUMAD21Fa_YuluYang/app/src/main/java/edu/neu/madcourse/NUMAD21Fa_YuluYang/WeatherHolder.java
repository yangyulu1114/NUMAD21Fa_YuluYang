package edu.neu.madcourse.NUMAD21Fa_YuluYang;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WeatherHolder extends RecyclerView.ViewHolder{
    public TextView mDate;
    public TextView mDes;
    public TextView mTemp;
    public ImageView mImage;

    public WeatherHolder(@NonNull View itemView) {
        super(itemView);
        mDate = itemView.findViewById(R.id.date);
        mDes = itemView.findViewById(R.id.des);
        mTemp = itemView.findViewById(R.id.temp);
        mImage = itemView.findViewById(R.id.image);
    }

}
