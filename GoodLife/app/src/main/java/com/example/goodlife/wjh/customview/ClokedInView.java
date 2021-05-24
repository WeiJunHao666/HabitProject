package com.example.goodlife.wjh.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.goodlife.Utils;

public class ClokedInView extends View {

    private Paint paint;
    private Path path;

    private static int COLOR = Color.GREEN;
    private static int WIDTH = 6;

    public ClokedInView(Context context) {
        super(context);
        init();
    }

    public ClokedInView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ClokedInView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        path = new Path();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(6);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(COLOR);
        int center = getWidth() / 2;
        int radius = center - WIDTH/2;
        RectF rectF = new RectF(center-radius, center-radius, center+radius, center+radius);
        canvas.drawArc(rectF, -90, 360,false, paint);
        canvas.drawPath(path, paint);

        //path.reset();
        path.moveTo(getWidth()/4, getHeight()/2);
        path.rLineTo(40,40);
        path.rLineTo(66, -56);
        canvas.drawPath(path, paint);
    }
}
