package com.arthurpasqualon.appswipe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout layoutBackground;
    private TextView tvText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutBackground = findViewById(R.id.layoutBackground);
        tvText = findViewById(R.id.tvText);

        layoutBackground.setOnTouchListener(new OnSwipeTouchListener(this){
            @Override
            public void onSwipeBottom() {
                super.onSwipeBottom();
                tvText.setText("Swipe Bottom");
                layoutBackground.setBackgroundColor(Color.RED);
            }

            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                tvText.setText("Swipe Left");
                layoutBackground.setBackgroundColor(Color.GREEN);
            }

            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                tvText.setText("Swipe Right");
                layoutBackground.setBackgroundColor(Color.YELLOW);
            }

            @Override
            public void onSwipeTop() {
                super.onSwipeTop();
                tvText.setText("Swipe Top");
                layoutBackground.setBackgroundColor(Color.BLUE);
            }

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                tvText.setText("Simple Touch");
                layoutBackground.setBackgroundColor(Color.WHITE);
                return super.onTouch(v, event);
            }
        });
    }
}