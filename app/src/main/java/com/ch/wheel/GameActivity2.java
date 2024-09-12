package com.ch.wheel;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;

import com.ch.co.R;

/**
 * Android实现自定义方向盘-2添加陀螺仪
 */
public class GameActivity2 extends Activity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor gyroscopeSensor;
    private SteeringWheelView2 steeringWheelView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wheel_activity_game1);

        steeringWheelView = findViewById(R.id.steeringWheelView);

        // 初始化传感器管理器
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager != null) {
            gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
            if (gyroscopeSensor != null) {
                sensorManager.registerListener(this, gyroscopeSensor, SensorManager.SENSOR_DELAY_GAME);
            } else {
                Log.e("GameActivity", "Gyroscope sensor not available.");
            }
        }

        steeringWheelView.setOnSteeringWheelChangeListener(angle -> {
            // 根据方向盘角度处理游戏中的方向
            Log.d("SteeringWheel", "Angle: " + angle);
            // 在这里根据angle控制游戏中的方向
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (gyroscopeSensor != null) {
            sensorManager.registerListener(this, gyroscopeSensor, SensorManager.SENSOR_DELAY_GAME);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            float rotationRateZ = event.values[2];

            // 使用rotationRateZ作为方向盘旋转的参考
            float newAngle = steeringWheelView.getCurrentAngle() + rotationRateZ * 10; // 放大因子调整灵敏度
            steeringWheelView.updateSteeringWheelAngle(newAngle);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // 不需要处理精度变化
    }
}
