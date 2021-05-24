package com.example.goodlife.wjh;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.TimePickerDialog;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TimePicker;


import com.example.goodlife.R;
import com.example.goodlife.Utils;
import com.example.goodlife.wjh.adapter.RecyclerRemindAdapter;
import com.example.goodlife.wjh.adapter.SlideRemindAdapter;
import com.example.goodlife.wjh.bean.RemindTimes;
import com.example.goodlife.wjh.customview.LinedTextView;

import java.io.Serializable;
import java.util.ArrayList;

public class ActivityRemind extends AppCompatActivity implements View.OnClickListener{

    private ListView listView;
    private SlideRemindAdapter adapter;
    private LinedTextView textView;
    private RelativeLayout spacing;

    private ImageView img_return;
    private ImageView img_add;

    private ArrayList<RemindTimes> list;
    private int flag = 0;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remind);

        //init();
        findViews();
        setListener();
        textView.setWidthHeight(Utils.getDisplayMetrics(this).widthPixels,
                Utils.getDisplayMetrics(this).heightPixels);

        Intent intent = getIntent();
        list = (ArrayList<RemindTimes>) intent.getSerializableExtra("list");

        //recyclerView绑定适配器
        setRecyclerAdapter(flag);

    }

    private void setRecyclerAdapter(int flag) {

        adapter = new SlideRemindAdapter(this, list, flag);
        listView.setAdapter(adapter);
        setListViewHeightBasedOnChildren(listView);

    }

    private void findViews() {
        listView = findViewById(R.id.listview_remind);
        textView = findViewById(R.id.txt_lined);
        spacing = findViewById(R.id.layout_bottom_margin);
        img_add = findViewById(R.id.img_remind_add);
        img_return = findViewById(R.id.img_remind_return);
    }

    private void setListener() {
        img_return.setOnClickListener(this);
        img_add.setOnClickListener(this);
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
                String time;
                if(minute<10){
                    time = hour + ":0" + minute;
                }else{
                    time = hour + ":" + minute;
                }

                RemindTimes r = new RemindTimes();
                r.setTime(time);
                r.setFlag(1);
                list.add(r);

                //标记save了一个提醒
                flag = 1;
                //隐藏linedTextView 展示用View画的线
                if(list.size()>=9){
                    textView.setVisibility(View.GONE);
                    setRecyclerAdapter(flag);
                }else{
                    setRecyclerAdapter(0);
                }

                show.dismiss();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_remind_return:
                Intent intent = new Intent();
                intent.putExtra("back", (Serializable) list);
                setResult(1, intent);
                finish();
                break;
            case R.id.img_remind_add:
                createDialogTimePicker();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("back", (Serializable) list);
        setResult(1, intent);
        super.onBackPressed();
    }


    public void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }

}