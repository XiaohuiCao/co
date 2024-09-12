package com.ch.wheel;


import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.ch.co.R;

public class SecondLiveDataActivity extends AppCompatActivity {
    private SharedViewModel sharedViewModel;
    private TextView angleTextView;
    private DataRepository dataRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wheel_second_activity_game1);

        angleTextView = findViewById(R.id.angleTextView);
        // 获取 DataRepository 的实例
        dataRepository = DataRepository.getInstance();
        // 获取共享的 ViewModel
        sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);

//        // 观察角度数据的变化
//        sharedViewModel.getAngleLiveData().observe(this, angle -> {
//            System.out.println("----------角度------：" + angle);
//            // 更新角度显示
//            angleTextView.setText("Angle from MainActivity: " + angle + "°");
//        });

        // 观察全局的角度数据变化
        dataRepository.getAngleLiveData().observe(this, angle -> {
            // 更新角度显示
            angleTextView.setText("Angle from MainActivity: " + angle + "°");
        });
    }
}
