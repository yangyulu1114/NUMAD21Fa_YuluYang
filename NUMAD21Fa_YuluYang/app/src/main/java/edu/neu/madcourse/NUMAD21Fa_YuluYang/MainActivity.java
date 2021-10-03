package edu.neu.madcourse.NUMAD21Fa_YuluYang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v("bush", android.util.Patterns.WEB_URL.matcher("google.com").matches() + "  1");
        Log.v("bush", android.util.Patterns.WEB_URL.matcher("www.google.com").matches() + "  2");
        Log.v("bush", android.util.Patterns.WEB_URL.matcher("http://google.com").matches() + " 3");
        Log.v("bush", android.util.Patterns.WEB_URL.matcher("https://google.com").matches() + " 4");
        Log.v("bush", android.util.Patterns.WEB_URL.matcher("http:\\google.com").matches() + " 5");
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
        }
    }
    private void onClickAbout() {
        // fix for hiding package name on xiaomi phone
        Toast toast = Toast.makeText(this, null, Toast.LENGTH_SHORT);
        toast.setText(getString(R.string.about_toast));
        toast.show();
    }
}