package com.example.goodlife.wjh.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.goodlife.wjh.sethabit.ActivitySetHabit;
import com.example.goodlife.R;
import com.example.goodlife.wjh.bean.Habit;

import java.util.List;

import static com.example.goodlife.R.drawable.background_circular;

public class AddGridViewAdapter extends BaseAdapter {

    private Context context;
    private List<Habit> dataSource;
    private int item_layout_id;
    private TypedArray imgs;
    private String[] colors;

    public AddGridViewAdapter(Context context, List<Habit> dataSource, int item_layout_id, TypedArray imgs, String[] colors) {
        this.context = context;
        this.dataSource = dataSource;
        this.item_layout_id = item_layout_id;
        this.imgs = imgs;
        this.colors = colors;
    }

    @Override
    public int getCount() {
        return dataSource!=null ? dataSource.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return dataSource!=null ? dataSource.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(item_layout_id, null);

        LinearLayout layout = convertView.findViewById(R.id.bg_addHabit);
        ImageView img = convertView.findViewById(R.id.img_addHabit);
        TextView txt = convertView.findViewById(R.id.txt_addHabit);

        //修改circular.xml文件中的背景颜色
        GradientDrawable background = (GradientDrawable) context.getResources().getDrawable(background_circular);
        final String color = colors[dataSource.get(position).getColor()];
        background.setColor(Color.parseColor(color));
        background.setStroke(6, Color.BLACK);

        layout.setBackgroundResource(background_circular);
        img.setImageResource(imgs.getResourceId(dataSource.get(position).getIcon(), -1));
        txt.setText(dataSource.get(position).getName());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ActivitySetHabit.class);
                context.startActivity(intent);
            }
        });

        return convertView;
    }
}
