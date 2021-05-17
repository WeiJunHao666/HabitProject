package com.example.goodlife.wjh;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.TimePickerDialog;

import android.os.Build;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TimePicker;


import com.example.goodlife.R;
import com.example.goodlife.Utils;
import com.example.goodlife.wjh.adapter.RecyclerRemindAdapter;
import com.example.goodlife.wjh.bean.Remind;
import com.example.goodlife.wjh.view.LinedTextView;

import java.util.ArrayList;
import java.util.List;

public class ActivityRemind extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private RecyclerRemindAdapter adapter;
    private LinedTextView textView;
    private RelativeLayout spacing;
    private TimePickerDialog dialogs;

    private ImageView img_return;
    private ImageView img_add;

    private List<Remind> list;
    private int flag = 0;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remind);

        init();
        findViews();
        setListener();
        textView.setWidthHeight(Utils.getDisplayMetrics(this).widthPixels,
                Utils.getDisplayMetrics(this).heightPixels);
        //recyclerView绑定适配器
        setRecyclerAdapter(flag);

    }


    private void setRecyclerAdapter(int flag) {

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(manager);

        adapter = new RecyclerRemindAdapter(this, list, flag);
        recyclerView.setAdapter(adapter);

    }

    private void findViews() {
        recyclerView = findViewById(R.id.recycler_remind);
        textView = findViewById(R.id.txt_lined);
        spacing = findViewById(R.id.layout_bottom_margin);
        img_add = findViewById(R.id.img_remind_add);
        img_return = findViewById(R.id.img_remind_return);
    }

    private void setListener() {
        img_return.setOnClickListener(this);
        img_add.setOnClickListener(this);
    }


    private void init() {
        list = new ArrayList<Remind>();
        Remind r1 = new Remind("10:09", 1);
        Remind r2 = new Remind("10:09", 1);
        Remind r3 = new Remind("10:09", 1);
        Remind r4 = new Remind("10:09", 1);
        Remind r5 = new Remind("10:09", 1);
        Remind r6 = new Remind("10:09", 1);
        Remind r7 = new Remind("10:09", 1);
        Remind r8 = new Remind("10:09", 1);
        Remind r9 = new Remind("10:09", 1);
        list.add(r1);
        list.add(r2);
        list.add(r3);
        list.add(r4);
        list.add(r5);
        list.add(r6);
        list.add(r7);
        list.add(r8);
        list.add(r9);


    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void createDialogTimePicker(){
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        View view = View.inflate(this, R.layout.dialog_timepicker, null);
        dialog.setView(view)
                .setCancelable(false)
                .create();
        final AlertDialog show = dialog.show();

        Button cancel = view.findViewById(R.id.btn_remind_cancel);
        Button save = view.findViewById(R.id.btn_remind_save);
        final TimePicker timePicker = view.findViewById(R.id.timePicker);

        //设置当前时间
        String []time = Utils.getCurrentTime().split(":");
        timePicker.setHour(Integer.parseInt(time[0]));
        timePicker.setMinute(Integer.parseInt(time[1]));

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show.dismiss();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                int hour = timePicker.getHour();
                int minute = timePicker.getMinute();

                Remind r = new Remind(hour+":"+minute, 1);
                list.add(r);

                //标记save了一个提醒
                flag = 1;
                //隐藏linedTextView 展示用View画的线
                textView.setVisibility(View.GONE);
                setRecyclerAdapter(flag);

                show.dismiss();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_remind_return:
                finish();
                break;
            case R.id.img_remind_add:
                createDialogTimePicker();
                break;
        }
    }

}