package com.example.goodlife.wjh.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goodlife.R;
import com.example.goodlife.wjh.bean.Remind;

import java.util.List;

public class RecyclerRemindAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Remind> dataSource;
    private View inflater;
    private int flag2;

    public RecyclerRemindAdapter(Context context, List<Remind> dataSource, int flag2) {
        this.context = context;
        this.dataSource = dataSource;
        this.flag2 = flag2;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(context).inflate(R.layout.recyclerview_item_remind, parent, false);
        ViewHolder viewHolder = new ViewHolder(inflater);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.txt.setText(dataSource.get(position).getTime());
        int flag = dataSource.get(position).getFlag();
        if(flag2 == 1){
            viewHolder.layout.setVisibility(View.VISIBLE);
        }
        viewHolder.aSwitch.setChecked(false);
    }

    @Override
    public int getItemCount() {
        return dataSource!=null ? dataSource.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView txt;
        Switch aSwitch;
        View layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt = itemView.findViewById(R.id.txt_remind_time);
            aSwitch = itemView.findViewById(R.id.switch_button);
            layout = itemView.findViewById(R.id.view_bottom_line);
        }
    }
}
