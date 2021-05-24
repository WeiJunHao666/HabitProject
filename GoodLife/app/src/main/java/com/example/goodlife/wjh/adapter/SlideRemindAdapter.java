package com.example.goodlife.wjh.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.BaseSwipeAdapter;
import com.example.goodlife.R;
import com.example.goodlife.Utils;
import com.example.goodlife.wjh.bean.RemindTimes;

import java.util.List;

public class SlideRemindAdapter extends BaseSwipeAdapter {

    private Context context;
    private List<RemindTimes> list;
    private int flag2;
    private int pos;
    private TextView txt;

    public SlideRemindAdapter(Context context, List<RemindTimes> list, int flag2){
        this.context = context;
        this.list = list;
        this.flag2 = flag2;
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipeRemind;
    }

    @Override
    public View generateView(final int position, ViewGroup parent) {
        View itemView = View.inflate(context, R.layout.listview_item_remind, null);
        SwipeLayout swipeLayout = (SwipeLayout) itemView.findViewById(getSwipeLayoutResourceId(position));

        // 设置滑动是否可用,默认为true
        swipeLayout.setSwipeEnabled(true);
        // 设置为true,在当前一条item(除侧滑以外部分)点击时,可收回侧滑出来部分,默认为false
        swipeLayout.setClickToClose(true);

        /**
         * 打开时调用：循环调用onStartOpen,onUpdate,onHandRelease,onUpdate,onOpen,
         * 关闭时调用：onStartClose,onUpdate,onHandRelease,onHandRelease,onUpdate,onClose
         */
        swipeLayout.addSwipeListener(new SwipeLayout.SwipeListener() {
            @Override
            public void onStartOpen(SwipeLayout layout) {
                //Log.e(TAG, "onStartOpen: ");
            }
            @Override
            public void onOpen(SwipeLayout layout) {
                list.get(position).setOpen(true);
                pos = position;
                //Log.e("qqq", "onOpen: " + position);
            }
            @Override
            public void onStartClose(SwipeLayout layout) {
                //Log.e(TAG, "onStartClose: ");
            }
            @Override
            public void onClose(SwipeLayout layout) {
                list.get(position).setOpen(false);
                //Log.e("qqq", "onClose: " + position);
            }
            @Override
            public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {
                //Log.e(TAG, "onUpdate: ");
            }
            @Override
            public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {
                //Log.e(TAG, "onHandRelease: ");
            }
        });

        return itemView;
    }

    @Override
    public void fillValues(final int position, View convertView) {
        //绑定控件
        txt = convertView.findViewById(R.id.txt_remind_time);
        Switch aSwitch = convertView.findViewById(R.id.switch_button);
        View layout = convertView.findViewById(R.id.view_bottom_line);
        LinearLayout swipeDelete =  convertView.findViewById(R.id.swipe_delete);
        RelativeLayout bg_remind = convertView.findViewById(R.id.bg_remind);

        //设置控件属性
        txt.setText(list.get(position).getTime());
        int flag = list.get(position).getFlag();
        if(flag2 == 1){
            layout.setVisibility(View.VISIBLE);
        }
        aSwitch.setChecked(true);

        swipeDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //list删除该习惯并更新adapter
                list.remove(pos);
                notifyDataSetChanged();
                //关闭所有打开的item
                closeAllItems();
            }
        });
        bg_remind.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if(isOpenOrClose(list)){
                    //Log.e(TAG, "不进入");
                    closeAllItems();
                }else{
                    //Log.e("qqq", "进入");
                    createDialogTimePicker(position);
                }
            }
        });
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return list != null ? list.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private boolean isOpenOrClose(List<RemindTimes> list) {
        for(int i=0; i<list.size(); i++){
            if(list.get(i).isOpen()){
                return true;
            }
        }
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void createDialogTimePicker(final int position){
        final AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        View view = View.inflate(context, R.layout.dialog_timepicker, null);
        dialog.setView(view)
                .setCancelable(false)
                .create();
        final AlertDialog show = dialog.show();

        Button cancel = view.findViewById(R.id.btn_remind_cancel);
        Button save = view.findViewById(R.id.btn_remind_save);
        final TimePicker timePicker = view.findViewById(R.id.timePicker);

        //设置当前时间
        String []time = Utils.getCurrentTime().split(":");
        timePicker.setHour(Integer.parseInt(time[0]));
        timePicker.setMinute(Integer.parseInt(time[1]));

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show.dismiss();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                int hour = timePicker.getHour();
                int minute = timePicker.getMinute();
                String time;
                if(minute<10){
                    time = hour + ":0" + minute;
                }else{
                    time = hour + ":" + minute;
                }
                txt.setText(time);
                list.get(position).setTime(time);
                show.dismiss();
            }
        });
    }
}
