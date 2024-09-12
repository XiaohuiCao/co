package com.ch.wheel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Android实现自定义方向盘-2添加陀螺仪
 */
public class SteeringWheelView2 extends View {
    private Paint paint;
    private float centerX, centerY, radius;
    private float currentAngle = 0;
    private OnSteeringWheelChangeListener listener;

    public SteeringWheelView2(Context context) {
        super(context);
        init();
    }

    public SteeringWheelView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SteeringWheelView2(Context context, AttributeSet attrs, int defStyleAttr) {
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

        // 画一个指示方向的线
        float indicatorX = (float) (centerX + radius * Math.cos(Math.toRadians(currentAngle)));
        float indicatorY = (float) (centerY + radius * Math.sin(Math.toRadians(currentAngle)));
        paint.setColor(Color.RED);
        canvas.drawLine(centerX, centerY, indicatorX, indicatorY, paint);
        paint.setColor(Color.GRAY); // 恢复颜色
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

                updateSteeringWheelAngle(angle);
                break;

            case MotionEvent.ACTION_UP:
                // 触摸结束后的逻辑
                break;
        }
        return true;
    }

    public void updateSteeringWheelAngle(float angle) {
        currentAngle = angle % 360;
        if (currentAngle < 0) currentAngle += 360;

        if (listener != null) {
            listener.onSteeringWheelChanged(currentAngle);
        }
        invalidate();
    }

    public float getCurrentAngle() {
        return currentAngle;
    }

    public void setOnSteeringWheelChangeListener(OnSteeringWheelChangeListener listener) {
        this.listener = listener;
    }

    public interface OnSteeringWheelChangeListener {
        void onSteeringWheelChanged(float angle);
    }
}