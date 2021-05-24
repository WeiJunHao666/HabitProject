package com.example.goodlife.zsl.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.goodlife.R;
import com.example.goodlife.wjh.bean.Habit;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class HabitNoteActivity extends AppCompatActivity {
    private ImageView back;
    private CircleImageView habitHead;
    private TextView habitName;
    private TextView noteNumber;
    private ListView listView;
    private RelativeLayout noteAdd;
    private Habit habit = new Habit();
    private TypedArray imgs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_note);
        getViews();
        setOnClickListener();
        Intent intent = super.getIntent();
        String str = intent.getStringExtra("icon");
        String str1 = intent.getStringExtra("name");
        int i = Integer.parseInt(str);
        habit.setIcon(i);
        habit.setName(str1);
        imgs = getResources().obtainTypedArray(R.array.imgs);
        habitHead.setImageResource(imgs.getResourceId(habit.getIcon(), -1));
        habitName.setText(habit.getName());


    }


    private void getViews(){
        back = findViewById(R.id.habit_note_back);
        habitHead = findViewById(R.id.habit_note_head);
        habitName = findViewById(R.id.habit_note_habit_name);
        noteNumber = findViewById(R.id.habit_note_number);
        listView = findViewById(R.id.habit_note_list);
        noteAdd = findViewById(R.id.habit_note_add);
    }


    private void setOnClickListener(){
        MyListener listener = new MyListener();
        back.setOnClickListener(listener);
        noteAdd.setOnClickListener(listener);

    }

    private class MyListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.habit_note_back:
                    onBackPressed();
                    overridePendingTransition(R.anim.activity_maintain,R.anim.activity_out_bottom);
                    break;
                case R.id.habit_note_add:
                    Intent intent = new Intent(HabitNoteActivity.this,HabitNoteAddPageActivity.class);
                    intent.putExtra("icon",habit.getIcon()+"");
                    intent.putExtra("name",habit.getName());
                    startActivity(intent);
                    overridePendingTransition(R.anim.activity_in_bottom,R.anim.activity_maintain);
                    break;
            }
        }
    }
}