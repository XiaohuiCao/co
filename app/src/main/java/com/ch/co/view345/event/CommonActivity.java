package com.ch.co.view345.event;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.ch.co.R;

public class CommonActivity extends Activity implements OnClickListener, OnLongClickListener {

    private static final String TAG = "CommonActivity";
    private static final int MESSAGE_SCROLL_TO = 1;
    private static final int FRAME_COUNT = 30;
    private static final int DELAYED_TIME = 33;

    private Button mBtnMove;
    private View mBtnScroll;
    private int mCount = 0;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
            case MESSAGE_SCROLL_TO: {
                mCount++;
                if (mCount <= FRAME_COUNT) {
                    float fraction = mCount / (float) FRAME_COUNT;
                    int scrollX = (int) (fraction * 100);
                    mBtnMove.scrollTo(scrollX, 0);
                    mHandler.sendEmptyMessageDelayed(MESSAGE_SCROLL_TO, DELAYED_TIME);
                }
                break;
            }
            default:
                break;
            }
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_event_activity_common);
        initView();
    }

    private void initView() {
        mBtnMove = findViewById(R.id.moving_button);
        mBtnMove.setOnClickListener(this);
        mBtnScroll = findViewById(R.id.scroll_button);
        mBtnScroll.setOnLongClickListener(this);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Log.d(TAG, "button1.left=" + mBtnMove.getLeft());
        Log.d(TAG, "button1.x=" + mBtnMove.getX());
    }

    @Override
    public void onClick(View v) {
        if (v == mBtnMove) {
//             mBtnMove.setTranslationX(100);
//             Log.d(TAG, "button1.left=" + mBtnMove.getLeft());
//             Log.d(TAG, "button1.x=" + mBtnMove.getX());
//             ObjectAnimator.ofFloat(mBtnMove, "translationX", 0, 100)
//             .setDuration(1000).start();
//             ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) mBtnMove
//             .getLayoutParams();
//             params.width += 100;
//             params.leftMargin += 100;
//             mBtnMove.requestLayout();
//             mBtnMove.setLayoutParams(params);
//
//             final int startX = 0;
//             final int deltaX = 100;
//             ValueAnimator animator = ValueAnimator.ofInt(0,
//             1).setDuration(1000);
//             animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//             @Override
//             public void onAnimationUpdate(ValueAnimator animator) {
//             float fraction = animator.getAnimatedFraction();
//             mBtnMove.scrollTo(startX + (int) (deltaX * fraction), 0);
//             }
//             });
//             animator.start();

            mHandler.sendEmptyMessageDelayed(MESSAGE_SCROLL_TO, DELAYED_TIME);
        }
    }

    @Override
    public boolean onLongClick(View v) {
        Toast.makeText(this, "long click", Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        super.onStart();
    }
}
