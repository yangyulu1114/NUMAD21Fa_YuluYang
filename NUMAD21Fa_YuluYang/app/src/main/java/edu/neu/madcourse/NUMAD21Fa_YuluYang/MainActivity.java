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

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.aboutBtn :
                onClickAbout();
                break;
            case R.id.clicky_clicky:
                Intent intent = new Intent(MainActivity.this, ClickyClickyActivity.class);
                startActivity(intent);
                break;
            case R.id.link_collector:
                Intent intent2 = new Intent(MainActivity.this, LinkActivity.class);
                startActivity(intent2);
                break;
            case R.id.locator:
                Intent intent3 = new Intent(MainActivity.this, LocatorActivity.class);
                startActivity(intent3);
            case R.id.newBtn:
                Intent intent4 = new Intent(MainActivity.this, WeatherForecastActivity.class);
                startActivity(intent4);
        }
    }
    private void onClickAbout() {
        // fix for hiding package name on xiaomi phone
        Toast toast = Toast.makeText(this, null, Toast.LENGTH_SHORT);
        toast.setText(getString(R.string.about_toast));
        toast.show();
    }
}