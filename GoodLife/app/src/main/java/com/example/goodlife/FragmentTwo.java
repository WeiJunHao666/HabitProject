package com.example.goodlife;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.goodlife.adapter.SlideListViewAdapter;
import com.example.goodlife.bean.Habit;

import java.util.ArrayList;
import java.util.List;


public class FragmentTwo extends Fragment {

    private View root;

    private ImageView img_addBtn;
    //private RecyclerView recyclerView;
    private ListView listView;

    //private RecyclerAllHabitAdapter adapter;
    private SlideListViewAdapter adapter;
    private List<Habit> list;
    private TypedArray imgs;
    private String[] colors;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_two_page, container, false);
        imgs = getContext().getResources().obtainTypedArray(R.array.imgs);
        colors = getContext().getResources().getStringArray(R.array.colors);

        findViews();
        initData();
        setRecyclerAdapter();

        img_addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityAddHabit.class);
                startActivity(intent);
            }
        });

        return root;
    }

    private void initData() {
        list = new ArrayList<>();
        Habit h = new Habit("习惯", 0,0);
        Habit h1 = new Habit("习惯", 1,1);
        Habit h2 = new Habit("习惯", 1,1);
        Habit h3 = new Habit("习惯", 1,1);
        Habit h4 = new Habit("习惯", 1,1);
        Habit h5 = new Habit("习惯", 1,1);
        list.add(h);
        list.add(h1);
        list.add(h2);
        list.add(h3);
        list.add(h4);
        list.add(h5);
    }

    private void setRecyclerAdapter() {

//        LinearLayoutManager manager = new LinearLayoutManager(this.getContext());
//        manager.setOrientation(RecyclerView.VERTICAL);
//        recyclerView.addItemDecoration(new SpacesItemDecoraction(20));
//        recyclerView.setLayoutManager(manager);

        adapter = new SlideListViewAdapter(this.getContext(), list, imgs, colors);
        listView.setAdapter(adapter);
    }

    private void findViews() {
        img_addBtn = root.findViewById(R.id.img_addBtn);
        //recyclerView = root.findViewById(R.id.recycler_all);
        listView = root.findViewById(R.id.listview_all);
    }

}
