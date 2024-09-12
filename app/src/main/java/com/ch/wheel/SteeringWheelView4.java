package com.ch.wheel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 链接: Android实现自定义方向盘-4解决触摸时指针跳跃的问题
 */
public class SteeringWheelView4 extends View {
    private Paint paint;
    private float centerX, centerY, radius;
    private float currentAngle = 0;
    private OnSteeringWheelChangeListener listener;

    private static final float MIN_DRAG_DISTANCE = 20f; // 最小拖拽距离阈值

    private float startX, startY;
    private boolean isDragging = false;
    private float initialAngleOffset = 0f;

    public SteeringWheelView4(Context context) {
        super(context);
        init();
    }

    public SteeringWheelView4(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SteeringWheelView4(Context context, AttributeSet attrs, int defStyleAttr) {
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
                // 记录初始点击位置和状态
                startX = x;
                startY = y;
                isDragging = false;

                // 计算触摸点与当前指针的角度偏移量
                initialAngleOffset = calculateAngle(x, y) - currentAngle;
                break;

            case MotionEvent.ACTION_MOVE:
                // 计算拖拽距离
                float deltaX = x - startX;
                float deltaY = y - startY;
                float distance = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);

                if (distance > MIN_DRAG_DISTANCE) {
                    isDragging = true;

                    // 计算当前触摸点的角度，并应用初始偏移量
                    float angle = calculateAngle(x, y) - initialAngleOffset;

                    // 更新方向盘角度
                    updateSteeringWheelAngle(angle);
                }
                break;

            case MotionEvent.ACTION_UP:
                // 在拖拽状态下结束触摸
                if (isDragging) {
                    // 可以在这里执行松手后的操作，例如回弹效果等
                }
                isDragging = false;
                break;
        }
        return true;
    }

    private float calculateAngle(float x, float y) {
        float angle = (float) Math.toDegrees(Math.atan2(centerY - y, centerX - x));
        if (angle < 0) angle += 360;
        return angle;
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