package com.ch.wheel;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

/**
 * Android实现自定义方向盘-5livedata实现
 */
public class SteeringWheelView5 extends View {
    private static final float MIN_DRAG_DISTANCE = 20f; // 最小拖拽距离阈值
    private float centerX, centerY, radius;
    private float initialAngleOffset = 0f;
    private float initialAngle = 0f;
    private float startX, startY;
    private boolean isDragging = false;
    private Paint paint;
    private MutableLiveData<Float> angleLiveData = new MutableLiveData<>();
    private MutableLiveData<Integer> countLivaData = new MutableLiveData<>();

    int count = 0;

    public SteeringWheelView5(Context context) {
        super(context);
        init();
    }

    public SteeringWheelView5(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SteeringWheelView5(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);

        // 设置初始角度为 0 度
        angleLiveData.setValue(initialAngle);
        countLivaData.setValue(0);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        centerX = getWidth() / 2;
        centerY = getHeight() / 2;
        radius = Math.min(centerX, centerY) - 20;
        canvas.drawCircle(centerX, centerY, radius, paint);

        // 画一个指示方向的线
        Float angle = angleLiveData.getValue();
        if (angle != null) {
            float indicatorX = (float) (centerX + radius * Math.cos(Math.toRadians(angle)));
            float indicatorY = (float) (centerY + radius * Math.sin(Math.toRadians(angle)));
            paint.setColor(Color.RED);
            canvas.drawLine(centerX, centerY, indicatorX, indicatorY, paint);
            paint.setColor(Color.GRAY); // 恢复颜色
        }
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
                Float currentAngle = angleLiveData.getValue();
                if (currentAngle != null) {
                    initialAngleOffset = calculateAngle(x, y) - currentAngle;
                }
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
                if (isDragging) {
                    // 当松手时，启动动画将指针恢复到初始位置
//                    resetSteeringWheelAngle();
                }
                isDragging = false;
                count = 0;
        }
        return true;
    }

    private void resetSteeringWheelAngle() {
        Float currentAngle = angleLiveData.getValue();
        if (currentAngle != null) {
            ValueAnimator animator = ValueAnimator.ofFloat(currentAngle, initialAngle);
            animator.setDuration(800); // 动画持续时间，500ms
            animator.addUpdateListener(animation -> {
                float animatedValue = (float) animation.getAnimatedValue();
                updateSteeringWheelAngle(animatedValue);
                System.out.println("--------调用resetSteeringWheelAngle的次数" + count++);
                countLivaData.setValue(count);
            });
            animator.start();
        }
    }

    private float calculateAngle(float x, float y) {
        float angle = (float) Math.toDegrees(Math.atan2(centerY - y, centerX - x));
        if (angle < 0) angle += 360;
        return angle;
    }

    public void updateSteeringWheelAngle(float angle) {
        angle = angle % 360;
        if (angle < 0) angle += 360;
        angleLiveData.setValue(angle);
        invalidate();
    }

    public void resetSteeringWheelAngle(float angle) {
        angle = angle % 360;
        if (angle > 180) angle -= 180;
        angleLiveData.setValue(angle);
        invalidate();
    }


    public LiveData<Float> getAngleLiveData() {
        return angleLiveData;
    }

    public LiveData<Integer> getCountLiveData() {
        return countLivaData;
    }
}