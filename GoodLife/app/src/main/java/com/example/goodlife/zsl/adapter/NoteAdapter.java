package com.example.goodlife.zsl.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.goodlife.R;
import com.example.goodlife.wjh.bean.Habit;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.goodlife.R.drawable.background_circular;
import static com.example.goodlife.R.drawable.head;
import static com.example.goodlife.R.drawable.note_item_background;

public class NoteAdapter extends BaseAdapter {
    private Context context;
    private List<Habit> list;
    private int item_layout_id;
    private TypedArray head;
    private String[] colors;

    public NoteAdapter(Context context,List<Habit> list,int item_layout_id,TypedArray head,String[] colors){
        this.context = context;
        this.list = list;
        this.item_layout_id = item_layout_id;
        this.head = head;
        this.colors = colors;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(null == convertView){
            //加载列表项布局文件
            LayoutInflater mInflater = LayoutInflater.from(context);
            convertView = mInflater.inflate(item_layout_id,null);
        }
        LinearLayout layout = convertView.findViewById(R.id.note_page_item_background);
        CircleImageView habit_head = convertView.findViewById(R.id.habit_head);
        TextView habit_name = convertView.findViewById(R.id.habit_name);
        TextView habit_note_number = convertView.findViewById(R.id.habit_note_number);


        GradientDrawable background = (GradientDrawable) context.getResources().getDrawable(note_item_background);
        final String color = colors[list.get(position).getColor()];
        background.setColor(Color.parseColor(color));
        background.setStroke(6, Color.BLACK);

        layout.setBackgroundResource(note_item_background);
        habit_head.setImageResource(head.getResourceId(list.get(position).getIcon(), -1));
        habit_name.setText( list.get(position).getName());
        //habit_note_number.setText(list.get(position).getColor());
        return convertView;
    }
}
