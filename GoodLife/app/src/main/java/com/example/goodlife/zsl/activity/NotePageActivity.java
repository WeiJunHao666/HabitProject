package com.example.goodlife.zsl.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.goodlife.R;
import com.example.goodlife.wjh.bean.Habit;
import com.example.goodlife.zsl.adapter.NoteAdapter;

import java.util.ArrayList;
import java.util.List;

public class NotePageActivity extends AppCompatActivity {
    private ImageView back;
    private ImageView more;
    private TextView allNoteNumber;
    private List<Habit> habitList = new ArrayList<>();
    private GridView gridView;
    private TypedArray imgs;
    private String[] colors;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_page);
        getViews();
        setOnClickListener();
        imgs = getResources().obtainTypedArray(R.array.imgs);
        colors = getResources().getStringArray(R.array.colors);
        Habit habit1 = new Habit();
        Habit habit2 = new Habit();
        habit1.setIcon(0);
        habit1.setName("早餐");
        habit1.setColor(0);
        habit2.setIcon(2);
        habit2.setName("晨跑");
        habit2.setColor(1);
        habitList.add(habit1);
        habitList.add(habit2);
        NoteAdapter noteAdapter = new NoteAdapter(NotePageActivity.this,habitList,R.layout.note_page_item,imgs,colors);
        gridView.setAdapter(noteAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(NotePageActivity.this,HabitNoteActivity.class);
                intent.putExtra("icon",habitList.get(position).getIcon()+"");
                Log.e("zzz",habitList.get(position).getIcon()+"");
                intent.putExtra("name",habitList.get(position).getName());
                Log.e("zsl",habitList.get(position).getName());
                startActivity(intent);
                overridePendingTransition(R.anim.activity_in_bottom,R.anim.activity_maintain);
            }
        });
    }

    private void getViews(){
        back = findViewById(R.id.note_page_back);
        more = findViewById(R.id.note_page_more);
        allNoteNumber = findViewById(R.id.note_page_text2);
        gridView = findViewById(R.id.note_page_body);
    }

    private void setOnClickListener(){
        MyListener listener = new MyListener();
        back.setOnClickListener(listener);
        more.setOnClickListener(listener);

    }

    private class MyListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.note_page_back:
                    onBackPressed();
                    overridePendingTransition(R.anim.activity_maintain,R.anim.activity_out_bottom);
                    break;
                case R.id.note_page_more:
                    Intent intent = new Intent(NotePageActivity.this,NoteMorePageActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.activity_in_bottom,R.anim.activity_maintain);
                    break;
            }
        }
    }
}