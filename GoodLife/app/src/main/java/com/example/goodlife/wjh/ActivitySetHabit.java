package com.example.goodlife.wjh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.goodlife.R;
import com.example.goodlife.Utils;
import com.example.goodlife.wjh.adapter.ColorGridViewAdapter;
import com.example.goodlife.wjh.adapter.IconGridViewAdapter;
import com.example.goodlife.wjh.kindview.MyGridView;

import java.util.Random;

import static com.example.goodlife.R.drawable.background_time_checked;

public class ActivitySetHabit extends AppCompatActivity implements View.OnClickListener {

    private MyGridView gridView_color;
    private MyGridView gridView_icon;
    private RadioGroup group_time;
    private LinearLayout group_week;
    private RadioButton radioBtn;
    private CheckBox checkMon;
    private CheckBox checkTue;
    private CheckBox checkWed;
    private CheckBox checkThu;
    private CheckBox checkFri;
    private CheckBox checkSat;
    private CheckBox checkSun;
    private CheckBox checkBox;
    private LinearLayout bg_setHabit;
    private ImageView img_setHabit;
    private ImageView img_addRemind;
    private ImageView img_return;
    private ImageView img_save;
    private Context context = this;

    private ColorGridViewAdapter colorAdapter;
    private IconGridViewAdapter iconAdapter;


