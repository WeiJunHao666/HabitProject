package com.example.goodlife.wjh.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.goodlife.R;
import com.example.goodlife.wjh.bean.Habit;

import java.util.List;


public class GridViewAdapter extends BaseAdapter {

    private Context context;
    private List<Habit> list;
    private int item_layout_id;
    private TypedArray imgs;
    private TypedArray colors;
    public GridViewAdapter(Context context, List<Habit> list, int item_layout_id, TypedArray imgs, TypedArray colors){
        this.context = context;
        this.list = list;
        this.item_layout_id = item_layout_id;
        this.imgs = imgs;
        this.colors = colors;
    }

    @Override
    public int getCount() {
        if(list!=null){
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if(list!=null){
            return list.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView =inflater.inflate(item_layout_id, null);

        LinearLayout bg = convertView.findViewById(R.id.bg_time);
        ImageView img = convertView.findViewById(R.id.iv_timeImg);
        TextView txt = convertView.findViewById(R.id.tv_timeContent);

        bg.setBackgroundColor(colors.getResourceId(list.get(position).getColor(), -1));
        img.setImageResource(imgs.getResourceId(list.get(position).getImg(), -1));
        txt.setText(list.get(position).getName());

        return convertView;
    }
}
