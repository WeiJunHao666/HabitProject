package com.example.goodlife.wjh.detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.goodlife.R;
import com.example.goodlife.wjh.ActivityRemind;
import com.example.goodlife.wjh.MyView;
import com.example.goodlife.wjh.adapter.RecyclerRecordAdapter;
import com.example.goodlife.wjh.bean.Diary;
import com.haibin.calendarview.CalendarLayout;
import com.haibin.calendarview.CalendarView;
import java.util.ArrayList;
import java.util.List;

public class ActivityHabitDetail extends AppCompatActivity implements View.OnClickListener, MyView {

    private CalendarView calendarView;
    private CalendarLayout calendarLayout;
    private ImageView img_return;
    private ImageView img_edit;
    private RelativeLayout layoutRemind;
    private TextView txt_lookAll;

    private RecyclerView recyclerView;
    private RecyclerRecordAdapter adapter;
    private List<Diary> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_detail);
        //
        findViews();
        //
        initData();
        //
        setRecyclerAdapter();
        //
        setListener();
        //月份改变事件
        calendarView.setOnMonthChangeListener(new com.haibin.calendarview.CalendarView.OnMonthChangeListener() {
            @Override
            public void onMonthChange(int year, int month) {


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
    }


    private void setRecyclerAdapter() {
        //recyclerView绑定适配器
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(manager);

        adapter = new RecyclerRecordAdapter(this, list);
        recyclerView.setAdapter(adapter);

    }

    private void initData() {
        list = new ArrayList<>();
        Diary d1 = new Diary(0, "05月14日", "17:55", "哈利咯破哈速度哈师大");
        Diary d2 = new Diary(0, "05月14日", "17:55", "哈利咯破哈速度哈师大");
        list.add(d1);
        list.add(d2);
    }

    private void findViews() {
        recyclerView = findViewById(R.id.recycler_detail);
        calendarLayout = findViewById(R.id.calendarLayout_detail);
        calendarView = findViewById(R.id.calendarView_detail);
        img_return = findViewById(R.id.img_detail_return);
        img_edit = findViewById(R.id.img_detail_edit);
        layoutRemind = findViewById(R.id.layout_remind);
        txt_lookAll = findViewById(R.id.txt_lookAll);
    }

    private void setListener() {
        img_return.setOnClickListener(this);
        img_edit.setOnClickListener(this);
        layoutRemind.setOnClickListener(this);
        txt_lookAll.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_detail_return:
                finish();
                break;
            case R.id.img_detail_edit:
                break;
            case R.id.layout_remind:
                Intent intent = new Intent(this, ActivityRemind.class);
                startActivity(intent);
                break;
            case R.id.txt_lookAll:
                break;
        }
    }

    @Override
    public void onFailure(String message) {

    }

    @Override
    public void onSuccess(String data) {

    }

    @Override
    public void onSuccess2(String data) {

    }
}