package com.example.goodlife.zsl.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.goodlife.R;
import com.example.goodlife.wjh.bean.Habit;

import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;

public class HabitNoteAddPageActivity extends AppCompatActivity {
    private ImageView back;
    private CircleImageView head;
    private TextView name;
    private EditText editText;
    private Button submint;
    private Button picture;
    private TypedArray imgs;

    private Habit habit = new Habit();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_note_add_page);


        getViews();
        setClickListener();
        Intent intent = super.getIntent();
        String str = intent.getStringExtra("icon");
        String str1 = intent.getStringExtra("name");
        int i = Integer.parseInt(str);
        habit.setIcon(i);
        habit.setName(str1);
        imgs = getResources().obtainTypedArray(R.array.imgs);
        head.setImageResource(imgs.getResourceId(habit.getIcon(), -1));
        name.setText(habit.getName());
    }

    private void getViews(){
        back = findViewById(R.id.write_note_page_back);
        head = findViewById(R.id.write_note_page_habit_head);
        name = findViewById(R.id.write_note_page_habit_name);
        editText = findViewById(R.id.write_note_page_edit);
        submint = findViewById(R.id.write_note_page_submit);
        picture = findViewById(R.id.write_note_page_up_picture);
    }
    private void setClickListener(){
        MyListener listener = new MyListener();
        back.setOnClickListener(listener);
        submint.setOnClickListener(listener);
        picture.setOnClickListener(listener);

    }
    private class MyListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.write_note_page_back:
                    onBackPressed();
                    overridePendingTransition(R.anim.activity_maintain,R.anim.activity_out_bottom);
                    break;
                case R.id.write_note_page_submit:
                    if (editText.length()==0){
                        Toast.makeText(getApplicationContext(), "请输入内容", Toast.LENGTH_SHORT).show();
                    }else {
                        onBackPressed();
                        overridePendingTransition(R.anim.activity_maintain, R.anim.activity_out_bottom);
                    }
                    break;
                case R.id.write_note_page_up_picture:

                    break;
            }
        }
    }
}