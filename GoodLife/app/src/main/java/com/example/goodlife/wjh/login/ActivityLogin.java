package com.example.goodlife.wjh.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.goodlife.R;
import com.example.goodlife.Utils;
import com.example.goodlife.wjh.MainActivity;
import com.example.goodlife.wjh.MyView;
import com.example.goodlife.wjh.bean.ReqUser;
import com.example.goodlife.wjh.bean.ResponseBody;
import com.example.goodlife.wjh.login.listener.PasswordEditChangedListener;
import com.example.goodlife.wjh.login.listener.UserEditChangedListener;
import com.example.goodlife.wjh.regist.ActivityRegist;

public class ActivityLogin extends AppCompatActivity implements MyView{

    private EditText et_login_user;
    private EditText et_login_password;
    private TextView tv_login_register;

    private ImageView hide_img;
    private Button btn_login;
    private ImageView iv_login_user_cancle;
    private ImageView iv_login_password_cancle;

    private ImageView img_return;

    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sp = getApplication().getSharedPreferences("data",MODE_PRIVATE);
        int id = sp.getInt("userId", 0);
        Log.e("id", id+"");
        if(id==0){
            setContentView(R.layout.activity_login);
            setView();
            loginPresenter = new LoginPresenter(this);
        }else{
            Intent j = new Intent();
            j.setClass(getApplicationContext(), MainActivity.class);
            startActivity(j);
        }

    }

    private void findViews() {
        et_login_user = findViewById(R.id.et_login_user);
        et_login_password = findViewById(R.id.et_login_password);
        tv_login_register = findViewById(R.id.tv_login_register);
        btn_login = findViewById(R.id.btn_login);
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
//        img_return.setOnClickListener(myOnClickListener);

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
                        ReqUser req = new ReqUser();
                        req.setAccount(user);
                        req.setPwd(password);
                        loginPresenter.getData(req);
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

    @Override
    public void onFailure(String message) {
        Looper.prepare();
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        Looper.loop();
    }

    @Override
    public void onSuccess(String data) {
        ResponseBody body = Utils.gson.fromJson(data, ResponseBody.class);
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