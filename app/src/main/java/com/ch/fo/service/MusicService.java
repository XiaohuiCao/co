package com.ch.fo.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.ch.co.R;

import java.io.IOException;

/**
 * @author mialab
 * @date 创建时间：2015-8-9 上午11:11:21
 *
 */
public class MusicService extends Service {
    private static final String TAG = "MusicService";
    private MediaPlayer mediaPlayer;
    private boolean reset = false;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.v(TAG, "onCreate");
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.lake);
            mediaPlayer.setLooping(false);
        }
    }

    @Override
    public void onDestroy() {
        Log.v(TAG, "onDestroy");
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Log.v(TAG, "onStart");
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                int num = bundle.getInt("music");
                switch (num) {
                    case 1:
                        play();
                        break;
                    case 2:
                        stop();
                        break;
                    case 3:
                        pause();
                        break;
                }
            }
        }
    }

    public void play() {
        Log.v(TAG, "-----------" + reset + "----------");
        if (mediaPlayer == null)
            return;
        if (reset)
            mediaPlayer.seekTo(0);
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
    }

    public void pause() {
        reset = false;
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    public void stop() {
        reset = true;
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            try {
                mediaPlayer.prepare(); // 在调用stop后如果需要再次通过start
                // 进行播放,需要之前调用prepare函数
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
