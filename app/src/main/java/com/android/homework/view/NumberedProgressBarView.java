package com.android.homework.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.android.homework.utils.ThreadUtils;

public class NumberedProgressBarView extends View {

    private static final int RECTANGLE_COUNT = 7;
    private static final int RECTANGLE_SPACING_DP = 2;

    private Paint mBackgroundPaint;
    private Paint mProgressPaint;
    private Paint mProgressNonePaint;
    private Paint mProgressRedPaint;
    private int mMaxNumber;
    private int mCurrentNumber;
    private float mRectangleWidth;
    private float mRectangleSpacing;
    private boolean mIsRedVisible;

    public NumberedProgressBarView(Context context) {
        super(context);
        init();
    }

    public NumberedProgressBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(Color.BLACK);
        mBackgroundPaint.setStyle(Paint.Style.FILL);

        mProgressPaint = new Paint();
        mProgressPaint.setColor(Color.parseColor("#17D9CD"));
        mProgressPaint.setStyle(Paint.Style.FILL);

        mProgressNonePaint = new Paint();
        mProgressNonePaint.setColor(Color.parseColor("#363636"));
        mProgressNonePaint.setStyle(Paint.Style.FILL);

        mProgressRedPaint = new Paint();
        mProgressRedPaint.setColor(Color.parseColor("#FFFB0303"));
        mProgressRedPaint.setStyle(Paint.Style.FILL);


        mMaxNumber = RECTANGLE_COUNT;
        mCurrentNumber = 0;

        float density = getResources().getDisplayMetrics().density;
        mRectangleSpacing = RECTANGLE_SPACING_DP * density;
        mIsRedVisible=false;
        startBlinkAnimation();
    }

    private void startBlinkAnimation() {
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);
        animator.setDuration(500);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                mIsRedVisible = value < 0.5f;
                invalidate();
            }
        });
        animator.start();
    }

    public void setNumber(int number) {
        mCurrentNumber = Math.max(0, Math.min(number, mMaxNumber));
        invalidate(); // 通知视图重绘
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        calculateRectangleWidth();
    }

    private void calculateRectangleWidth() {
        int availableWidth = getWidth() - getPaddingLeft() - getPaddingRight();
        mRectangleWidth = (availableWidth - mRectangleSpacing * (RECTANGLE_COUNT - 1)) / RECTANGLE_COUNT;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 绘制背景
        canvas.drawRect(0, 0, getWidth(), getHeight(), mBackgroundPaint);

        // 绘制矩形
        for (int i = 0; i < RECTANGLE_COUNT; i++) {
            float left = getPaddingLeft() + (mRectangleWidth + mRectangleSpacing) * i;
            float right = left + mRectangleWidth;
            float top = getPaddingTop();
            float bottom = getHeight() - getPaddingBottom();

            Paint paint = null;
            Log.d("ccm------>","i："+i+" ,mCurrentNumber: "+mCurrentNumber);

            if (i == 0 && 0 == mCurrentNumber && mIsRedVisible){
                paint = mProgressRedPaint;
            } else
                if (i < mCurrentNumber) {
                paint = mProgressPaint;
            } else {
                paint = mProgressNonePaint;
            }
            canvas.drawRect(left, top, right, bottom, paint);
        }
    }
}
