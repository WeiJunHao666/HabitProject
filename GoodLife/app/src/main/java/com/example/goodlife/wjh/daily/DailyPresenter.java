package com.example.goodlife.wjh.daily;

import com.example.goodlife.wjh.MyListener;
import com.example.goodlife.wjh.MyView;

public class DailyPresenter {

    private MyView view;
    private DailyModel model = new DailyModel();

    public DailyPresenter(MyView view){
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
