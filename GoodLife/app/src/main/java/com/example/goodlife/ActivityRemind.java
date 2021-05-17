package com.example.goodlife;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.os.Build;
import android.os.Bundle;

import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TimePicker;


import com.example.goodlife.adapter.RecyclerRemindAdapter;
import com.example.goodlife.bean.Remind;
import com.example.goodlife.view.LinedTextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.sql.Time;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class ActivityRemind extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private RecyclerRemindAdapter adapter;
    private LinedTextView textView;
    private TimePickerDialog dialogs;

    private ImageView img_return;
    private ImageView img_add;

    private List<Remind> list;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remind);

        init();
        findViews();
        setListener();
        //recyclerView绑定适配器
        setRecyclerAdapter();

    }


    private void setRecyclerAdapter() {

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(manager);

        adapter = new RecyclerRemindAdapter(this, list);
        recyclerView.setAdapter(adapter);

        textView.setWidthHeight(Utils.getDisplayMetrics(this).widthPixels,
                Utils.getDisplayMetrics(this).heightPixels);
    }

    private void findViews() {
        recyclerView = findViewById(R.id.recycler_remind);
        textView = findViewById(R.id.txt_lined);
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
//        Remind r2 = new Remind("10:09", 1);
//        Remind r3 = new Remind("10:09", 1);
//        Remind r4 = new Remind("10:09", 1);
//        Remind r5 = new Remind("10:09", 1);
//        Remind r6 = new Remind("10:09", 1);
//        Remind r7 = new Remind("10:09", 1);
//        Remind r8 = new Remind("10:09", 1);
//        Remind r9 = new Remind("10:09", 1);
        list.add(r1);
//        list.add(r2);
//        list.add(r3);
//        list.add(r4);
//        list.add(r5);
//        list.add(r6);
//        list.add(r7);
//        list.add(r8);
//        list.add(r9);


    }

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
        //save = new Button(new ContextThemeWrapper(this, R.style.AppTheme), null, R.style.AppTheme);

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
                adapter.notifyDataSetChanged();
                show.dismiss();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_remind_return:
                break;
            case R.id.img_remind_add:
                createDialogTimePicker();
                break;
        }
    }

}