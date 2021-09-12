package edu.neu.madcourse.NUMAD21Fa_YuluYang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button mAboutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAboutButton = findViewById(R.id.aboutBtn);
        setonClickListener();
    }

    protected void setonClickListener() {
        mAboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = new Toast(getBaseContext());
                TextView tv = new TextView(getBaseContext());
                tv.setText("Name: Yulu Yang; Email: yang.yulu@northeastern.edu ");
                toast.setView(tv);
                toast.show();
            }
        });
    }
}