package com.ch.co.animal7;

import com.ch.co.R;

import android.app.Activity;
import android.os.Bundle;

// 动画进入方式和退出方式不一样
public class AnimalSwitchBtnActivity extends Activity {

    private static final String TAG = "AnimalSwitchBtnActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animal_switch_btn_activity);
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
