package com.example.goodlife.zsl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.goodlife.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class NoteAdapter extends BaseAdapter {
    private Context context;
    private List list;
    private int item_layout_id;

    public NoteAdapter(Context context,List list,int item_layout_id){
        this.context = context;
        this.list = list;
        this.item_layout_id = item_layout_id;
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

        CircleImageView habit_head = convertView.findViewById(R.id.habit_head);
        TextView habit_name = convertView.findViewById(R.id.habit_name);
        TextView habit_note_number = convertView.findViewById(R.id.habit_note_number);


        return convertView;
    }
}
