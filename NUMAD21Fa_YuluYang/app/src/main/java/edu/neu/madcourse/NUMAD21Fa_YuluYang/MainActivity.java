package edu.neu.madcourse.NUMAD21Fa_YuluYang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickAbout(View view) {
        // fix for hiding package name on xiaomi phone
        Toast toast = Toast.makeText(this, null, Toast.LENGTH_SHORT);
        toast.setText(getString(R.string.about_toast));
        toast.show();
    }

    public void onClickyClicky(View view) {
        Intent intent = new Intent(MainActivity.this, ClickyClickyActivity.class);
        startActivity(intent);
    }
}