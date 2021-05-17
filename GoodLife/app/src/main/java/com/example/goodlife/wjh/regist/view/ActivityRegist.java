package com.example.goodlife.wjh.regist.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.goodlife.wjh.MainActivity;
import com.example.goodlife.R;
import com.example.goodlife.wjh.regist.presenter.RegisterPresenter;

public class ActivityRegist extends AppCompatActivity implements RegisterView{

    private EditText et_register_user;
    private EditText et_register_password;
    private Button btn_register;
    private CheckBox cb_register_check;
    private TextView tv_protocol;
    private RegisterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
    }

    private void setListener() {
        MyOnClickListener myOnClickListener = new MyOnClickListener();
        et_register_password.setOnClickListener(myOnClickListener);
        et_register_user.setOnClickListener(myOnClickListener);
        btn_register.setOnClickListener(myOnClickListener);
        cb_register_check.setOnClickListener(myOnClickListener);
    }

    private void findViews() {
        et_register_user = findViewById(R.id.et_register_user);
        et_register_password = findViewById(R.id.et_register_password);
        btn_register = findViewById(R.id.btn_register);
        cb_register_check = findViewById(R.id.cb_register_check);
        tv_protocol = findViewById(R.id.tv_protocol);
    }

    class MyOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_register:
                    if(!cb_register_check.isChecked()){
                        onFailure("请阅读协议");
                    }else{
                        String user = et_register_user.getText().toString();
                        String password = et_register_password.getText().toString();
                        if(user==null || user.equals("")){
                            onFailure("请填写用户名");

                        }else if(password==null || password.equals("")){
                            onFailure("请填写密码");

                        }else{
                            presenter.upload(user, password);
                        }
                    }
                    break;
            }
        }
    }

    /**
     * 设置服务协议及隐私协议的格式
     */
    private void setProtocol() {
        tv_protocol = findViewById(R.id.tv_protocol);
        SpannableString spannableString = new SpannableString("已阅读并同意以下协议： 二货平台服务协议、隐私权政策");
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {

            }

            @Override
            public void updateDrawState(TextPaint ds) {
                ds.setColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));//设置颜色
                ds.setUnderlineText(true);//去掉下划线
            }
        };
        spannableString.setSpan(clickableSpan, 12, 26, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(ContextCompat.getColor(this, R.color.colorPrimary));
        spannableString.setSpan(colorSpan, 12, 26, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        tv_protocol.setText(spannableString);
        tv_protocol.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public void onFailure(String str) {
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(String str) {
        Intent i = new Intent();
        i.setClass(getApplicationContext(), MainActivity.class);
        startActivity(i);
        //presenter.upload();
        Toast.makeText(getApplicationContext(),"注册成功", Toast.LENGTH_SHORT).show();
    }
}