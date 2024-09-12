package com.ch.wheel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Android实现自定义方向盘
 */
public class SteeringWheelView1 extends View {
    private Paint paint;
    private float centerX, centerY, radius;
    private OnSteeringWheelChangeListener listener;

    public SteeringWheelView1(Context context) {
        super(context);
        init();
    }

    public SteeringWheelView1(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SteeringWheelView1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        centerX = getWidth() / 2;
        centerY = getHeight() / 2;
        radius = Math.min(centerX, centerY) - 20;
        canvas.drawCircle(centerX, centerY, radius, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                // 计算触摸点相对方向盘中心的角度
                float angle = (float) Math.toDegrees(Math.atan2(centerY - y, centerX - x));
                if (angle < 0) angle += 360;

                // 通知监听器角度变化
                if (listener != null) {
                    listener.onSteeringWheelChanged(angle);
                }
                invalidate();
                break;

            case MotionEvent.ACTION_UP:
                // 触摸结束，方向盘回到初始位置或其他逻辑
                break;
        }
        return true;
    }

    // 设置角度变化监听器
    public void setOnSteeringWheelChangeListener(OnSteeringWheelChangeListener listener) {
        this.listener = listener;
    }

    public interface OnSteeringWheelChangeListener {
        void onSteeringWheelChanged(float angle);
    }
}