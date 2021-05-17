package com.example.goodlife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.goodlife.adapter.ColorGridViewAdapter;
import com.example.goodlife.adapter.IconGridViewAdapter;
import com.example.goodlife.kindview.MyGridView;

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
    private ImageView img_addRemind;

    private ColorGridViewAdapter colorAdapter;
    private IconGridViewAdapter iconAdapter;


    private String[] colors;
    private TypedArray imgs;
    private int[] time = {0, 1, 2, 3, 4, 5, 6};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_habit);

        colors = getResources().getStringArray(R.array.colors);
        imgs = getResources().obtainTypedArray(R.array.imgs);

        findViews();
        setGridViewAdapter(gridView_color, colors.length, 30, 1);
        setGridViewAdapter(gridView_icon, imgs.length(), 50, 0);
        setListenter();

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
        }
    }

    private void setCheckBoxBackground(CheckBox check){
        Log.e("ischecked()", check.isChecked()+"");
        if (check.isChecked()){
            Log.e("sa", "sss");
            check.setChecked(true);
            check.setBackgroundResource(R.drawable.background_time_checked);
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
                group.getChildAt(num).setBackgroundResource(R.drawable.background_time_checked);
                radioBtn = (RadioButton) group.getChildAt(num);
                radioBtn.setTextColor(Color.parseColor("#ffffff"));
            }else{
                group.getChildAt(i).setBackgroundResource(R.drawable.background_time_nocheck);
                radioBtn = (RadioButton) group.getChildAt(i);
                radioBtn.setTextColor(Color.parseColor("#111111"));
            }
        }
    }


    private void setGridViewAdapter(GridView gridView,  int size, int length, int flag) {

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
            colorAdapter = new ColorGridViewAdapter(this, colors, R.layout.item_gridview_color);
            gridView.setAdapter(colorAdapter);
        }else{
            iconAdapter = new IconGridViewAdapter(this, imgs, R.layout.item_gridview_icon);
            gridView.setAdapter(iconAdapter);
        }
    }


}