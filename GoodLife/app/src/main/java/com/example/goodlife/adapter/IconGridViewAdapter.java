package com.example.goodlife.adapter;

import android.content.Context;
import android.content.res.TypedArray;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.goodlife.R;

public class IconGridViewAdapter extends BaseAdapter {
    private Context context;
    private TypedArray imgs;
    private int item_layout_id;

    public IconGridViewAdapter(Context context, TypedArray imgs, int item_layout_id) {
        this.context = context;
        this.imgs = imgs;
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

        return convertView;
    }
}
