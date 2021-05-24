package com.example.goodlife.wjh.sethabit;

import com.example.goodlife.Utils;
import com.example.goodlife.wjh.MyListener;
import com.example.goodlife.wjh.MyView;
import com.example.goodlife.wjh.bean.Habit;

public class SetHabitPresenter {

    private MyView view;
    private SetHabitModel model = new SetHabitModel();

    public SetHabitPresenter(MyView view){
        this.view = view;
    }

    public void setHabit(String url, Habit habit){

        String json = Utils.gson.toJson(habit);

        model.setHabit(url, json, new MyListener() {
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
