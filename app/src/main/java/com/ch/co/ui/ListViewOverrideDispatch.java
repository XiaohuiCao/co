package com.ch.co.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * 场景2:存在同向滑动，同为竖向或者横向滑动，需判断为内部滑动还是外部滑动
 *
 * 解决方法：
 * 内部拦截机制，重写dispatchTouchEvent，
 * 当子元素调用requestDisallowInterceptTouchEvent(false)时，父元素才可继续拦截所需要的事件
 *
 * 因为ACTION_DOWN事件不受FLAG_DISALLOW_INTERCEPT标志位的控制，
 * 所以父容器不拦截down行为，调用requestDisallowInterceptTouchEvent(true)，
 * ACTION_MOVE中requestDisallowInterceptTouchEvent(false)，父容器可继续拦截
 */
public class ListViewOverrideDispatch extends ListView {
    private static final String TAG = "ListViewEx";

    private InterHorizontalScrollInterceptViewEx mInterView;

    // 分别记录上次滑动的坐标
    private int mLastX = 0;
    private int mLastY = 0;

    public ListViewOverrideDispatch(Context context) {
        super(context);
    }

    public ListViewOverrideDispatch(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListViewOverrideDispatch(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setInterInterceptView(InterHorizontalScrollInterceptViewEx interView) {
        mInterView = interView;
    }

    /**
     * 内部拦截机制，重写dispatchTouchEvent，
     * ACTION_MOVE中requestDisallowInterceptTouchEvent(false)，父容器可继续拦截
     * @param event
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
            mInterView.requestDisallowInterceptTouchEvent(true);
            break;
            }
            case MotionEvent.ACTION_MOVE: {
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                Log.d(TAG, "dx:" + deltaX + " dy:" + deltaY);
                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    mInterView.requestDisallowInterceptTouchEvent(false);
                }
                break;
            }
            case MotionEvent.ACTION_UP: {
                break;
            }
            default:
                break;
        }

        mLastX = x;
        mLastY = y;
        return super.dispatchTouchEvent(event);
    }
}
