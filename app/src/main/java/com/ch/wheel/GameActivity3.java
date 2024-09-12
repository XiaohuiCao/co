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
import java.util.LinkedList;

/**
 * Android实现自定义方向盘-3添加平滑处理
 */
public class GameActivity3 extends Activity implements SensorEventListener {
    private static final float ALPHA = 0.1f; // 低通滤波器系数
    private static final int WINDOW_SIZE = 10; // 滑动窗口大小

    private SensorManager sensorManager;
    private Sensor gyroscopeSensor;
    private SteeringWheelView3 steeringWheelView;
    private float[] gyroscopeRotationRate = new float[3];
    private LinkedList<Float> rotationRateZWindow = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wheel_activity_game1);

        steeringWheelView = findViewById(R.id.steeringWheelView);

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
            Log.d("SteeringWheel", "Angle: " + angle);
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
            // 低通滤波器
            gyroscopeRotationRate[0] = lowPassFilter(event.values[0], gyroscopeRotationRate[0]);
            gyroscopeRotationRate[1] = lowPassFilter(event.values[1], gyroscopeRotationRate[1]);
            gyroscopeRotationRate[2] = lowPassFilter(event.values[2], gyroscopeRotationRate[2]);

            float rotationRateZ = gyroscopeRotationRate[2];

            // 更新滑动窗口
            if (rotationRateZWindow.size() >= WINDOW_SIZE) {
                rotationRateZWindow.poll();
            }
            rotationRateZWindow.add(rotationRateZ);

            // 计算滑动平均值
            float averageRotationRateZ = calculateMovingAverage(rotationRateZWindow);

            // 更新方向盘角度
            float newAngle = steeringWheelView.getCurrentAngle() + averageRotationRateZ * 10;
            steeringWheelView.updateSteeringWheelAngle(newAngle);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // 不需要处理精度变化
    }

    private float lowPassFilter(float current, float previous) {
        return previous + ALPHA * (current - previous);
    }

    private float calculateMovingAverage(LinkedList<Float> window) {
        float sum = 0;
        for (float value : window) {
            sum += value;
        }
        return sum / window.size();
    }
}
