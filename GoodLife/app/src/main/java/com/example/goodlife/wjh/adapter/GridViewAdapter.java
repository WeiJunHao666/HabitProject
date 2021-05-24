package com.example.goodlife.wjh.adapter;

import android.annotation.SuppressLint;
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
import android.widget.TextView;

import com.example.goodlife.R;
import com.example.goodlife.Utils;
import com.example.goodlife.wjh.bean.Habit;
import com.example.goodlife.wjh.customview.ClokedInView;

import java.util.List;

import static com.example.goodlife.R.drawable.background_circular;


public class GridViewAdapter extends BaseAdapter {

    private Context context;
    private List<Habit> list;
    private int item_layout_id;

    public GridViewAdapter(Context context, List<Habit> list, int item_layout_id){
        this.context = context;
        this.list = list;
        this.item_layout_id = item_layout_id;
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView =inflater.inflate(item_layout_id, null);

        LinearLayout layout = convertView.findViewById(R.id.bg_time);
        ImageView img = convertView.findViewById(R.id.iv_timeImg);
        TextView txt = convertView.findViewById(R.id.tv_timeContent);
        ClokedInView view = convertView.findViewById(R.id.clocked_in);
        //修改circular.xml文件中的背景颜色
        GradientDrawable background = (GradientDrawable) context.getResources().getDrawable(background_circular);
        String color = Utils.getColor(context, R.array.colors, list.get(position).getColor());
        background.setColor(Color.parseColor(color));
        background.setStroke(6, Color.BLACK);
        layout.setBackgroundResource(background_circular);
        //
        img.setImageResource(Utils.getImageId(context, R.array.imgs, list.get(position).getIcon()));
        txt.setText(list.get(position).getName());
        return convertView;
    }
}
