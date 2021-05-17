package com.example.goodlife.zsl.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.goodlife.R;

public class FeedBackActivity extends AppCompatActivity {
    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
         back = findViewById(R.id.feed_back_back);
         back.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 onBackPressed();
                 overridePendingTransition(R.anim.activity_maintain,R.anim.activity_out_bottom);
             }
         });
    }
}