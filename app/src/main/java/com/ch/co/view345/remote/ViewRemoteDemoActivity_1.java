package com.ch.co.view345.remote;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;
import com.ch.co.R;

public class ViewRemoteDemoActivity_1 extends Activity {
    private static final String TAG = "DemoActivity_1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_remote_demo_1);
        initView();
    }

    private void initView() {
        Toast.makeText(this, getIntent().getStringExtra("sid"),
                Toast.LENGTH_SHORT).show();
    }
}
