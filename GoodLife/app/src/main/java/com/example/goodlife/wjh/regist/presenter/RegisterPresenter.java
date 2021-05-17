package com.example.goodlife.wjh.regist.presenter;

import android.os.Handler;
import android.util.Log;

import com.example.goodlife.ConfigUtil;
import com.example.goodlife.wjh.regist.model.RegisterListener;
import com.example.goodlife.wjh.regist.model.RegisterModel;
import com.example.goodlife.wjh.regist.view.RegisterView;


public class RegisterPresenter {
    private RegisterView registerView;
    private RegisterModel registerModelClass;
    private Handler mHandler = new Handler();
    public RegisterPresenter(RegisterView registerView){
        this.registerView = registerView;
    }
    public void upload(String userName, String password){

        registerModelClass = new RegisterModel();
        String s = ConfigUtil.REGISTER +  "?username="+userName+"&password=" + password;
        Log.e("sss", s);
        registerModelClass.register(s, new RegisterListener() {
            @Override
            public void onSuccess(final String msg) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        registerView.onSuccess(msg);
                    }
                });
            }


            @Override
            public void onFailure(final String msg) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        registerView.onFailure(msg);
                    }
                });
            }
        });
    }
}
