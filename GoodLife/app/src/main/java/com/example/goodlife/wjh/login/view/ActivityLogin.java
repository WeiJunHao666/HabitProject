package com.example.goodlife.wjh.login.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.goodlife.R;
import com.example.goodlife.wjh.login.listener.PasswordEditChangedListener;
import com.example.goodlife.wjh.login.listener.UserEditChangedListener;
import com.example.goodlife.wjh.login.presenter.LoginPresenter;
import com.example.goodlife.wjh.regist.view.ActivityRegist;

public class ActivityLogin extends AppCompatActivity implements LoginView{

    private EditText et_login_user;
    private EditText et_login_password;
    private TextView tv_login_register;
    //private CircleImageView iv_login_head_img;
    private ImageView hide_img;
    private Button btn_login;
    private ImageView iv_login_user_cancle;
    private ImageView iv_login_password_cancle;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences sp = getApplication().getSharedPreferences("data",MODE_PRIVATE);
//        int id = 1;
//        if(id==0){
            setView();
            loginPresenter = new LoginPresenter(this);
//        }else{
//            Intent j = new Intent();
//            j.setClass(getApplicationContext(), MainActivity.class);
//            startActivity(j);
//        }

    }

    private void findViews() {
        et_login_user = findViewById(R.id.et_login_user);
        et_login_password = findViewById(R.id.et_login_password);
        tv_login_register = findViewById(R.id.tv_login_register);
        btn_login = findViewById(R.id.btn_login);
        //iv_login_head_img = findViewById(R.id.iv_login_head_img);
        hide_img = findViewById(R.id.hide_img);
        iv_login_user_cancle = findViewById(R.id.iv_login_user_cancle);
        iv_login_password_cancle = findViewById(R.id.iv_login_password_cancle);
    }

    private void setListener() {
        MyOnClickListener myOnClickListener = new MyOnClickListener();
        tv_login_register.setOnClickListener(myOnClickListener);
        btn_login.setOnClickListener(myOnClickListener);
        hide_img.setOnClickListener(myOnClickListener);
        iv_login_user_cancle.setOnClickListener(myOnClickListener);
        iv_login_password_cancle.setOnClickListener(myOnClickListener);
        et_login_user.addTextChangedListener(new UserEditChangedListener(iv_login_user_cancle, et_login_user));
        et_login_password.addTextChangedListener(new PasswordEditChangedListener(iv_login_password_cancle));
    }

    private void setView() {
        findViews();
        setListener();

        et_login_password.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    hide_img.setImageResource(R.drawable.hide);
                    hide_img.setTag("1");
                }else{
                    hide_img.setTag("0");
                    hide_img.setImageDrawable(null);
                }
            }
        });
    }

    class MyOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch(v.getId()){

                case R.id.tv_login_register:
                    Intent i = new Intent();
                    i.setClass(getApplicationContext(), ActivityRegist.class);
                    startActivity(i);
                    break;

                case R.id.btn_login:
                    String user = et_login_user.getText().toString();
                    String password = et_login_password.getText().toString();
                    if(user==null || user.equals("")){
                        onFailure("请填写用户名");

                    }else if(password==null || password.equals("")){
                        onFailure("请填写密码");

                    }else{
                        getData(user, password);
                    }
                    break;

                case R.id.hide_img:
                    if(hide_img.getTag() == "1"){
                        hide_img.setImageResource(R.drawable.show);
                        hide_img.setTag("2");
                        //显示密码
                        et_login_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    }else if(hide_img.getTag() == "2"){
                        hide_img.setImageResource(R.drawable.hide);
                        hide_img.setTag("1");
                        //隐藏密码
                        et_login_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    }
                    //光标移到内容末尾
                    et_login_password.setSelection(et_login_password.getText().length());
                    break;

                case R.id.iv_login_user_cancle:
                    et_login_user.setText("");
                    iv_login_user_cancle.setImageDrawable(null);
                    break;

                case R.id.iv_login_password_cancle:
                    et_login_password.setText("");
                    iv_login_password_cancle.setImageDrawable(null);
                    break;
            }
        }
    }

    public void getData(String userName, String password){
        loginPresenter.getData(userName, password);
    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(String data) {
        Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
//        Intent j = new Intent();
//        j.setClass(getApplicationContext(), LoginActivity.class);
//        startActivity(j);
//        SaveUserInfo.save(getApplicationContext(), data);
    }
}