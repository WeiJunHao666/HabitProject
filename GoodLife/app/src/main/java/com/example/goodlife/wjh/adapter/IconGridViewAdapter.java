package com.example.goodlife.wjh.adapter;

import android.content.Context;
import android.content.res.TypedArray;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.goodlife.R;
import com.example.goodlife.Utils;

public class IconGridViewAdapter extends BaseAdapter {
    private Context context;
    private TypedArray imgs;
    private int item_layout_id;
    private String color;
    private boolean[] flag;

    public IconGridViewAdapter(Context context, TypedArray imgs, boolean[] flag, String color, int item_layout_id) {
        this.context = context;
        this.imgs = imgs;
        this.flag = flag;
        this.color = color;
        this.item_layout_id = item_layout_id;
    }

    @Override
    public int getCount() {
        return imgs!=null ? imgs.length() : 0;
    }

    @Override
    public Object getItem(int position) {
        return imgs!=null ? imgs.getResourceId(position, -1) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(R.layout.item_gridview_icon, null);

        LinearLayout layout = convertView.findViewById(R.id.bg_set_icon);
        ImageView img = convertView.findViewById(R.id.img_set_icon);

        img.setImageResource(imgs.getResourceId(position, -1));

        if(flag[position] == true){

            GradientDrawable drawable = new GradientDrawable();
            drawable.setShape(GradientDrawable.RECTANGLE);
            drawable.setStroke(Utils.dip2px(context,2), Color.BLACK);
            drawable.setColor(Color.parseColor(color));
            drawable.setCornerRadius(Utils.dip2px(context, 8));
            drawable.setSize(50, 50);
            layout.setBackground(drawable);
        }

        return convertView;
    }
}
