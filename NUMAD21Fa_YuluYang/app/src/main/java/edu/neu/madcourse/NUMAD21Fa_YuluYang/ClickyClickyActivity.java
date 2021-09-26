package edu.neu.madcourse.NUMAD21Fa_YuluYang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ClickyClickyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clicky_clicky);
        ViewGroup container = findViewById(R.id.container);
        for (int i = 0; i < container.getChildCount() - 1; i++) {
            Button button = (Button) container.getChildAt(i);
            button.setOnTouchListener(new TouchListener(button));
        }
    }

    private class TouchListener implements View.OnTouchListener {

        private String mName;

        public TouchListener(Button button) {
            mName = button.getText().toString();
        }
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            TextView tvStatus = findViewById(R.id.status);
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    tvStatus.setText(getString(R.string.pressed_format, mName));
                    v.setBackgroundResource(R.drawable.btn_bg_pressed);
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    tvStatus.setText(getString(R.string.pressed_format, "-"));
                    v.setBackgroundResource(R.drawable.btn_bg_normal);
                    break;
                default:
                    break;
            }
            return v.onTouchEvent(event);
        }
    }
}