package com.example.goodlife.wjh.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import com.example.goodlife.R;
import com.example.goodlife.Utils;


import static com.example.goodlife.R.drawable.background_circular;


public class ColorGridViewAdapter extends BaseAdapter {

    private Context context;
    private String[] colors;
    private boolean[] flag;
    private int item_layout_id;

    public ColorGridViewAdapter(Context context, String[] colors, boolean[] flag, int item_layout_id) {
        this.context = context;
        this.colors = colors;
        this.flag = flag;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(R.layout.item_gridview_color, null);

        final LinearLayout layout = convertView.findViewById(R.id.bg_color);

        if(flag[position] == false){
            String color = Utils.getColor(context, R.array.colors, position);
            Log.e("c", color);
            Utils.setBackground(context, background_circular, layout, color, "#111111", 0);
        }else{
            String color = Utils.getColor(context, R.array.colors, position);
            GradientDrawable drawable = new GradientDrawable();
            drawable.setShape(GradientDrawable.OVAL);
            drawable.setStroke(Utils.dip2px(context,2), Color.BLACK);
            drawable.setColor(Color.parseColor(color));
            layout.setBackground(drawable);
        }

        return convertView;
    }
}
