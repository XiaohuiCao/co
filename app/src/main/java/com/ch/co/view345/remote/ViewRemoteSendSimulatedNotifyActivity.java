package com.ch.co.view345.remote;

import static com.ch.co.utils.MyConstants.*;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.ch.co.R;

/**
 * 发送消息模拟器
 */
public class ViewRemoteSendSimulatedNotifyActivity extends Activity {
    private static final String TAG = "ViewRemoteContentViewAct";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_remote_send_msg);
        Log.d(TAG, "onCreate");
//        Toast.makeText(this, getIntent().getStringExtra(REMOTE_VIEW_MSG_ID), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "12345", Toast.LENGTH_SHORT).show();
        initView();
    }

    private void initView() {
    }

    public void onButtonClick(View view) {
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.layout_simulated_notification);
        remoteViews.setTextViewText(R.id.custom_notification_msg_up, "msg from process:" + Process.myPid());
        remoteViews.setImageViewResource(R.id.notify_small_icon, R.drawable.music);
        PendingIntent contentIntent = PendingIntent.getActivity(this,
                0, new Intent(this, ViewRemoteContentIntentAct.class), PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent openMsgContentView = PendingIntent.getActivity(this, 0,
                new Intent(this, ViewRemoteSendSimulatedNotifyActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.notify_item_holder, contentIntent);
        remoteViews.setOnClickPendingIntent(R.id.custom_notification_msg_down, openMsgContentView);
        Intent intent = new Intent(REMOTE_ACTION);
        intent.putExtra(EXTRA_REMOTE_VIEWS, remoteViews);
        sendBroadcast(intent);
    }
}
