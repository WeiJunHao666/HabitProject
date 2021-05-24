package com.example.goodlife.wjh.sethabit;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.goodlife.ConfigUtil;
import com.example.goodlife.R;
import com.example.goodlife.Utils;
import com.example.goodlife.wjh.ActivityRemind;
import com.example.goodlife.wjh.MyView;
import com.example.goodlife.wjh.adapter.ColorGridViewAdapter;
import com.example.goodlife.wjh.adapter.IconGridViewAdapter;
import com.example.goodlife.wjh.bean.Habit;
import com.example.goodlife.wjh.bean.RemindTimes;
import com.example.goodlife.wjh.bean.ResponseBody;
import com.example.goodlife.wjh.kindview.MyGridView;
import com.example.goodlife.wjh.sethabit.SetHabitPresenter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import static com.example.goodlife.R.drawable.background_time_checked;

public class ActivitySetHabit extends AppCompatActivity implements View.OnClickListener, MyView {

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
    private EditText ed_name;
    private EditText ed_word;
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
    private int pos1 = 0;
    private int pos2 = 0;
    private String color;
    private int colorIndex;
    private int icon;
    private String name;
    private int habitId;
    private int flag;
    private ArrayList<RemindTimes> list;
    private int[] time = {0, 1, 2, 3, 4, 5, 6};

