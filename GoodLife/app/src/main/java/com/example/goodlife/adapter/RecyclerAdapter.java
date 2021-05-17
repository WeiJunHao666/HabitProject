package com.example.goodlife.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goodlife.R;
import com.example.goodlife.bean.Habit;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Habit> dataSource;
    private View inflater;
    private TypedArray imgs;
    private String[] colors;

    public RecyclerAdapter(Context context, List<Habit> dataSource, TypedArray imgs, String[] colors){
        this.context = context;
        this.dataSource = dataSource;
        this.imgs = imgs;
        this.colors = colors;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(context).inflate(R.layout.recyclerview_item_daily, parent, false);
        ViewHolder viewHolder = new ViewHolder(inflater);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.txt_name.setText(dataSource.get(position).getName());
        //修改circular.xml文件中的背景颜色
        GradientDrawable background = (GradientDrawable) context.getResources().getDrawable(R.drawable.background_circular);
        String color = colors[dataSource.get(position).getColor()];
        background.setColor(Color.parseColor(color));
        background.setStroke(6,Color.BLACK);
        //设置img的icon
        viewHolder.img_daily.setImageResource(imgs.getResourceId(dataSource.get(position).getIcon(), -1));

    }

    @Override
    public int getItemCount() {
        if(dataSource!=null){
            return dataSource.size();
        }
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView txt_time;
        LinearLayout bg_daily;
        ImageView img_daily;
        TextView txt_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_time = itemView.findViewById(R.id.txt_daily_time);
            txt_name = itemView.findViewById(R.id.txt_daily_name);
            img_daily = itemView.findViewById(R.id.img_daily);
            bg_daily = itemView.findViewById(R.id.bg_daily);
        }
    }
}
