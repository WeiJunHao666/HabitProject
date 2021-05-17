package com.example.goodlife.zsl.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.goodlife.R;

public class SetPageActivity extends AppCompatActivity {
    private ImageView back;
    private RelativeLayout btn1;
    private RelativeLayout btn2;
    private RelativeLayout btn3;
    private RelativeLayout btn4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_page);
        findViews();
        setClickListener();
    }

    private void findViews(){
        back = findViewById(R.id.img_set_back);
        btn1 = findViewById(R.id.set_btn1);
        btn2 = findViewById(R.id.set_btn2);
        btn3 = findViewById(R.id.set_btn3);
        btn4 = findViewById(R.id.set_btn4);
    }

    private void setClickListener(){
        MyListener listener = new MyListener();
        back.setOnClickListener(listener);
        btn1.setOnClickListener(listener);
        btn2.setOnClickListener(listener);
        btn3.setOnClickListener(listener);
        btn4.setOnClickListener(listener);
    }
    private class MyListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.img_set_back:
                    onBackPressed();
                    overridePendingTransition(R.anim.activity_maintain,R.anim.activity_out_bottom);
                    break;
            }
        }
    }
}