package com.ch.wheel;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.ch.co.R;

/**
 * Android实现自定义方向盘
 */
public class GameActivity1 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wheel_activity_game1);

        SteeringWheelView1 steeringWheelView = findViewById(R.id.steeringWheelView);
        steeringWheelView.setOnSteeringWheelChangeListener(new SteeringWheelView1.OnSteeringWheelChangeListener() {
            @Override
            public void onSteeringWheelChanged(float angle) {
                // 处理角度变化，例如控制车辆方向
                Log.d("SteeringWheel", "Angle: " + angle);
                // 在这里根据angle控制游戏中的方向
            }
        });
    }
}