    private String[] colors;
    private TypedArray imgs;
    private boolean[] flags;
    private boolean[] flag2s;
    private String color;
    private int[] time = {0, 1, 2, 3, 4, 5, 6};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_habit);

        //绑定控件
        findViews();
        //初始化数据
        initData();
        //给颜色和图标设置适配器
        setGridViewAdapter(gridView_color, colors.length, 30, 1);
        setGridViewAdapter(gridView_icon, imgs.length(), 50, 0);
        //设置监听器
        setListenter();

    }
    private void initData(){

        //设置随机数
        Random rand = new Random();
        int randNum = rand.nextInt(25);

        colors = getResources().getStringArray(R.array.colors);
        imgs = getResources().obtainTypedArray(R.array.imgs);
        color = Utils.getColor(this, R.array.colors, randNum);

        flags = new boolean[colors.length];
        flag2s = new boolean[imgs.length()];

        for(int i=0; i<colors.length; i++){
            flags[i] = false;
        }

        for(int i=0; i<imgs.length(); i++){
            flag2s[i] = false;
        }
        //设置第一个被选中
        flag2s[0] = true;
        //初始化背景颜色
        setBackgroundInit();
        //设置icon初始背景
        Utils.setBackgroundCircle(context, color, bg_setHabit);

    }

    private void findViews() {
        gridView_color = findViewById(R.id.gridView_color);
        gridView_icon = findViewById(R.id.gridView_icon);
        group_time = findViewById(R.id.radioGroup_time);
        group_week = findViewById(R.id.checkBox_week);
        checkMon = findViewById(R.id.checkBtn_Mon);
        checkTue = findViewById(R.id.checkBtn_Tue);
        checkWed = findViewById(R.id.checkBtn_Wed);
        checkThu = findViewById(R.id.checkBtn_Thu);
        checkFri = findViewById(R.id.checkBtn_Fri);
        checkSat = findViewById(R.id.checkBtn_Sat);
        checkSun = findViewById(R.id.checkBtn_Sun);
        img_addRemind = findViewById(R.id.img_addRemind);
        img_setHabit = findViewById(R.id.img_setHabit);
        img_return = findViewById(R.id.img_set_return);
        img_save = findViewById(R.id.img_set_save);
        bg_setHabit = findViewById(R.id.bg_setHabit);

    }

    private void setListenter() {

        group_time.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioBtn_any:
                        setRadioCheckedBackground(0, group);
                        break;
                    case R.id.radioBtn_up:
                        setRadioCheckedBackground(1, group);
                        break;
                    case R.id.radioBtn_mor:
                        setRadioCheckedBackground(2, group);
                        break;
                    case R.id.radioBtn_noon:
                        setRadioCheckedBackground(3, group);
                        break;
                    case R.id.radioBtn_evening:
                        setRadioCheckedBackground(4, group);
                        break;
                    case R.id.radioBtn_night:
                        setRadioCheckedBackground(5, group);
                        break;
                    case R.id.radioBtn_bed:
                        setRadioCheckedBackground(6, group);
                        break;
                }
            }
        });

        checkMon.setOnClickListener(this);
        checkTue.setOnClickListener(this);
        checkWed.setOnClickListener(this);
        checkThu.setOnClickListener(this);
        checkFri.setOnClickListener(this);
        checkSat.setOnClickListener(this);
        checkSun.setOnClickListener(this);
        img_addRemind.setOnClickListener(this);
        img_return.setOnClickListener(this);
        img_save.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.checkBtn_Mon:
                setCheckBoxBackground(checkMon);
                Log.e("1", checkMon.isChecked()+"");
                break;
            case R.id.checkBtn_Tue:
                setCheckBoxBackground(checkTue);
                Log.e("2", checkTue.isChecked()+"");
                break;
            case R.id.checkBtn_Wed:
                setCheckBoxBackground(checkWed);
                Log.e("3", checkWed.isChecked()+"");
                break;
            case R.id.checkBtn_Thu:
                setCheckBoxBackground(checkThu);
                Log.e("4", checkThu.isChecked()+"");
                break;
            case R.id.checkBtn_Fri:
                setCheckBoxBackground(checkFri);
                Log.e("5", checkFri.isChecked()+"");
                break;
            case R.id.checkBtn_Sat:
                setCheckBoxBackground(checkSat);
                Log.e("6", checkSat.isChecked()+"");
                break;
            case R.id.checkBtn_Sun:
                setCheckBoxBackground(checkSun);
                Log.e("7", checkSun.isChecked()+"");
                break;
            case R.id.img_addRemind:
                Intent intent = new Intent(getApplicationContext(), ActivityRemind.class);
                startActivity(intent);
                break;
            case R.id.img_set_return:
                finish();
                break;
            case R.id.img_set_save:
                break;
        }
    }

    private void setCheckBoxBackground(CheckBox check){
        Log.e("ischecked()", check.isChecked()+"");
        if (check.isChecked()){
            Log.e("sa", "sss");
            check.setChecked(true);
            Utils.setBackgroundChecked(context, color, check);
            check.setTextColor(Color.parseColor("#ffffff"));
        }else{
            Log.e("sa2", "sss");
            check.setChecked(false);
            check.setBackgroundResource(R.drawable.background_time_nocheck);
            check.setTextColor(Color.parseColor("#111111"));
        }
    }


    private void setRadioCheckedBackground(int num, ViewGroup group){
        for(int i=0; i<time.length; i++){
            if (time[i] == num){
                Utils.setBackgroundChecked(context, color, group.getChildAt(num));
                radioBtn = (RadioButton) group.getChildAt(num);
                radioBtn.setTextColor(Color.parseColor("#ffffff"));
            }else{
                group.getChildAt(i).setBackgroundResource(R.drawable.background_time_nocheck);
                radioBtn = (RadioButton) group.getChildAt(i);
                radioBtn.setTextColor(Color.parseColor("#111111"));
            }
        }
    }


    private void setGridViewAdapter(GridView gridView, int size, int length, final int flag) {

        int columns;
        if(size%3==0){
            columns = size/3;
        }else{
            columns = size/3 + 1;
        }

        int gridviewWidth = (int) (columns * (length+20));
        int itemWidth = (int) (length + 20);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                Utils.dip2px(getBaseContext(),gridviewWidth), LinearLayout.LayoutParams.MATCH_PARENT);
        gridView.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
        gridView.setColumnWidth(Utils.dip2px(getBaseContext(), itemWidth)); // 设置列表项宽
        gridView.setStretchMode(GridView.NO_STRETCH);
        gridView.setNumColumns(columns); // 设置列数量=列表集合数

        if(flag == 1){
            colorAdapter = new ColorGridViewAdapter(this, colors, flags, R.layout.item_gridview_color);
            gridView.setAdapter(colorAdapter);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
                    if(flags[position] == false){

                        //icon更换颜色
                        color = Utils.getColor(context, R.array.colors, position);
                        Utils.setBackgroundCircle(context, color, bg_setHabit);

                        //time和week选中按钮更换颜色
                        GradientDrawable background = (GradientDrawable) context.getResources().getDrawable(background_time_checked);
                        background.setColor(Color.parseColor(color));
                        //设置背景颜色
                        setBackgroundInit();

                        //设置只能选中一个item
                        for(int i=0; i<flags.length; i++){
                            if(flags[i] == true ){
                                flags[i] = false;
                            }
                        }
                        flags[position] = true;
                        colorAdapter.notifyDataSetChanged();

                        //icon背景色变换联动
                        iconAdapter = new IconGridViewAdapter(context, imgs, flag2s, color, R.layout.item_gridview_icon);
                        gridView_icon.setAdapter(iconAdapter);

                    }
                }
            });
        }else{
            iconAdapter = new IconGridViewAdapter(this, imgs, flag2s, color, R.layout.item_gridview_icon);
            gridView.setAdapter(iconAdapter);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    //更换icon
                    img_setHabit.setImageResource(imgs.getResourceId(position, -1));

                    //设置只能选中一个item
                    if(flag2s[position] == false){

                        for(int i=0; i<flag2s.length; i++){
                            if(flag2s[i] == true ){
                                flag2s[i] = false;
                            }
                        }
                        flag2s[position] = true;
                        iconAdapter.notifyDataSetChanged();
                    }

                }
            });
        }
    }

    private void setBackgroundInit(){
        for(int i=0; i<7; i++){
            Log.e("执行", "true");
            radioBtn = (RadioButton) group_time.getChildAt(i);
            checkBox = (CheckBox) group_week.getChildAt(i);
            if(radioBtn.isChecked()){
                Utils.setBackgroundChecked(context, color, radioBtn);
            }
            if(checkBox.isChecked()){
                Utils.setBackgroundChecked(context, color, checkBox);
            }
        }
    }


}