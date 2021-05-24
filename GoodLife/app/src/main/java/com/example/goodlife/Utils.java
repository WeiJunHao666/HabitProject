package com.example.goodlife;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.example.goodlife.wjh.bean.ResponseCode;
import com.example.goodlife.wjh.bean.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static android.content.Context.MODE_PRIVATE;
import static com.example.goodlife.R.drawable.background_circular;
import static com.example.goodlife.R.drawable.cal;

public class Utils {

    public static final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();
    private static TypedArray imgs;
    private static String[] colors;
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
        imgs = context.getResources().obtainTypedArray(resId);
        return imgs.getResourceId(index, -1);
    }
    /**
     * 获得background的颜色
     */
    public static String getColor(Context context, int resId, int index){
        colors = context.getResources().getStringArray(resId);
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
     * 获得当前时间（小时：分钟）
     */
    public static String getCurrentTime(){

        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
        Calendar cal= Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");

        return dateFormat.format(cal.getTime())+"";
    }

    /**
     * 获取当前时间(年月日：小时：分钟)
     * @return
     */
    public static Date getCurrentAllTime(){
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
        Calendar cal= Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return cal.getTime();
    }
    /**
     * 设置选中的背景颜色(方形)
     */
    public static void setBackgroundChecked(Context context, String color, View view, float radius){
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setColor(Color.parseColor(color));
        drawable.setStroke(dip2px(context, 2), Color.BLACK);
        drawable.setCornerRadius(dip2px(context, radius));
        //drawable.setSize(60,40);
        view.setBackground(drawable);
    }
    /**
     *设置选中的背景颜色(圆形)
     */
    public static void setBackgroundCircle(Context context, String color, View view){
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.OVAL);
        drawable.setStroke(Utils.dip2px(context,2), Color.BLACK);
        drawable.setColor(Color.parseColor(color));
        view.setBackground(drawable);
    }
    /**
     *
     */
    public static boolean isSuccess(int code){
        if(code<3000){
            return true;
        }
        return false;
    }
    /**
     * 返回状态码对应信息
     */
    public static String info(int code){
        String str = null;
        switch(code){
            case ResponseCode.SIGN_IN_SUCCESS:
                str = "登陆成功";
                break;
            case ResponseCode.SIGN_UP_SUCCESS:
                str = "注册成功";
                break;
            case ResponseCode.UPDATE_SUCCESS:
                str = "修改成功";
                break;
            case ResponseCode.DELETE_SUCCESS:
                str = "删除成功";
                break;
            case ResponseCode.CLOCK_SUCCESS:
                str = "打卡成功";
                break;
            case ResponseCode.CREATE_SUCCESS:
                str = "创建成功";
                break;
            case ResponseCode.SIGN_IN_FAILED:
                str = "登陆失败";
                break;
            case ResponseCode.SIGN_UP_FAILED:
                str = "注册失败，账号已存在";
                break;
            case ResponseCode.UPDATE_FAILED:
                str = "修改失败";
                break;
            case ResponseCode.DELETE_FAILED:
                str = "删除失败";
                break;
            case ResponseCode.CREATE_FAILED:
                str = "创建失败";
                break;
            case ResponseCode.CLOCK_FAILED:
                str = "打卡失败";
                break;
        }
        return str;
    }

    /**
     * 保存用户信息
     * @param context
     * @param data
     */

    public static void save(Context context, String data){
        User info = new Gson().fromJson(data, User.class);
        //可以创建一个新的SharedPreference来对储存的文件进行操作
        SharedPreferences sp = context.getSharedPreferences("data", MODE_PRIVATE);
        //像SharedPreference中写入数据需要使用Editor
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("userId", info.getUserId());
        editor.putString("account", info.getAccount());
        editor.putString("pwd", info.getPwd());
        editor.putString("username", info.getUsername());

        editor.commit();
    }
    /**
     * 获得用户Id
     */
    public static int getUserId(Context context){
        SharedPreferences sp = context.getSharedPreferences("data",MODE_PRIVATE);
        int id = sp.getInt("userId", 0);
        return id;
    }
}
