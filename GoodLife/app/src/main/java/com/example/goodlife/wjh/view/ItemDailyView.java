package com.example.goodlife.wjh.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class ItemDailyView extends View {
    private Paint onPaint;
    private Paint rPaint;

    public ItemDailyView(Context context) {
        super(context);
        init();
    }


    public ItemDailyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ItemDailyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(getWidth()/2, 0, getWidth()/2, getHeight()/3-20, onPaint);
        canvas.drawCircle(getWidth()/2, getHeight()/3, 20, rPaint);
        canvas.drawLine(getWidth()/2, getHeight()/3+20, getWidth()/2, getHeight(), onPaint);
    }

    private void init() {

        onPaint = new Paint();
        rPaint = new Paint();

        onPaint.setStrokeWidth(5);
        onPaint.setColor(Color.BLACK);
        onPaint.setAntiAlias(true);

        rPaint.setStrokeWidth(10);
        rPaint.setColor(Color.BLACK);
        rPaint.setAntiAlias(true);
        rPaint.setStyle(Paint.Style.STROKE);

    }
}
