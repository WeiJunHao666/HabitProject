package com.example.goodlife.zsl.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.goodlife.wjh.bean.Habit;

import java.util.List;

public class OverHabitAdapter extends BaseAdapter {
    private Context context;
    private List<Habit> habits;
    private int item_layout_id;
    private TypedArray head;
    private String[] colors;

    public OverHabitAdapter(Context context,List<Habit> habits,int item_layout_id,TypedArray head,String[] colors){
        this.context = context;
        this.habits = habits;
        this.item_layout_id = item_layout_id;
        this.head = head;
        this.colors = colors;
    }


    @Override
    public int getCount() {
        return habits.size();
    }

    @Override
    public Object getItem(int position) {
        return habits.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(item_layout_id, null);



        return convertView;
    }
}
