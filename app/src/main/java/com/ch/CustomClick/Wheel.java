package com.ch.CustomClick;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.ch.co.R;

public class Wheel extends View implements View.OnTouchListener {
    int xPosition;
    int yPosition;
    int centerX;
    int centerY;
    int mainRadius;
    int secondRadius;
    boolean isClicked;
    OnMyWheelMoveListener myWheelMoveListener;

    public Wheel(Context context) {
        super(context);
    }

    public Wheel(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Wheel(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        isClicked = false;
    }

    @Override

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.UNSPECIFIED ? 100 : MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.UNSPECIFIED ? 100 : MeasureSpec.getSize(heightMeasureSpec);
        if (width > height) {
            width = height;
        } else {
            height = width;
        }
        this.mainRadius = (getWidth() - 100) / 2;
        this.secondRadius = mainRadius / 5 * 2;
        setMeasuredDimension(width, height);
        this.centerX = getWidth() / 2;
        this.centerY = getHeight() / 2;
        this.xPosition = centerX;
        this.yPosition = centerY;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Bitmap bm;
        Paint BackgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        BackgroundPaint.setFilterBitmap(true);
        BackgroundPaint.setDither(true);

        Paint circlePaint = new Paint();
        circlePaint.setColor(Color.parseColor("#52c1bd"));
        circlePaint.setStyle(Paint.Style.FILL);
        if (!isClicked) {
            bm = ((BitmapDrawable) getResources().getDrawable(R.drawable.circle1)).getBitmap();
        } else {
            bm = ((BitmapDrawable) getResources().getDrawable(R.drawable.circle)).getBitmap();
        }
        Rect mSrcRect = new Rect(0, 0, bm.getWidth(), bm.getHeight());
        Rect mDestRect = new Rect(30, 30, getWidth() - 30, getHeight() - 30);
        canvas.drawBitmap(bm, mSrcRect, mDestRect, BackgroundPaint);
        canvas.drawCircle(this.xPosition, this.yPosition, secondRadius, circlePaint);

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        isClicked = true;
        this.xPosition = (int) event.getX();
        this.yPosition = (int) event.getY();
        if (Math.sqrt((this.xPosition - this.centerX) * (this.xPosition - this.centerX) + (this.yPosition - this.centerY) * (this.yPosition - this.centerY)) > mainRadius) {
            double Yrate = (this.yPosition - this.centerY) / Math.sqrt((this.xPosition - this.centerX) * (this.xPosition - this.centerX) + (this.yPosition - this.centerY) * (this.yPosition - this.centerY));
            double Xrate = (this.xPosition - this.centerX) / Math.sqrt((this.xPosition - this.centerX) * (this.xPosition - this.centerX) + (this.yPosition - this.centerY) * (this.yPosition - this.centerY));
            this.yPosition = (int) (mainRadius * Yrate) + this.centerY;
            this.xPosition = (int) (mainRadius * Xrate) + this.centerX;
        }
        if (this.myWheelMoveListener != null) {
            this.myWheelMoveListener.onValueChanged(this.xPosition, this.yPosition);
        }
        invalidate();

        if (event.getAction() == 1) {
            isClicked = false;
            this.yPosition = this.centerY;
            this.xPosition = this.centerX;
            if (this.myWheelMoveListener != null) {
                this.myWheelMoveListener.onValueChanged(this.xPosition, this.yPosition);
            }
            invalidate();
        }
        return true;
    }

    public void setOnMyWheelMoveListener(OnMyWheelMoveListener listener) {
        this.myWheelMoveListener = listener;
    }

    public static abstract interface OnMyWheelMoveListener {
        public abstract void onValueChanged(int xDistance, int yDistance);
    }
}