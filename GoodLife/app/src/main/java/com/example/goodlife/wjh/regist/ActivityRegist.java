package com.example.goodlife.wjh.regist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.goodlife.Utils;
import com.example.goodlife.wjh.MainActivity;
import com.example.goodlife.R;
import com.example.goodlife.wjh.MyView;
import com.example.goodlife.wjh.bean.ReqUser;
import com.example.goodlife.wjh.bean.ResponseBody;

public class ActivityRegist extends AppCompatActivity implements MyView {

    private EditText et_register_user;
    private EditText et_register_password;
    private EditText et_username;
    private Button btn_register;
    private CheckBox cb_register_check;
    private TextView tv_protocol;
    private ImageView img_return;

    private RegisterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        findViews();
        setListener();
        presenter = new RegisterPresenter(this);
        setProtocol();
    }

    private void setListener() {
        MyOnClickListener myOnClickListener = new MyOnClickListener();
        et_register_password.setOnClickListener(myOnClickListener);
        et_register_user.setOnClickListener(myOnClickListener);
        btn_register.setOnClickListener(myOnClickListener);
        cb_register_check.setOnClickListener(myOnClickListener);
        img_return.setOnClickListener(myOnClickListener);
    }

    private void findViews() {
        et_register_user = findViewById(R.id.et_register_user);
        et_register_password = findViewById(R.id.et_register_password);
        et_username = findViewById(R.id.et_username);
        btn_register = findViewById(R.id.btn_register);
        cb_register_check = findViewById(R.id.cb_register_check);
        tv_protocol = findViewById(R.id.tv_protocol);
        img_return = findViewById(R.id.img_regist_return);
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
                        String username = et_username.getText().toString();
                        if(user==null || user.equals("")){
                            onFailure("请填写用户名");

                        }else if(password==null || password.equals("")){
                            onFailure("请填写密码");

                        }else if(username==null || username.equals("")){
                            onFailure("请填写用户名");
                        }else{
                            ReqUser reqUser = new ReqUser(user, password, username);
                            presenter.upload(reqUser);
                        }
                    }
                    break;
                case R.id.img_regist_return:
                    finish();
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
        Looper.prepare();
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
        Looper.loop();
    }

    @Override
    public void onSuccess(String str) {

        ResponseBody body = Utils.gson.fromJson(str, ResponseBody.class);

        if(!Utils.isSuccess(body.getCode())) {
            Looper.prepare();
            Toast.makeText(getApplicationContext(), Utils.info(body.getCode()), Toast.LENGTH_SHORT).show();
            Looper.loop();
        }else{
            Utils.save(getApplicationContext(), (String) body.getData());
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            Looper.prepare();
            Toast.makeText(getApplicationContext(), Utils.info(body.getCode()), Toast.LENGTH_SHORT).show();
            Looper.loop();
        }
    }

    @Override
    public void onSuccess2(String data) {
        
    }
}