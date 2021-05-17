package com.example.goodlife.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import com.example.goodlife.R;


import static com.example.goodlife.R.drawable.background_circular;


public class ColorGridViewAdapter extends BaseAdapter {

    private Context context;
    private String[] colors;
    private int item_layout_id;

    public ColorGridViewAdapter(Context context, String[] colors, int item_layout_id) {
        this.context = context;
        this.colors = colors;
        this.item_layout_id = item_layout_id;
    }

    @Override
    public int getCount() {
        return colors!=null ? colors.length : 0;
    }

    @Override
    public Object getItem(int position) {
        return colors!=null ? colors[position] : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(R.layout.item_gridview_color, null);

        LinearLayout layout = convertView.findViewById(R.id.bg_color);
        GradientDrawable background = (GradientDrawable) context.getResources().getDrawable(background_circular);
        String color = colors[position];
        background.setColor(Color.parseColor(color));

        layout.setBackgroundResource(background_circular);

        return convertView;
    }
}
