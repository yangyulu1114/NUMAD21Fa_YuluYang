package edu.neu.madcourse.NUMAD21Fa_YuluYang;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class LinkAdapter extends RecyclerView.Adapter<LinkHolder> {

    private final List<LinkData> mLinkList;
    private final ItemClickListener mListener;

    public LinkAdapter(List<LinkData> linkList, ItemClickListener listener) {
        mLinkList = linkList;
        mListener = listener;
    }

    @NonNull
    @Override
    public LinkHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_link, parent, false);
        return new LinkHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LinkHolder holder, int position) {
        LinkData currentItem = mLinkList.get(position);

        holder.mName.setText(currentItem.getName());
        holder.mUrl.setText(currentItem.getUrl());
        holder.bind(mLinkList.get(position), mListener);
    }

    @Override
    public int getItemCount() {
        return mLinkList.size();
    }
}
