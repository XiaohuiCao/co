package com.ch.fo.async.work;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

/**
 * 步骤 2：创建一个 Worker 类
 * Worker 类是你定义后台任务的地方。在这个类中，你将重写 doWork() 方法，并在其中编写你想要后台执行的任务。
 */
public class MyWorker extends Worker {

    public MyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        // 在此处执行后台任务
        // 例如：模拟任务处理
        for (int i = 0; i < 10; i++) {
            // 模拟一些后台任务处理
            System.out.println("任务处理中: " + i);
        }

        // 返回结果
        return Result.success();
    }
}

//    // 在需要时调度任务
//    OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(MyWorker.class).build();
//WorkManager.getInstance(context).enqueue(workRequest);
