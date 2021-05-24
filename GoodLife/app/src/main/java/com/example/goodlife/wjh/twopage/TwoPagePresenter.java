package com.example.goodlife.wjh.twopage;

import com.example.goodlife.wjh.MyListener;
import com.example.goodlife.wjh.MyView;
import com.example.goodlife.wjh.twopage.TwoPageModel;

public class TwoPagePresenter {

    private MyView view;
    private TwoPageModel model = new TwoPageModel();

    public TwoPagePresenter(MyView view){
        this.view = view;
    }

    public void show(String url){
        model.show(url, new MyListener() {
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

    public void delete(String url){
        model.delete(url, new MyListener() {
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
