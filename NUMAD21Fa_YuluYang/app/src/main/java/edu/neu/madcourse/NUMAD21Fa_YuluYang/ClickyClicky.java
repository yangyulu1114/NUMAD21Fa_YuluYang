package edu.neu.madcourse.NUMAD21Fa_YuluYang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ClickyClicky extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clicky_clicky);
        setOnTouchListener();
    }

    public void onClickBtn(View view) {
        TextView textView = findViewById(R.id.text);
        switch (view.getId()) {
            case R.id.BtnA:
                textView.setText("pressed : A");
                break;
            case R.id.BtnB:
                textView.setText("pressed : B");
                break;
            case R.id.BtnC:
                textView.setText("pressed : C");
                break;
            case R.id.BtnD:
                textView.setText("pressed : D");
                break;
            case R.id.BtnE:
                textView.setText("pressed : E");
                break;
            case R.id.BtnF:
                textView.setText("pressed : F");
                break;

        }

    }

    public void setOnTouchListener() {
        Button btnA = findViewById(R.id.BtnA);
        btnA.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.v("bush", "down a");
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.v("bush", "UP a");
                        break;
                    default:
                        Log.v("bush", motionEvent.getAction() + "");
                }
                return false;
            }
        });
    }

}