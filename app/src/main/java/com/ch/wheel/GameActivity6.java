package com.ch.wheel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.ch.co.R;

/**
 * 新添加一个页面，通过livedata进行数据共享
 */
public class GameActivity6 extends AppCompatActivity {

    private SteeringWheelView5 steeringWheelView;
    private TextView angleTextView;
    private Button navigateButton;
    private SharedViewModel sharedViewModel;
    private DataRepository dataRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wheel_activity_game1);

        steeringWheelView = findViewById(R.id.steeringWheelView);
        angleTextView = findViewById(R.id.angleTextView);
        navigateButton = findViewById(R.id.navigateButton);
        // 获取 DataRepository 的实例
        dataRepository = DataRepository.getInstance();

        // 获取 ViewModel
        sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);

        // 观察方向盘角度变化
        steeringWheelView.getAngleLiveData().observe(this, new Observer<Float>() {
            @Override
            public void onChanged(Float angle) {
                // 更新角度显示
                angleTextView.setText("Angle: " + angle + "°");
                sharedViewModel.setAngle(angle);  // 更新共享的 ViewModel 中的角度数据
                dataRepository.setAngle(angle);  // 更新全局 DataRepository 中的角度数据
            }
        });

        // 设置按钮点击事件，启动 SecondActivity
        navigateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameActivity6.this, SecondLiveDataActivity.class);
                startActivity(intent);
            }
        });
    }
}