    private Habit habit;
    private SetHabitPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_habit);

        //绑定控件
        findViews();

        Intent intent = getIntent();
        flag = intent.getIntExtra("flag", -1);

        if(flag==0){
            colorIndex = intent.getIntExtra("color", -1);
            icon = intent.getIntExtra("icon", -1);
            name = intent.getStringExtra("name");
            habitId = intent.getIntExtra("habitId", -1);
            list = (ArrayList<RemindTimes>) intent.getSerializableExtra("list");
            //初始化数据
            initData(colorIndex, icon, name);
        }else{
            list = new ArrayList<>();
            //初始化数据
            initData(0, 0, "");
        }
        //给颜色和图标设置适配器
        setGridViewAdapter(gridView_color, colors.length, 30, 1);
        setGridViewAdapter(gridView_icon, imgs.length(), 50, 0);
        //设置监听器
        setListenter();

    }
    private void initData(int colorIndex, int icon, String name){

        //设置随机数
//        Random rand = new Random();
//        int randNum = rand.nextInt(25);
        habit = new Habit();
        colors = getResources().getStringArray(R.array.colors);
        imgs = getResources().obtainTypedArray(R.array.imgs);
        color = colors[colorIndex];
        presenter = new SetHabitPresenter(this);

        flags = new boolean[colors.length];
        flag2s = new boolean[imgs.length()];

        for(int i=0; i<colors.length; i++){
            flags[i] = false;
        }

        for(int i=0; i<imgs.length(); i++){
            flag2s[i] = false;
        }
        //设置第一个被选中
//        flag2s[0] = true;
//        flags[0] = true;
        //初始化time、week背景颜色
        setCheckedBackground();
        //设置icon初始背景
        Utils.setBackgroundCircle(context, color, bg_setHabit);
        img_setHabit.setImageResource(imgs.getResourceId(icon, -1));
        ed_name.setText(name);

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
        ed_name = findViewById(R.id.edit_name);
        ed_word = findViewById(R.id.edit_words);
        bg_setHabit = findViewById(R.id.bg_setHabit);

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

    }
    //复选框点击事件要注意！！！
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.checkBtn_Mon:
                setCheckBoxBackground(checkMon);
                //Log.e("1", checkMon.isChecked()+"");
                break;
            case R.id.checkBtn_Tue:
                setCheckBoxBackground(checkTue);
                //Log.e("2", checkTue.isChecked()+"");
                break;
            case R.id.checkBtn_Wed:
                setCheckBoxBackground(checkWed);
                //Log.e("3", checkWed.isChecked()+"");
                break;
            case R.id.checkBtn_Thu:
                setCheckBoxBackground(checkThu);
                //Log.e("4", checkThu.isChecked()+"");
                break;
            case R.id.checkBtn_Fri:
                setCheckBoxBackground(checkFri);
                //Log.e("5", checkFri.isChecked()+"");
                break;
            case R.id.checkBtn_Sat:
                setCheckBoxBackground(checkSat);
                //Log.e("6", checkSat.isChecked()+"");
                break;
            case R.id.checkBtn_Sun:
                setCheckBoxBackground(checkSun);
                //Log.e("7", checkSun.isChecked()+"");
                break;
            case R.id.img_addRemind:

                Intent intent = new Intent(this,ActivityRemind.class);
                intent.putExtra("list", (Serializable) list);
                startActivityForResult(intent, 1);
                break;
            case R.id.img_set_return:
                finish();
                break;
            case R.id.img_set_save:
                habit.setName(String.valueOf(ed_name.getText()));
                habit.setEncourageWords(String.valueOf(ed_word.getText()));
                habit.setColor(pos1);
                habit.setIcon(pos2);
                habit.setTimeOfDay(whoRadChecked());
                habit.setDayOfWeek(whoCheckChecked());
                habit.setContinueDays(0);
                habit.setMaxContinueDays(0);
                habit.setKeepDays(0);
                Date date = new Date();

                //json解析Date类要给json设置一下Date的格式（Utils类里）
                habit.setCreateDate(new Date());
                //flag=0 twopage的编辑
                if(flag == 0){

                }else{
                    presenter.setHabit(ConfigUtil.CREATE+Utils.getUserId(this), habit);
                }

                break;
        }
    }

    private void setGridViewAdapter(GridView gridView, int size, int length, final int flag) {


        int columns = size%3==0 ? size/3: size/3+1;
        int gridviewWidth = (int) (columns * (length+20));
        int itemWidth = (int) (length + 20);

        //设置gridview属性
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                Utils.dip2px(getBaseContext(),gridviewWidth), LinearLayout.LayoutParams.MATCH_PARENT);
        gridView.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
        gridView.setColumnWidth(Utils.dip2px(getBaseContext(), itemWidth)); // 设置列表项宽
        gridView.setStretchMode(GridView.NO_STRETCH);
        gridView.setNumColumns(columns); // 设置列数量=列表集合数

        //flag=1设置颜色的gridview
        if(flag == 1){

            colorAdapter = new ColorGridViewAdapter(this, colors, flags, R.layout.item_gridview_color);
            gridView.setAdapter(colorAdapter);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //如果该item没有被选中(没有设置选中的效果)
                    if(flags[position] == false){

                        //记录该item的position
                        pos1 = position;
                        //icon、（time选中、week选中）背景更换颜色
                        color = Utils.getColor(context, R.array.colors, position);
                        Utils.setBackgroundCircle(context, color, bg_setHabit);

                        GradientDrawable background = (GradientDrawable) context.getResources().getDrawable(background_time_checked);
                        background.setColor(Color.parseColor(color));
                        setCheckedBackground();

                        //设置只能选中一个item
                        for(int i=0; i<flags.length; i++){
                            if(flags[i] == true ){
                                flags[i] = false;
                            }
                        }
                        //设置position位置被选中，刷新adapter
                        flags[position] = true;
                        colorAdapter.notifyDataSetChanged();
                        //icon背景色同步color
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
                        pos2 = position;
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

    private void setRadioCheckedBackground(int num, ViewGroup group){
        for(int i=0; i<time.length; i++){
            if (time[i] == num){
                Utils.setBackgroundChecked(context, color, group.getChildAt(num), 8);
                radioBtn = (RadioButton) group.getChildAt(num);
                radioBtn.setTextColor(Color.parseColor("#ffffff"));
            }else{
                group.getChildAt(i).setBackgroundResource(R.drawable.background_time_nocheck);
                radioBtn = (RadioButton) group.getChildAt(i);
                radioBtn.setTextColor(Color.parseColor("#111111"));
            }
        }
    }

    private void setCheckBoxBackground(CheckBox check){

        if (check.isChecked()){
            Utils.setBackgroundChecked(context, color, check, 8);
            check.setTextColor(Color.parseColor("#ffffff"));
        }else{
            check.setBackgroundResource(R.drawable.background_time_nocheck);
            check.setTextColor(Color.parseColor("#111111"));
        }
    }

    private int whoRadChecked(){
        int index = 0;
        for(int i=0; i<7; i++){
            radioBtn = (RadioButton) group_time.getChildAt(i);
            if(radioBtn.isChecked()){
                index = i;
            }
        }
        return index;
    }

    private String whoCheckChecked(){
        String str = "";
        for(int i=0; i<7; i++){
            checkBox = (CheckBox) group_week.getChildAt(i);
            if(checkBox.isChecked()){
                str += i;
            }
        }
        return str;
    }

    private void setCheckedBackground(){
        for(int i=0; i<7; i++){
            radioBtn = (RadioButton) group_time.getChildAt(i);
            checkBox = (CheckBox) group_week.getChildAt(i);
            if(radioBtn.isChecked()){
                Utils.setBackgroundChecked(context, color, radioBtn, 8);
            }
            if(checkBox.isChecked()){
                Utils.setBackgroundChecked(context, color, checkBox, 8);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
         list = (ArrayList<RemindTimes>) data.getSerializableExtra("back");
         habit.setRemindTimes(list);
    }

    @Override
    public void onFailure(String str) {
        Looper.prepare();
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
        Looper.loop();
    }

    @Override
    public void onSuccess(String data) {
        ResponseBody body = Utils.gson.fromJson(data, ResponseBody.class);
        if(!Utils.isSuccess(body.getCode())) {
            Looper.prepare();
            Toast.makeText(getApplicationContext(), Utils.info(body.getCode()), Toast.LENGTH_SHORT).show();
            Looper.loop();
        }else{
            finish();
            Looper.prepare();
            Toast.makeText(getApplicationContext(), Utils.info(body.getCode()), Toast.LENGTH_SHORT).show();
            Looper.loop();
        }
    }

    @Override
    public void onSuccess2(String data) {

    }
}