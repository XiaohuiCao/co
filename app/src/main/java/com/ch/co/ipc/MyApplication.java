package com.ch.co.ipc;

import com.ch.co.ipc.binderpool.BinderPool;
import com.ch.co.ipc.utils.MyUtils;

import android.app.Application;
import android.os.Process;
import android.util.Log;

public class MyApplication extends Application {

    private static final String TAG = "MyApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        String processName = MyUtils.getProcessName(getApplicationContext(), Process.myPid());
        Log.d(TAG, "application start, process name:" + processName);
        new Thread(this::doWorkInBackground).start();
    }

    private void doWorkInBackground() {
        // init binder pool
        Log.d(TAG, "application do work in background");
        BinderPool.getInstance(getApplicationContext());
    }
}
