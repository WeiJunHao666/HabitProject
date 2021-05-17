package com.example.goodlife.zsl.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.icu.lang.UCharacter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;

import com.example.goodlife.R;

public class HabitRemindActivity extends AppCompatActivity {
    private ImageView back;
    private Button submit;
    private Switch aSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_remind);
        getViews();
        setOnClickListener();
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

            @SuppressLint("ResourceAsColor")
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    submit.setBackgroundDrawable(getResources().getDrawable(R.drawable.rounded_corner_button));
                    submit.setTextColor(R.color.white);
                }else{
                    submit.setBackgroundDrawable(getResources().getDrawable(R.drawable.rounded_corner_button));
                    submit.setTextColor(R.color.white);
                }
            }
        });




    }

    private void getViews(){
        back = findViewById(R.id.habit_remind_back);
        submit = findViewById(R.id.habit_remind_submit);
        aSwitch = findViewById(R.id.habit_remind_switch);
    }
    private void setOnClickListener(){
        MyListener listener = new MyListener();
        back.setOnClickListener(listener);
        submit.setOnClickListener(listener);

    }
    private class MyListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.habit_remind_back:
                    onBackPressed();
                    overridePendingTransition(R.anim.activity_maintain,R.anim.activity_out_bottom);
                    break;
                case R.id.habit_remind_submit:
                    onBackPressed();
                    overridePendingTransition(R.anim.activity_maintain,R.anim.activity_out_bottom);
                    break;
            }
        }
    }
}