package com.ch.fo.async.intentService;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

public class MyJobIntentService extends JobIntentService {

    private static final int JOB_ID = 1000;

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        // 处理后台任务
    }

    // 启动服务的静态方法
    public static void enqueueWork(Context context, Intent intent) {
        enqueueWork(context, MyJobIntentService.class, JOB_ID, intent);
    }
}

//    // 在需要时启动服务
//    Intent intent = new Intent(context, MyJobIntentService.class);
//MyJobIntentService.enqueueWork(context, intent);
