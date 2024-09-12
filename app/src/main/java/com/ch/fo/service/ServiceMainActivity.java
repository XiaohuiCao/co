package com.ch.fo.service;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.ch.co.R;

public class ServiceMainActivity extends Activity implements OnClickListener {
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_main);

        Button playBtn = (Button) findViewById(R.id.play);
        Button stopBtn = (Button) findViewById(R.id.stop);
        Button pauseBtn = (Button) findViewById(R.id.pause);
        Button exitBtn = (Button) findViewById(R.id.exit);

        playBtn.setOnClickListener(this);
        stopBtn.setOnClickListener(this);
        pauseBtn.setOnClickListener(this);
        exitBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int num = -1;
        intent = new Intent(this, MusicService.class);
        int id = v.getId();
        if (id == R.id.play) {
            Toast.makeText(this, "play music...", Toast.LENGTH_SHORT).show();
            num = 1;
        }
        if (id == R.id.stop) {
            Toast.makeText(this, "stop music...", Toast.LENGTH_SHORT).show();
            num = 2;
        }
        if (id == R.id.pause) {
            Toast.makeText(this, "pause music...", Toast.LENGTH_SHORT).show();
            num = 3;
        }
        if (id == R.id.exit) {
            Toast.makeText(this, "退出...", Toast.LENGTH_SHORT);
            num = 4;
            stopService(intent);
            this.finish();
            return;
        }

        Bundle bundle = new Bundle();
        bundle.putInt("music", num);
        intent.putExtras(bundle);

        startService(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (intent != null) {
            stopService(intent);
        }
    }
}
