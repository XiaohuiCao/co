package com.ch.co.view345.remote;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;
import com.ch.co.R;

public class ViewRemoteContentIntentAct extends Activity {
    private static final String TAG = "ViewRemoteContentIntentAct";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_remote_content_intent);
        initView();
    }

    private void initView() {
        Toast.makeText(this, getIntent().getStringExtra("remoteViewId"),
                Toast.LENGTH_SHORT).show();
    }
}
