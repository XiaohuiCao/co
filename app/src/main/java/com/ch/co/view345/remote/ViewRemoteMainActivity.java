package com.ch.co.view345.remote;

import static com.ch.co.utils.MyConstants.*;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RemoteViews;
import com.ch.co.R;

/**
 * 远程view含两部分，通知栏消息和发送模拟通知栏消息
 */
public class ViewRemoteMainActivity extends Activity {
    private static final String TAG = "ViewRemoteMainActivity";
    private LinearLayout mRemoteViewsContent;

    private BroadcastReceiver mRemoteViewsReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            RemoteViews remoteViews = intent.getParcelableExtra(EXTRA_REMOTE_VIEWS);
            if (remoteViews != null) {
                updateUI(remoteViews);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_remote_activity_main);
        initView();
    }
    
    private void initView() {
        mRemoteViewsContent = (LinearLayout) findViewById(R.id.remote_views_content);
        IntentFilter filter = new IntentFilter(REMOTE_ACTION);
        registerReceiver(mRemoteViewsReceiver, filter);
    }

    private void updateUI(RemoteViews remoteViews) {
//        View view = remoteViews.apply(this, mRemoteViewsContent);
        int layoutId = getResources().getIdentifier("layout_simulated_notification", "layout", getPackageName());
        View view = getLayoutInflater().inflate(layoutId, mRemoteViewsContent, false);
        remoteViews.reapply(this, view);
        mRemoteViewsContent.addView(view);
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mRemoteViewsReceiver);
        super.onDestroy();
    }
    
    public void onButtonClick(View v) {
        if (v.getId() == R.id.notification_bar) {
            Intent intent = new Intent(this, ViewRemoteNotifyActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.send_simu_notification_bar) {
            Intent intent = new Intent(this, ViewRemoteSendSimulatedNotifyActivity.class);
            startActivity(intent);
        }
    }
}
