package com.example.goodlife.wjh.regist;

import com.example.goodlife.ConfigUtil;
import com.example.goodlife.Utils;
import com.example.goodlife.wjh.MyListener;
import com.example.goodlife.wjh.MyView;
import com.example.goodlife.wjh.bean.ReqUser;


public class RegisterPresenter {
    private MyView view;
    private RegisterModel model = new RegisterModel();
    public RegisterPresenter(MyView view){
        this.view = view;
    }

    public void upload(ReqUser user){

        String url = ConfigUtil.REGISTER;
        String json = Utils.gson.toJson(user);

        model.register(url, json, new MyListener() {
            @Override
            public void onSuccess(final String msg) {
                view.onSuccess(msg);
            }

            @Override
            public void onFailure(final String msg) {
                view.onFailure(msg);
            }
        });
    }
}
