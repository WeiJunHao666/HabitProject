package com.example.goodlife;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;

import com.example.goodlife.adapter.RecyclerRecordAdapter;
import com.example.goodlife.bean.Diary;
import com.haibin.calendarview.CalendarLayout;
import com.haibin.calendarview.CalendarView;

import java.util.ArrayList;
import java.util.List;

public class ActivityHabitDetail extends AppCompatActivity {

    private CalendarView calendarView;
    private CalendarLayout calendarLayout;

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
    }

}