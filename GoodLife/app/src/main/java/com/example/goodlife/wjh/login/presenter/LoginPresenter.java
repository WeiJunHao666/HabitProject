package com.example.goodlife.wjh.login.presenter;

import android.os.Handler;

import com.example.goodlife.ConfigUtil;
import com.example.goodlife.wjh.login.model.LoginListener;
import com.example.goodlife.wjh.login.model.LoginModel;
import com.example.goodlife.wjh.login.view.LoginView;


public class LoginPresenter {
    private LoginView loginView;
    private LoginModel loginModelClass;
    private Handler mHandler = new Handler();

    public LoginPresenter(LoginView loginView){
        this.loginView = loginView;
    }

    public void getData(String userName, String password){

        loginModelClass = new LoginModel();
        String s = ConfigUtil.LOGIN +"?username="+userName+"&password=" +password;
        loginModelClass.login(s, new LoginListener() {
            @Override
            public void onSuccess(final String data) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        loginView.onSuccess(data);
                    }
                });

            }

            @Override
            public void onFailure(final String msg) {
                mHandler.post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        loginView.onFailure(msg);
                    }
                });
            }

        });
    }
}
