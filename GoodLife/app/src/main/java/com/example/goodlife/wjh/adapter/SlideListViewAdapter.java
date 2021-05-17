package com.example.goodlife.wjh.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.BaseSwipeAdapter;
import com.example.goodlife.wjh.ActivityHabitDetail;
import com.example.goodlife.R;
import com.example.goodlife.wjh.bean.Habit;

import java.util.List;


public class SlideListViewAdapter extends BaseSwipeAdapter implements View.OnClickListener{

    private Context context;
    private List<Habit> list;
    private final String TAG = "MySwipeAdapter";
    private TypedArray imgs;
    private String[] colors;


    public SlideListViewAdapter(Context context, List<Habit>list, TypedArray imgs, String[] colors) {
        this.context = context;
        this.list = list;
        this.imgs = imgs;
        this.colors = colors;
    }
    /**
     * 返回SwipeLayout的ID
     *
     * @param position
     * @return
     */
    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }
    /**
     * 绑定布局
     *
     * @param position
     * @param parent
     * @return
     */
    @Override
    public View generateView(final int position, ViewGroup parent) {
        View itemView = View.inflate(context, R.layout.listview_item_all_habit, null);
        SwipeLayout swipeLayout = (SwipeLayout) itemView.findViewById(getSwipeLayoutResourceId(position));
        // 设置滑动是否可用,默认为true
        swipeLayout.setSwipeEnabled(true);
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
                Log.e(TAG, "onOpen: " + position);
            }
            @Override
            public void onStartClose(SwipeLayout layout) {
                //Log.e(TAG, "onStartClose: ");
            }
            @Override
            public void onClose(SwipeLayout layout) {
                list.get(position).setOpen(false);
                Log.e(TAG, "onClose: " + position);
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
        // 设置为true,在当前一条item(除侧滑以外部分)点击时,可收回侧滑出来部分,默认为false
        swipeLayout.setClickToClose(true);
        // SwipeLayout单击事件,可替代ListView的OnitemClickListener事件.
//        swipeLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.e(TAG, "onClick: ");
//                Toast.makeText(context, "kkk", LENGTH_SHORT).show();
//            }
//        });
        return itemView;
    }
    /**
     * 绑定数据
     *
     * @param position
     * @param convertView
     */
    @Override
    public void fillValues(final int position, final View convertView) {
        RelativeLayout bg_all = convertView.findViewById(R.id.bg_all);
        ImageView icon = convertView.findViewById(R.id.img_all_icon);
        TextView txt_name = convertView.findViewById(R.id.txt_all_name);
        TextView txt_day = convertView.findViewById(R.id.txt_all_day);
        LinearLayout swipeEdit =  convertView.findViewById(R.id.swipe_edit);
        LinearLayout swipeDelete =  convertView.findViewById(R.id.swipe_delete);

        GradientDrawable background = (GradientDrawable) context.getResources().getDrawable(R.drawable.background_all);
        String color = colors[list.get(position).getColor()];
        background.setColor(Color.parseColor(color));
        background.setStroke(2, Color.BLACK);

        icon.setImageResource(imgs.getResourceId(list.get(position).getIcon(),-1));
        txt_name.setText(list.get(position).getName());
        bg_all.setBackgroundResource(R.drawable.background_all);

        swipeDelete.setOnClickListener(this);
        swipeEdit.setOnClickListener(this);

        bg_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isOpenOrClose(list)){
                    closeAllItems();
                    Log.e(TAG, "不进入");
                }else{
                    Log.e(TAG, "进入");
                    Intent intent = new Intent(context, ActivityHabitDetail.class);
                    context.startActivity(intent);
                }
            }
        });

    }

    private boolean isOpenOrClose(List<Habit> list) {
        for(int i=0; i<list.size(); i++){
            if(list.get(i).isOpen()){
                return true;
            }
        }
        return false;
    }

    @Override
    public int getCount() {
        return list.size();
    }
    @Override
    public Habit getItem(int i) {
        return list.get(i);
    }
    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.swipe_edit:
                // 关闭所有打开的Swipe的item
                this.closeAllItems();
                Toast.makeText(context, "Swipe--Edit", Toast.LENGTH_SHORT).show();
                break;
            case R.id.swipe_delete:
                this.closeAllItems();
                Toast.makeText(context, "Swipe--Delete", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
