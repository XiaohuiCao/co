package com.ch.co.window8;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;

import com.ch.co.R;

public class DemoActivity_1 extends Activity {
    private static final String TAG = "DemoActivity_1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.window_demo_1);
        initView();
    }

    private void initView() {
        Dialog dialog = new Dialog(this.getApplicationContext());
        TextView textView = new TextView(this);
        textView.setText("this is toast!");
        dialog.setContentView(textView);
        dialog.getWindow().setType(LayoutParams.TYPE_SYSTEM_ERROR);
        dialog.show();
    }

}
