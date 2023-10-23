package com.android.homework.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * @auther 19062
 * @date 2023/9/20
 * @time 14:43.
 */


public class SwProgressBarView extends View {

    private static final int RECTANGLE_COUNT = 8;
    private static final int RECTANGLE_SPACING_DP = 2;

    private Paint mBackgroundPaint;
    private Paint mProgressPaint;
    private Paint mProgressNonePaint;

    private Paint mProgressRedPaint;
    private int mMaxNumber;
    private int mCurrentNumber;
    private float mRectangleWidth;
    private float mRectangleSpacing;


    public SwProgressBarView(Context context) {
        super(context);
        init();
    }

    public SwProgressBarView(Context context, AttributeSet attrs) {
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
            if (i < mCurrentNumber && i < 5) {
                paint = mProgressPaint;
            } else if(i >= RECTANGLE_COUNT - 5 && i+1 <= mCurrentNumber){
                paint = mProgressRedPaint;
            }
            else  {
                paint = mProgressNonePaint;
            }


            canvas.drawRect(left, top, right, bottom, paint);
        }
    }
}
