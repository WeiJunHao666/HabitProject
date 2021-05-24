package com.example.goodlife.wjh.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class ItemRemindView extends View {

    private Paint lPaint;
    private Paint rPaint;

    public ItemRemindView(Context context) {
        super(context);
        init();
    }

    public ItemRemindView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ItemRemindView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(getWidth()/2, 0, getWidth()/2, getHeight()/2-20, lPaint);
        canvas.drawCircle(getWidth()/2, getHeight()/2, 20, rPaint);
        canvas.drawLine(getWidth()/2, getHeight()/2+20, getWidth()/2, getHeight(), lPaint);
    }

    private void init() {
        lPaint = new Paint();
        rPaint = new Paint();

        lPaint.setStrokeWidth(5);
        lPaint.setColor(Color.parseColor("#E0E0E0"));
        lPaint.setAntiAlias(true);

        rPaint.setStrokeWidth(5);
        rPaint.setColor(Color.parseColor("#E0E0E0"));
        rPaint.setAntiAlias(true);
        rPaint.setStyle(Paint.Style.FILL);
    }
}
