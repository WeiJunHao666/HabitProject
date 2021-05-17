package com.example.goodlife;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.goodlife.adapter.RecyclerAdapter;
import com.example.goodlife.bean.Habit;

import com.haibin.calendarview.CalendarView;

import java.util.ArrayList;
import java.util.List;


public class ActivityDaily extends AppCompatActivity {

    private CalendarView calendarView;
    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private TextView txt_month;
    private List<Habit> dataSource;
    private ImageView img_return;
    private ImageView img_edit;


    private TypedArray imgs;
    private String[] colors;


    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily);
        //绑定控件
        findViews();

        //获取array文件中imgs、colors
        imgs = this.getResources().obtainTypedArray(R.array.imgs);
        colors = this.getResources().getStringArray(R.array.colors);

        //填充dataSource数据
        init();

        //recyclerView绑定适配器
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(manager);

        adapter = new RecyclerAdapter(this, dataSource, imgs, colors);
        recyclerView.setAdapter(adapter);

        //月份改变事件
        calendarView.setOnMonthChangeListener(new CalendarView.OnMonthChangeListener() {
            @Override
            public void onMonthChange(int year, int month) {

                txt_month.setText("日常动态·" + month+"月");

                if(calendarView.getCurMonth() != month ){
                    calendarView.setSelectedColor(Color.parseColor("#00000000"),
                            Color.BLACK, Color.BLACK);
                }else{
                    if(calendarView.getCurYear() == year){
                        calendarView.setSelectedColor(Color.parseColor("#108cd4"),
                                Color.parseColor("#ffffff"), Color.parseColor("#ffffff"));
                    }else{
                        calendarView.setSelectedColor(Color.parseColor("#00000000"),
                                Color.BLACK, Color.BLACK);
                    }
                }
            }
        });
        //返回
        img_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //编辑
        img_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void init() {
        dataSource = new ArrayList<Habit>();
        txt_month.setText("日常动态·" + calendarView.getCurMonth()+"月");
        Habit h = new Habit("习惯", 0,0);
        Habit h1 = new Habit("习惯", 1,1);
        Habit h2 = new Habit("习惯", 1,1);
        Habit h3 = new Habit("习惯", 1,1);
        Habit h4 = new Habit("习惯", 1,1);
        Habit h5 = new Habit("习惯", 1,1);
        Habit h6 = new Habit("习惯", 1,1);
        Habit h7 = new Habit("习惯", 1,1);
        Habit h8 = new Habit("习惯", 1,1);
        Habit h9 = new Habit("习惯", 1,1);
        Habit h10 = new Habit("习惯", 1,1);
        Habit h11 = new Habit("习惯", 1,1);
        dataSource.add(h);
        dataSource.add(h1);
        dataSource.add(h2);
        dataSource.add(h3);
        dataSource.add(h4);
        dataSource.add(h5);
        dataSource.add(h6);
        dataSource.add(h7);
        dataSource.add(h8);
        dataSource.add(h9);
        dataSource.add(h10);
        dataSource.add(h11);
    }

    private void findViews() {
        calendarView = findViewById(R.id.calendarView);
        recyclerView = findViewById(R.id.recyclerView);
        txt_month = findViewById(R.id.txt_daily_month);
        img_edit = findViewById(R.id.img_edit);
        img_return = findViewById(R.id.img_return);
    }

}