package com.example.goodlife.wjh.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class ItemView extends View {

    private Paint onPaint;
    private Paint rPaint;

    public ItemView(Context context) {
        super(context);
        init();
    }


    public ItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(getWidth()/2, 0, getWidth()/2, getHeight()/2-20, onPaint);
        canvas.drawCircle(getWidth()/2, getHeight()/2, 20, rPaint);
        canvas.drawLine(getWidth()/2, getHeight()/2+20, getWidth()/2, getHeight(), onPaint);
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
