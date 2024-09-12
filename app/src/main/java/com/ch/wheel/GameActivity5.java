package com.ch.wheel;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import com.ch.co.R;

/**
 * Android实现自定义方向盘-5livedata实现
 */
public class GameActivity5 extends AppCompatActivity {

    private SteeringWheelView5 steeringWheelView;
    private TextView angleTextView;
    private TextView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wheel_activity_game1);

        steeringWheelView = findViewById(R.id.steeringWheelView);
        angleTextView = findViewById(R.id.angleTextView);
        test = findViewById(R.id.test_data);

        // 观察方向盘角度变化
        steeringWheelView.getAngleLiveData().observe(this, new Observer<Float>() {
            @Override
            public void onChanged(Float angle) {
                // 更新角度显示
                angleTextView.setText("Angle: " + angle + "°");
            }
        });

        steeringWheelView.getCountLiveData().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                test.setText("count" + integer);
            }
        });
    }
}