package com.ch.co.view345.remote;

import static com.ch.co.utils.MyConstants.REMOTE_VIEW_MSG_ID;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.ch.co.R;

/**
 *
 */
public class ViewRemoteNotifyActivity extends Activity implements OnClickListener {
    private static final String TAG = "ViewRemoteTestActivity";
    private Button mBtnNormalNotification;
    private View mBtnCustomNotification;
    private static int sId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_remote_notify_act);
        initView();
    }

    private void initView() {
        mBtnNormalNotification = (Button) findViewById(R.id.normal_notification);
        mBtnNormalNotification.setOnClickListener(this);
        mBtnCustomNotification = (TextView) findViewById(R.id.custom_notification);
        mBtnCustomNotification.setOnClickListener(this);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onClick(View view) {
        if (view == mBtnNormalNotification) {
            sId ++;
            Notification.Builder builder = setCustomNotificationBuilder();       // 设置通知
            NotificationChannel channel = setChannel();                         // 建立通道
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            builder.setChannelId(String.valueOf(sId));
            Notification notification = builder.build();
            manager.createNotificationChannel(channel);
            manager.notify(sId, notification);
        } else if (view == mBtnCustomNotification) {
            sId ++;
            Notification.Builder builder = setCustomNotificationBuilder();  // 设置通知
            RemoteViews remoteViews =getNotifyMsgContentView();             // 获取通知详情的 content view
            PendingIntent pendingIntent = getNotifyMsgContentIntent();      // 获取通知详情的 content intent
            builder.setContentIntent(pendingIntent);
            NotificationChannel channel= setChannel();                      // 建立通道
            NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
            Notification notification = builder.build();
            notification.contentView = remoteViews;
            notification.contentIntent = pendingIntent;
            manager.createNotificationChannel(channel);
            manager.notify(sId, notification);
        }
    }

    private Notification.Builder setCustomNotificationBuilder() {
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle("coco 标题")
                .setContentText("coco 内容")
                .setSmallIcon(R.drawable.star)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setChannelId(String.valueOf(sId));
        return builder;
    }

    private RemoteViews getNotifyMsgContentView() {
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.custom_layout_notification);
        remoteViews.setTextViewText(R.id.custom_notification_msg_up, "custom remote id: " + sId);
        remoteViews.setImageViewResource(R.id.notify_small_icon, R.drawable.snow);
        PendingIntent openMsgContentView = PendingIntent.getActivity(this,
                0, new Intent(this, ViewRemoteSendSimulatedNotifyActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.custom_notification_msg_down, openMsgContentView);
        return remoteViews;
    }

    private PendingIntent getNotifyMsgContentIntent() {
        Intent intent = new Intent(this, ViewRemoteContentIntentAct.class);
        intent.putExtra(REMOTE_VIEW_MSG_ID, "" + sId);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        System.out.println(pendingIntent);
        return pendingIntent;
    }

    private NotificationChannel setChannel() {
        NotificationChannel channel = new NotificationChannel(String.valueOf(sId), "coco_custom_channel",
                NotificationManager.IMPORTANCE_DEFAULT);
        channel.enableLights(true);
        channel.setLightColor(Color.green(1));
        channel.setShowBadge(true);

        return channel;
    }
}
