package com.example.goodlife.wjh;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.goodlife.R;
import com.example.goodlife.wjh.adapter.GridViewAdapter;
import com.example.goodlife.wjh.bean.Habit;

import java.util.ArrayList;
import java.util.List;

public class FragmentOne extends Fragment {

    private View root;
    private LinearLayout ll_any;
    private LinearLayout ll_getUp;
    private LinearLayout ll_morning;

    private ImageView iv_getUp;
    private ImageView iv_morning;
    private TextView tv_getUpNumber;
    private TextView tv_morningNumber;

    private GridViewAdapter adapter;
    private GridView gvOne;
    private GridView gvTwo;
    private GridView gvThree;
    private GridView gvFour;
    private GridView gvFive;
    private GridView gvSix;
    private GridView gvSeven;
    private List<Habit> list;
    private TypedArray imgs;
    private TypedArray colors;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root =inflater.inflate(R.layout.fragment_one_page, container, false);
        list = new ArrayList<Habit>();
        imgs = getContext().getResources().obtainTypedArray(R.array.imgs);
        colors = getContext().getResources().obtainTypedArray(R.array.colors);
        findViews();
        setListener();
        initData(list);
        setGridAdapter(list, gvOne);
        setGridAdapter(list, gvTwo);
        return root;
    }

    private void initData(List<Habit> list) {
        Habit h = new Habit("习惯", 0,0);
        Habit h1 = new Habit("习惯", 1,1);
        list.add(h);
        list.add(h1);
    }

    private void setGridAdapter(List<Habit> list, GridView gv) {
        adapter = new GridViewAdapter(getContext(), list, R.layout.gridview_item, imgs, colors);
        gv.setAdapter(adapter);
    }

    private void findViews() {
        ll_any = root.findViewById(R.id.ll_any);
        ll_getUp = root.findViewById(R.id.ll_getUp);
        ll_morning = root.findViewById(R.id.ll_morning);
        iv_getUp = root.findViewById(R.id.iv_getUp);
        iv_morning = root.findViewById(R.id.iv_morning);
        tv_getUpNumber = root.findViewById(R.id.tv_getUpNumber);
        tv_morningNumber = root.findViewById(R.id.tv_morningNumber);
        gvOne = root.findViewById(R.id.gv_any);
        gvTwo = root.findViewById(R.id.gv_getUp);
        gvThree = root.findViewById(R.id.gv_morning);
    }

    private void setListener() {
        MyListener listener = new MyListener();
        ll_any.setOnClickListener(listener);
        ll_getUp.setOnClickListener(listener);
        ll_morning.setOnClickListener(listener);

    }

    private void onClickItem(ImageView iv, TextView tv, GridView gv){
        if(iv.getTag().equals("0")){
            iv.setImageResource(R.drawable.open);
            tv.setVisibility(View.VISIBLE);
            gv.setVisibility(View.GONE);
            tv.setText("余下"+"个");
            iv.setTag("1");
        }else{
            iv.setImageResource(R.drawable.close);
            tv.setVisibility(View.GONE);
            gv.setVisibility(View.VISIBLE);
            iv.setTag("0");
        }
    }

    class MyListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.ll_any:
                case R.id.ll_getUp:
                    onClickItem(iv_getUp, tv_getUpNumber, gvTwo);
                    break;
                case R.id.ll_morning:
                    onClickItem(iv_morning, tv_morningNumber, gvThree);
                    break;
            }
        }
    }
}
