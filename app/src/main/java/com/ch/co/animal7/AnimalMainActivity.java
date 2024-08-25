package com.ch.co.animal7;

import com.ch.co.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class AnimalMainActivity extends Activity {
    private static final String TAG = "AnimalMainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animal_activity_main);
    }

    public void onButtonClick(View v) {
        if (v.getId() == R.id.btn_switch_animal) {
            Intent intent = new Intent(this, AnimalSwitchBtnActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim);
        } else if (v.getId() == R.id.btn_large_animal) {
            Intent intent = new Intent(this, AnimalLargeActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btn_layout_list_animal) {
            Intent intent = new Intent(this, AnimalLayoutListActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        Log.d(TAG, "onWindowFocusChanged, focus:" + hasFocus);
        super.onWindowFocusChanged(hasFocus);
    }
}
