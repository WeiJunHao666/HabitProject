package com.example.goodlife.wjh.login.listener;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ImageView;

import com.example.goodlife.R;


public class PasswordEditChangedListener implements TextWatcher {
    private CharSequence temp;//监听前的文本
    private ImageView iv_login_password_cancle;
    public PasswordEditChangedListener(ImageView iv){
        this.iv_login_password_cancle = iv;
    }
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        temp = s;
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(temp.length()==0){
            iv_login_password_cancle.setImageDrawable(null);
        }else{
            iv_login_password_cancle.setImageResource(R.drawable.cancel);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
