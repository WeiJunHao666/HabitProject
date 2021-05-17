package com.example.goodlife.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;


import androidx.annotation.Nullable;

import com.example.goodlife.Utils;

public class LinedTextView extends androidx.appcompat.widget.AppCompatTextView {

    private int sWidth;
    private int sHeight;

    public LinedTextView(Context context) {
        super(context);
    }

    public LinedTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LinedTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        setMeasuredDimension(sWidth, sHeight);
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void setWidthHeight(int width, int height){
        this.sWidth = width;
        this.sHeight = height;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        Paint mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(1);
        mPaint.setColor(Color.BLACK);

        int right = getRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int height = getHeight();
        int lineHeight = Utils.dip2px(getContext(),60);
        int count = (height - paddingTop - paddingBottom) / lineHeight;

        for (int i = 0; i < count; i++) {
            int baseline = lineHeight * (i + 1) + paddingTop;
            canvas.drawLine(0 + paddingLeft, baseline, right - paddingRight, baseline, mPaint);
        }
        super.onDraw(canvas);
    }
}

