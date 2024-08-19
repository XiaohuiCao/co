package com.ch.co.window8;

import com.ch.co.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WindowMainActivity extends Activity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.window_activity_main);
    }

    public void onButtonClick(View v) {
        if (v.getId() == R.id.window_test) {
            Intent intent = new Intent(this, WindowTestActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.window_demo1) {
            Intent intent = new Intent(this, WindowDemoActivity_1.class);
            startActivity(intent);
        }
    }
}
