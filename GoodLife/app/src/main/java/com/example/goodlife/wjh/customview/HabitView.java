package com.example.goodlife.wjh.customview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;


public class HabitView extends View {

    private Paint paint;
    private Path path;
    private static int COLOR = Color.GREEN;
    private static int WIDTH = 6;
    private static int SPEED = 500;//圆弧绘制的速度
    private float progress;//圆弧的度数

    public HabitView(Context context) {
        super(context);
        init();
    }

    public HabitView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HabitView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(COLOR);
        int center = getWidth() / 2;
        int radius = center - WIDTH/2;
        RectF rectF = new RectF(center-radius, center-radius, center+radius, center+radius);
        canvas.drawArc(rectF, -90, progress,false, paint);
        canvas.drawPath(path, paint);

    }

    private void init() {
        paint = new Paint();
        path =  new Path();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(6);
        paint.setAntiAlias(true);
    }

    public void showAnim(){
        path.reset();
        ValueAnimator animCircle = ValueAnimator.ofFloat(0f, 360f);
        animCircle.setInterpolator(new LinearInterpolator());
        animCircle.setDuration(SPEED);
        animCircle.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                progress = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        animCircle.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                hookAnimation();
            }
        });
        animCircle.start();
    }

    private void hookAnimation() {

        path.moveTo(getWidth()/4, getHeight()/2);
        ValueAnimator animHook = ValueAnimator.ofFloat(0, 6.6f);
        animHook.setInterpolator(new LinearInterpolator());
        animHook.setDuration(SPEED);
        animHook.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float progress = (float) animation.getAnimatedValue();
                if(progress<4){
                    path.rLineTo(progress, progress);
                }else{
                    path.rLineTo(progress, -progress+1f);
                }
                postInvalidate();
            }
        });
        animHook.start();
    }


}
