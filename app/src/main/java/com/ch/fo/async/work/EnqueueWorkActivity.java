package com.ch.fo.async.work;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.ch.co.R;

/**
 * 步骤 3：调度任务
 * 一旦定义了 Worker 类，你就可以在需要的地方调度任务。这可以在活动（Activity）、片段（Fragment）或其他地方实现。
 */
public class EnqueueWorkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 创建一个一次性任务请求
        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(MyWorker.class).build();

        // 使用 WorkManager 调度任务
        WorkManager.getInstance(this).enqueue(workRequest);
    }
}