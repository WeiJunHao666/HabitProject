package com.example.goodlife.wjh.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.BaseSwipeAdapter;
import com.example.goodlife.Utils;
import com.example.goodlife.wjh.detail.ActivityHabitDetail;
import com.example.goodlife.R;
import com.example.goodlife.wjh.bean.Habit;
import com.example.goodlife.wjh.sethabit.ActivitySetHabit;
import com.example.goodlife.wjh.twopage.TwoPagePresenter;

import java.io.Serializable;
import java.util.List;



public class SlideListViewAdapter extends BaseSwipeAdapter implements View.OnClickListener{

    private Context context;
    private List<Habit> list;
    private TwoPagePresenter presenter;

    private int pos;


    public SlideListViewAdapter(Context context, List<Habit>list, TwoPagePresenter presenter) {
        this.context = context;
        this.list = list;
        this.presenter = presenter;
    }
    /**
     * 返回SwipeLayout的ID
     * @param position
     * @return
     */
    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }
    /**
     * 绑定布局
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
                //Log.e(TAG, "onOpen: " + position);
            }
            @Override
            public void onStartClose(SwipeLayout layout) {
                //Log.e(TAG, "onStartClose: ");
            }
            @Override
            public void onClose(SwipeLayout layout) {
                list.get(position).setOpen(false);
                //Log.e(TAG, "onClose: " + position);
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
    /**
     * 绑定数据
     *
     * @param position
     * @param convertView
     */
    @Override
    public void fillValues(final int position, final View convertView) {

        //绑定控件
        RelativeLayout bg_all = convertView.findViewById(R.id.bg_all);
        ImageView icon = convertView.findViewById(R.id.img_all_icon);
        TextView txt_name = convertView.findViewById(R.id.txt_all_name);
        TextView txt_day = convertView.findViewById(R.id.txt_all_day);
        LinearLayout swipeEdit =  convertView.findViewById(R.id.swipe_edit);
        LinearLayout swipeDelete =  convertView.findViewById(R.id.swipe_delete);

        //设置控件属性
        String color = Utils.getColor(context, R.array.colors, list.get(position).getColor());
        int imgId = Utils.getImageId(context, R.array.imgs, list.get(position).getIcon());
        Utils.setBackgroundChecked(context, color, bg_all, 5);
        icon.setImageResource(imgId);
        txt_name.setText(list.get(position).getName());

        //绑定点击监听器
        swipeDelete.setOnClickListener(this);
        swipeEdit.setOnClickListener(this);
        bg_all.setOnClickListener(this);


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

                Intent intent1 = new Intent(context, ActivitySetHabit.class);
                intent1.putExtra("flag", 0);
                intent1.putExtra("color", list.get(pos).getColor());
                intent1.putExtra("icon", list.get(pos).getIcon());
                intent1.putExtra("name", list.get(pos).getName());
                intent1.putExtra("habitId", list.get(pos).getHabitId());
//                ArrayList<RemindTimes> array = new ArrayList<>();
                intent1.putExtra("list", (Serializable) list.get(pos).getRemindTimes());
                context.startActivity(intent1);

                //Toast.makeText(context, "Swipe--Edit", Toast.LENGTH_SHORT).show();
                break;
            case R.id.swipe_delete:
                //服务器删除该习惯
                //presenter.delete(ConfigUtil.DELETE);

                //list删除该习惯并更新adapter
                list.remove(pos);
                notifyDataSetChanged();

                //关闭所有打开的item
                this.closeAllItems();
                Toast.makeText(context, "Swipe--Delete", Toast.LENGTH_SHORT).show();
                break;
            case R.id.bg_all:
                if(isOpenOrClose(list)){
                    //Log.e(TAG, "不进入");
                    closeAllItems();
                }else{
                    //Log.e(TAG, "进入");
                    Intent intent = new Intent(context, ActivityHabitDetail.class);
                    context.startActivity(intent);
                }
                break;
        }
    }

    private boolean isOpenOrClose(List<Habit> list) {
        for(int i=0; i<list.size(); i++){
            if(list.get(i).isOpen()){
                return true;
            }
        }
        return false;
    }
}
