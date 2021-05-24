package com.example.goodlife.wjh.homepage;

import com.example.goodlife.wjh.MyListener;
import com.example.goodlife.wjh.MyView;

public class HomePagePresenter {

    private MyView view;
    private HomePageModel model = new HomePageModel();

    public HomePagePresenter(MyView view){
        this.view = view;
    }

    public void show(String url){
        model.show(url, new MyListener(){

            @Override
            public void onSuccess(String msg) {
                view.onSuccess(msg);
            }

            @Override
            public void onFailure(String msg) {
                view.onFailure(msg);
            }
        });
    }
}
