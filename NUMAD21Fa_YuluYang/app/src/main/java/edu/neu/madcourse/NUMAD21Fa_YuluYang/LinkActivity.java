package edu.neu.madcourse.NUMAD21Fa_YuluYang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Layout;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LinkActivity extends AppCompatActivity implements ItemClickListener {

    private RecyclerView.LayoutManager mLayoutManger;
    private RecyclerView mRecyclerView;
    private LinkAdapter mLinkAdapter;
    private List<LinkData> mLinkDatas = LinkManager.mLinkDataList;
    private static final String KEY_OF_INSTANCE = "KEY_OF_INSTANCE";
    private static final String NUMBER_OF_LINKS = "NUMBER_OF_LINKS";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link);
        initialItemData(savedInstanceState);
        createRecyclerView();
    }

    public void onClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Please enter name and url");
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog, null);
        builder.setView(dialogView);
        EditText nameView = dialogView.findViewById(R.id.name_input);
        EditText urlView = dialogView.findViewById(R.id.url_input);
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = nameView.getText().toString().trim();
                String url = urlView.getText().toString().trim();
                addItem(name, url);
            }
        });

        builder.setNegativeButton("Cancel", null);
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        int size = mLinkDatas == null ? 0 : mLinkDatas.size();
        outState.putInt(NUMBER_OF_LINKS, size);

        for (int i = 0; i < size; i++) {
            outState.putString(KEY_OF_INSTANCE + i + "1", mLinkDatas.get(i).getName());
            outState.putString(KEY_OF_INSTANCE + i + "2", mLinkDatas.get(i).getUrl());
        }
        super.onSaveInstanceState(outState);
    }

    private void initialItemData(Bundle savedInstanceState) {
        if (savedInstanceState != null && savedInstanceState.containsKey(NUMBER_OF_LINKS)) {
            if (mLinkDatas == null || mLinkDatas.size() == 0) {

                int size = savedInstanceState.getInt(NUMBER_OF_LINKS);

                for (int i = 0; i < size; i++) {
                    String name = savedInstanceState.getString(KEY_OF_INSTANCE + i + "1");
                    String url = savedInstanceState.getString(KEY_OF_INSTANCE + i + "2");

                    LinkData data = new LinkData(name, url);
                    mLinkDatas.add(data);
                }
            }
        }
    }

    private void createRecyclerView() {
        mLayoutManger = new LinearLayoutManager(this);
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLinkAdapter = new LinkAdapter(mLinkDatas, this);
        mRecyclerView.setAdapter(mLinkAdapter);
        mRecyclerView.setLayoutManager(mLayoutManger);
    }

    @Override
    public void onItemClick(LinkData data) {
        String url = data.getUrl();
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "http://" + url;
        }

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        Log.v("bush", Uri.parse(url) + "");
        browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(browserIntent);
    }

    private void addItem(String name, String url) {
        View root = getWindow().getDecorView().findViewById(android.R.id.content);
        if (TextUtils.isEmpty(name)) {
            Snackbar.make(root, getResources().getString(R.string.empty_name), Snackbar.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(url)) {
            Snackbar.make(root, getResources().getString(R.string.empty_url), Snackbar.LENGTH_SHORT).show();
        } else if (!android.util.Patterns.WEB_URL.matcher(url).matches()) {
            Snackbar.make(root, getResources().getString(R.string.invalid_url), Snackbar.LENGTH_SHORT).show();
        } else {
            LinkData data = new LinkData(name, url);
            mLinkDatas.add(data);
            mLinkAdapter.notifyItemInserted(mLinkDatas.size() - 1);
            Snackbar.make(root, getResources().getString(R.string.succeed), Snackbar.LENGTH_SHORT).show();
        }
    }
}