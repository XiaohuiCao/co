package com.ch.co.ipc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.ch.co.R;
import com.ch.co.view345.event.ViewEventMainActivity;

public class CActivity extends Activity {
    private static final String TAG = "ThirdActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ipc_activity_c);
        findViewById(R.id.to_act_main).setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setClass(CActivity.this, MainActivity.class);
            intent.putExtra("time", System.currentTimeMillis());
            startActivity(intent);
        });

        findViewById(R.id.to_view_event_main).setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setClass(this, ViewEventMainActivity.class);
            startActivity(intent);
        });
        Log.d(TAG, "onCreate");
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState");
    }
}
