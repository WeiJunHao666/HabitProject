package com.example.goodlife.wjh.daily;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.goodlife.R;
import com.example.goodlife.wjh.MyView;
import com.example.goodlife.wjh.adapter.RecyclerAdapter;
import com.example.goodlife.wjh.bean.Habit;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;
import java.util.ArrayList;
import java.util.List;


public class ActivityDaily extends AppCompatActivity implements MyView {

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
        calendarView.setOnCalendarSelectListener(new CalendarView.OnCalendarSelectListener() {
            @Override
            public void onCalendarOutOfRange(Calendar calendar) {

            }

            @Override
            public void onCalendarSelect(Calendar calendar, boolean isClick) {
                Habit h = setHabit("学习", 0, 0);
                Habit h1 = setHabit("早饭", 1, 6);
                dataSource.remove(0);
                dataSource.remove(1);
                dataSource.add(h);
                dataSource.add(h1);
                adapter.notifyDataSetChanged();
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
        Habit h = setHabit("学习", 0, 0);
        Habit h1 = setHabit("早饭", 1, 6);
        Habit h2 = setHabit("英语", 3, 8);
        Habit h3 = setHabit("数学", 2, 2);
        Habit h4 = setHabit("跑步", 5, 3);
        dataSource.add(h);
        dataSource.add(h1);
        dataSource.add(h2);
        dataSource.add(h3);
        dataSource.add(h4);
    }


    private void findViews() {
        calendarView = findViewById(R.id.calendarView);
        recyclerView = findViewById(R.id.recyclerView);
        txt_month = findViewById(R.id.txt_daily_month);
        img_edit = findViewById(R.id.img_edit);
        img_return = findViewById(R.id.img_return);
    }

    private Habit setHabit(String str, int icon, int color){
        Habit habit = new Habit();
        habit.setName(str);
        habit.setIcon(icon);
        habit.setColor(color);

        return habit;
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