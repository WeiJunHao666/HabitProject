package com.example.goodlife.zsl.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.goodlife.R;
import com.example.goodlife.wjh.bean.Habit;

import java.util.ArrayList;
import java.util.List;

public class OverHabitPageActivity extends AppCompatActivity {
    private ImageView back;
    private ListView habitList;
    private List<Habit> habit = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_over_habit_page);
        back = findViewById(R.id.over_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                overridePendingTransition(R.anim.activity_maintain,R.anim.activity_out_bottom);
            }
        });
        Habit habit1 = new Habit();
        Habit habit2 = new Habit();
        habit1.setIcon(0);
        habit1.setName("早餐");
        habit1.setColor(0);
        habit2.setIcon(2);
        habit2.setName("晨跑");
        habit2.setColor(1);


        habit.add(habit1);
        habit.add(habit2);


    }
}