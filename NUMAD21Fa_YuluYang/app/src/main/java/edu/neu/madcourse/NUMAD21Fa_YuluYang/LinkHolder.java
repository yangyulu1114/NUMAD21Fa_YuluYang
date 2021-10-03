package edu.neu.madcourse.NUMAD21Fa_YuluYang;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LinkHolder extends RecyclerView.ViewHolder{

    public TextView mName;
    public TextView mUrl;

    public LinkHolder(@NonNull View itemView) {
        super(itemView);
        mName = itemView.findViewById(R.id.name);
        mUrl = itemView.findViewById(R.id.url);
    }
    public void bind(LinkData data, ItemClickListener listener) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(data);
            }
        });
    }
}
