package com.example.goodlife.wjh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.goodlife.R;
import com.example.goodlife.wjh.adapter.AddGridViewAdapter;
import com.example.goodlife.wjh.bean.Habit;
import com.example.goodlife.wjh.kindview.MyGridView;

import java.util.ArrayList;
import java.util.List;

public class ActivityAddHabit extends AppCompatActivity {

    private MyGridView gridView1;
    private MyGridView gridView2;
    private MyGridView gridView3;
    private MyGridView gridView4;
    private RelativeLayout layout;
    private ImageView img_return;

    private List<Habit> list1;
    private List<Habit> list2;
    private List<Habit> list3;
    private List<Habit> list4;

    private TypedArray imgs;
    private String[] colors;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_habit);

        imgs = getResources().obtainTypedArray(R.array.imgs);
        colors = getResources().getStringArray(R.array.colors);

        findViews();
        initData();

        setGridAdapter(list1, gridView1);
        setGridAdapter(list2, gridView2);
        setGridAdapter(list3, gridView3);
        setGridAdapter(list4, gridView4);

        img_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ActivitySetHabit.class);
                context.startActivity(intent);
            }
        });

    }

    private void findViews() {
        gridView1 = findViewById(R.id.gridView_one);
        gridView2 = findViewById(R.id.gridView_two);
        gridView3 = findViewById(R.id.gridView_three);
        gridView4 = findViewById(R.id.gridView_four);
        layout = findViewById(R.id.rl_custom);
        img_return = findViewById(R.id.img_return);
    }

    public void initData(){
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        list3 = new ArrayList<>();
        list4 = new ArrayList<>();
        Habit a1 = new Habit("习惯a1", 0, 0);
        Habit a2 = new Habit("习惯a2", 1, 1);
        Habit a3 = new Habit("习惯a3", 2, 2);
        Habit a4 = new Habit("习惯a4", 3, 3);
        Habit a5 = new Habit("习惯a5", 4, 3);
        Habit a6 = new Habit("习惯a6", 5, 3);
        Habit b1 = new Habit("习惯b1", 0, 0);
        Habit b2 = new Habit("习惯b2", 1, 1);
        Habit b3 = new Habit("习惯b3", 2, 2);
        Habit b4 = new Habit("习惯b4", 3, 3);
        Habit b5 = new Habit("习惯b5", 4, 3);
        Habit b6 = new Habit("习惯b6", 5, 3);
        Habit c1 = new Habit("习惯c1", 0, 0);
        Habit c2 = new Habit("习惯c2", 1, 1);
        Habit c3 = new Habit("习惯c3", 2, 2);
        Habit c4 = new Habit("习惯c4", 3, 3);
        Habit c5 = new Habit("习惯c5", 4, 3);
        Habit c6 = new Habit("习惯c6", 5, 3);
        Habit d1 = new Habit("习惯d1", 0, 0);
        Habit d2 = new Habit("习惯d2", 1, 1);
        Habit d3 = new Habit("习惯d3", 2, 2);
        Habit d4 = new Habit("习惯d4", 3, 3);
        Habit d5 = new Habit("习惯d5", 4, 3);
        Habit d6 = new Habit("习惯d6", 5, 3);
        listAdd(list1, a1, a2, a3, a4, a5, a6);
        listAdd(list2, b1, b2, b3, b4, b5, b6);
        listAdd(list3, c1, c2, c3, c4, c5, c6);
        listAdd(list4, d1, d2, d3, d4, d5, d6);
    }

    private void listAdd(List<Habit> list, Habit a1, Habit a2, Habit a3, Habit a4, Habit a5, Habit a6) {
        list.add(a1);
        list.add(a2);
        list.add(a3);
        list.add(a4);
        list.add(a5);
        list.add(a6);
    }

    private void setGridAdapter(List<Habit> list, GridView gridView) {
        AddGridViewAdapter adapter = new AddGridViewAdapter(this, list, R.layout.item_gridview_add, imgs, colors);
        gridView.setAdapter(adapter);
    }
}