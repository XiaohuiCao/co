package com.ch.CustomClick;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.ch.co.R;

public class CustomClickMainActivity extends Activity {
    Wheel wheel;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_click_main_activity);
        wheel = (Wheel) findViewById(R.id.myWheel);
        tv = (TextView) findViewById(R.id.tv);
        wheel.setOnMyWheelMoveListener(new Wheel.OnMyWheelMoveListener() {//设置交互事件
            @Override
            public void onValueChanged(int xDistance, int yDistance) {
                tv.setText("" + xDistance + "," + yDistance);
            }
        });
    }
}