package com.example.goodlife.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goodlife.R;
import com.example.goodlife.Utils;
import com.example.goodlife.bean.Diary;

import java.util.List;


public class RecyclerRecordAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private View inflater;
    private Context context;
    private List<Diary> dataSource;

    public RecyclerRecordAdapter(Context context, List<Diary> dataSource) {
        this.context = context;
        this.dataSource = dataSource;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(context).inflate(R.layout.recyclerview_item_detail_habit,  parent, false);
        ViewHolder viewHolder = new ViewHolder(inflater);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.img.setImageResource(Utils.getImageId(context, R.array.imgs,
                dataSource.get(position).getIcon()));
        viewHolder.txt_date.setText(dataSource.get(position).getDate());
        viewHolder.txt_time.setText(dataSource.get(position).getTime());
        viewHolder.txt_content.setText(dataSource.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return dataSource!=null ? dataSource.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView txt_date;
        TextView txt_time;
        TextView txt_content;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_record_detail);
            txt_date = itemView.findViewById(R.id.txt_record_date);
            txt_time = itemView.findViewById(R.id.txt_record_time);
            txt_content = itemView.findViewById(R.id.txt_record_content);
        }
    }
}
