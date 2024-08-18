package com.ch.co.animal7;

import com.ch.co.R;

import android.app.Activity;
import android.os.Bundle;

public class AnimalTestActivity extends Activity {

    private static final String TAG = "TestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animal_activity_test);
        initView();
    }

    private void initView() {

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
    }

}
