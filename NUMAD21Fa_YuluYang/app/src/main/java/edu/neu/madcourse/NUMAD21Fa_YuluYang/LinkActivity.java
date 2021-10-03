package edu.neu.madcourse.NUMAD21Fa_YuluYang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.os.Bundle;
import android.text.Layout;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class LinkActivity extends AppCompatActivity {

    private ConstraintLayout mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link);
        mLayout = findViewById(R.id.layout);
    }

    public void onClick(View view) {
        Snackbar.make(mLayout, "SnakBar", Snackbar.LENGTH_SHORT).show();
    }
}