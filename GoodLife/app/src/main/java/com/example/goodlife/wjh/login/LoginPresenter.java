package com.example.goodlife.wjh.login;

import com.example.goodlife.ConfigUtil;
import com.example.goodlife.Utils;
import com.example.goodlife.wjh.MyListener;
import com.example.goodlife.wjh.MyView;
import com.example.goodlife.wjh.bean.ReqUser;


public class LoginPresenter {
    private MyView view;
    private LoginModel model = new LoginModel();

    public LoginPresenter(MyView view){
        this.view = view;
    }

    public void getData(ReqUser req){
        String s = ConfigUtil.LOGIN;
        String json = Utils.gson.toJson(req);

        model.login(s, json, new MyListener() {
            @Override
            public void onSuccess(final String data) {
                view.onSuccess(data);
            }
            @Override
            public void onFailure(final String msg) {
                view.onFailure(msg);
            }
        });
    }
}
