package com.example.goodlife.zsl.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;

import com.example.goodlife.R;

public class VipPageActivity extends AppCompatActivity {
    private ImageView back;
    private Button getVip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vip_page);
        back = findViewById(R.id.vip_back);
        getVip = findViewById(R.id.btn_get_vip);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                overridePendingTransition(R.anim.activity_maintain,R.anim.activity_out_bottom);
            }
        });



    }
}