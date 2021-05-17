package com.example.goodlife.zsl.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.goodlife.R;

public class NotePageActivity extends AppCompatActivity {
    private ImageView back;
    private ImageView more;
    private TextView allNoteNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_page);
        getViews();
        setOnClickListener();
    }

    private void getViews(){
        back = findViewById(R.id.note_page_back);
        more = findViewById(R.id.note_page_more);
        allNoteNumber = findViewById(R.id.note_page_text2);
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