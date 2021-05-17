package com.example.goodlife.zsl.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.goodlife.R;

public class OverHabitPageActivity extends AppCompatActivity {
    private ImageView back;
    private ListView list;


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
    }
}