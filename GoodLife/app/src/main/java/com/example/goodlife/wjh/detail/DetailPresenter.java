package com.example.goodlife.wjh.detail;

import com.example.goodlife.wjh.MyListener;
import com.example.goodlife.wjh.MyView;

public class DetailPresenter {
    private MyView view;
    private DetailModel model = new DetailModel();

    public DetailPresenter(MyView view){
        this.view = view;
    }
    public void show(String url){
        model.show(url, new MyListener() {
            @Override
            public void onFailure(String msg) {
                view.onFailure(msg);
            }

            @Override
            public void onSuccess(String msg) {
                view.onSuccess(msg);
            }
        });
    }
}
