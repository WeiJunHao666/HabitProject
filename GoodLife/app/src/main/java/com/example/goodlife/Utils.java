package com.example.goodlife;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import static com.example.goodlife.R.drawable.background_circular;

public class Utils {

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 获取屏幕信息
     */
    public static DisplayMetrics getDisplayMetrics(Context context){
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return metrics;
    }

    /**
     * 获得icon的Id
     */
    public static int getImageId(Context context, int resId, int index){
        TypedArray imgs = context.getResources().obtainTypedArray(resId);
        return imgs.getResourceId(index, -1);
    }
    /**
     * 获得background的颜色
     */
    public static String getColor(Context context, int resId, int index){
        String[] colors = context.getResources().getStringArray(resId);
        return colors[index];
    }

    /**
     * 设置背景颜色
     */
    public static void setBackground(Context context, int resId, View view, String color, String color2, int width){

        GradientDrawable background = (GradientDrawable) context.getResources().getDrawable(resId);
        background.setColor(Color.parseColor(color));
        background.setStroke(width, Color.parseColor(color2));

        view.setBackgroundResource(resId);
    }
    /**
     * 获得当前时间
     */
    public static String getCurrentTime(){

        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
        Calendar cal= Calendar.getInstance();
        SimpleDateFormat dateFormat= new SimpleDateFormat("HH:mm");

        return dateFormat.format(cal.getTime())+"";
    }
}
