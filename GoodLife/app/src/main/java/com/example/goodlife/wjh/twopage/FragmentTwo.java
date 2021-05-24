package com.example.goodlife.wjh.twopage;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.goodlife.ConfigUtil;
import com.example.goodlife.R;
import com.example.goodlife.Utils;
import com.example.goodlife.wjh.ActivityAddHabit;
import com.example.goodlife.wjh.MyView;
import com.example.goodlife.wjh.adapter.SlideListViewAdapter;
import com.example.goodlife.wjh.bean.Habit;
import com.google.gson.reflect.TypeToken;

import java.util.List;


public class FragmentTwo extends Fragment implements MyView {

    private View root;
    private ImageView img_addBtn;
    private ListView listView;

    private SlideListViewAdapter adapter;
    private TwoPagePresenter presenter = new TwoPagePresenter(this);
    private List<Habit> list;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    String str = (String) msg.obj;
                    list = Utils.gson.fromJson(str, new TypeToken<List<Habit>>() {}.getType());
                    adapter = new SlideListViewAdapter(getContext(), list, presenter);
                    listView.setAdapter(adapter);
                    break;
                case 2:
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_two_page, container, false);
        findViews();
        presenter.show(ConfigUtil.TWOPAGE + Utils.getUserId(this.getContext()));

        return root;
    }

    private void findViews() {
        img_addBtn = root.findViewById(R.id.img_addBtn);
        listView = root.findViewById(R.id.listview_all);

        img_addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityAddHabit.class);
                intent.putExtra("flag", 1);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public void onFailure(String message) {
//        Looper.prepare();
//        Toast.makeText(getContext().getApplicationContext(), message, Toast.LENGTH_SHORT).show();
//        Looper.loop();
    }

    @Override
    public void onSuccess(String data) {
        Message message = new Message();
        message.what = 1;
        message.obj = data;
        handler.sendMessage(message);
    }

    @Override
    public void onSuccess2(String data) {
        Message message = new Message();
        message.what = 2;
        message.obj = data;
        handler.sendMessage(message);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.show(ConfigUtil.TWOPAGE+Utils.getUserId(getContext().getApplicationContext()));
    }
}
