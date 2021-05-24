package com.example.goodlife.wjh.homepage;


import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.goodlife.ConfigUtil;
import com.example.goodlife.R;
import com.example.goodlife.Utils;
import com.example.goodlife.wjh.customview.ClokedInView;
import com.example.goodlife.wjh.daily.ActivityDaily;
import com.example.goodlife.wjh.detail.ActivityHabitDetail;
import com.example.goodlife.wjh.MyView;
import com.example.goodlife.wjh.adapter.GridViewAdapter;
import com.example.goodlife.wjh.bean.Habit;
import com.example.goodlife.wjh.kindview.MyGridView;
import com.example.goodlife.wjh.customview.HabitView;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;


public class FragmentOne extends Fragment implements MyView {

    private View root;

    private LinearLayout ll_any;
    private LinearLayout ll_getUp;
    private LinearLayout ll_morning;
    private LinearLayout ll_noon;
    private LinearLayout ll_evening;
    private LinearLayout ll_night;
    private LinearLayout ll_bed;

    private ImageView img_getCard;
    private ImageView img_daily;


    private List<Habit> list;
    private List<Habit> list1;
    private List<Habit> list2;
    private List<Habit> list3;
    private List<Habit> list4;
    private List<Habit> list5;
    private List<Habit> list6;
    private List<Habit> list7;

    private GridViewAdapter adapter;
    private PopupWindow popupWindow;
    private Context mContext;

    private HomePagePresenter presenter;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    String str = (String)msg.obj;
                    list = Utils.gson.fromJson(str, new TypeToken<List<Habit>>() {}.getType());
                    //设置所有List的内容
                    setAllList();
                    //
                    setJudge("任意时间", ll_any, list1);
                    setJudge("起床后", ll_getUp, list2);
                    setJudge("晨间", ll_morning, list3);
                    setJudge("午间", ll_noon, list4);
                    setJudge("傍晚", ll_evening, list5);
                    setJudge("晚间", ll_night, list6);
                    setJudge("睡前", ll_bed, list7);

                    break;
            }
        }
    };



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        root =inflater.inflate(R.layout.fragment_one_page, container, false);

        findViews();
        initData();
        setListener();

        presenter = new HomePagePresenter(this);
        presenter.show(ConfigUtil.HOMEPAGE+Utils.getUserId(this.getContext()));

        return root;
    }

    private void initData() {
        list = new ArrayList<Habit>();
        list1 = new ArrayList<Habit>();
        list2 = new ArrayList<Habit>();
        list3 = new ArrayList<Habit>();
        list4 = new ArrayList<Habit>();
        list5 = new ArrayList<Habit>();
        list6 = new ArrayList<Habit>();
        list7 = new ArrayList<Habit>();
    }

    private void findViews() {
        ll_any = root.findViewById(R.id.ll_any);
        ll_getUp = root.findViewById(R.id.ll_getUp);
        ll_morning = root.findViewById(R.id.ll_morning);
        ll_noon = root.findViewById(R.id.ll_noon);
        ll_evening = root.findViewById(R.id.ll_evening);
        ll_night= root.findViewById(R.id.ll_night);
        ll_bed = root.findViewById(R.id.ll_bed);

        img_getCard = root.findViewById(R.id.img_getCard);
        img_daily = root.findViewById(R.id.img_daily);

    }

    /**
     * 顶部按钮监听器
     */
    private void setListener() {

        img_getCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), ActivityLogin.class);
//                startActivity(intent);
            }
        });

        img_daily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityDaily.class);
                startActivity(intent);
            }
        });

    }

    /**
     * GridView设置设配器和item的监听事件
     * @param list
     * @param gv
     */
    private void setGridAdapter(final List<Habit> list, final GridView gv) {
        adapter = new GridViewAdapter(getContext(), list, R.layout.gridview_item);
        gv.setAdapter(adapter);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ViewGroup viewGroup = (ViewGroup) view;
                ViewGroup viewGroup1 = (ViewGroup) viewGroup.getChildAt(0);
                LinearLayout layout = (LinearLayout) viewGroup1.getChildAt(0);
                HabitView habitView = (HabitView) viewGroup1.getChildAt(1);
                ClokedInView clokedInView = (ClokedInView) viewGroup1.getChildAt(2);

                if (clokedInView.getTag().equals("1")){
                    habitView.setTag("1");
                }

                if(habitView.getTag().equals("0")){
                    layout.setAlpha(0.3f);
//                    layout.setElevation(0);
                    habitView.showAnim();
                    view.setFocusable(true);
                    habitView.setTag("1");
                    habitView.setVisibility(View.VISIBLE);
                    showBottom(view, list, position);
                }else {
                    if(clokedInView.getTag().equals("1")){
                        clokedInView.setVisibility(View.GONE);
                        clokedInView.setTag("0");
                    }else{
                        createDialogCancel(habitView, layout);
                    }
                }
            }
        });
    }

    /**
     * 取消打卡弹出框
     */
    private void createDialogCancel(final HabitView habitView, final LinearLayout layout) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view1 = View.inflate(getContext(), R.layout.dialog_cancel, null);
        Button btn_cancel = view1.findViewById(R.id.btn_cancel);
        Button btn_yes = view1.findViewById(R.id.btn_yes);

        builder.setView(view1)
                .setCancelable(false)
                .create();
        final AlertDialog dialog = builder.create();
        Window window = dialog.getWindow();
        dialog.show();

        window.setBackgroundDrawableResource(R.drawable.background_dialog_cancel);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = Utils.getDisplayMetrics(this.getContext()).widthPixels/4*3;
        window.setAttributes(lp);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                layout.setAlpha(1f);

                habitView.setTag("0");
                habitView.setVisibility(View.GONE);
                dialog.dismiss();
            }
        });
    }

    /**
     * 底部打卡日志弹出框
     */
    private void showBottom(final View view, final List<Habit> list, final int position) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                View vPopupWindow = View.inflate(mContext, R.layout.dialog_bottom, null);//引入弹窗布局
                popupWindow = new PopupWindow(vPopupWindow, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
                //设置背景透明
                addBackground();
                //设置进出动画
                popupWindow.setAnimationStyle(R.style.BottomPopupWindowAnimation);
                //引入依附的布局
                View parentView = LayoutInflater.from(mContext).inflate(R.layout.fragment_one_page, null);
                //相对于父控件的位置（例如正中央Gravity.CENTER，下方Gravity.BOTTOM等），可以设置偏移或无偏移
                popupWindow.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);

                RelativeLayout layout = vPopupWindow.findViewById(R.id.layout_dialog_bottom);
                TextView txt_time = vPopupWindow.findViewById(R.id.txt_time);
                TextView txt_habit = vPopupWindow.findViewById(R.id.txt_habit);
                ImageView img_detail = vPopupWindow.findViewById(R.id.img_detail);
                ImageView img_habit = vPopupWindow.findViewById(R.id.img_habit);
                EditText editText = vPopupWindow.findViewById(R.id.et_journl);
                Button btn = vPopupWindow.findViewById(R.id.btn_finish);


                txt_time.setText(Utils.getCurrentTime());
                txt_habit.setText(list.get(position).getName());
                img_habit.setImageResource(Utils.getImageId(getContext(), R.array.imgs,
                        list.get(position).getIcon()));
                String color = Utils.getColor(getContext(), R.array.colors, list.get(position).getColor());
                Utils.setBackground(getContext(), R.drawable.shape_corner_top, layout, color, "#111111", 0 );
                Utils.setBackground(getContext(), R.drawable.background_bottom_yes, btn, color, "#c1a173", 2);

                img_detail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), ActivityHabitDetail.class);
                        startActivity(intent);
                    }
                });

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

                view.setFocusable(false);
            }
        },1000);
    }

    /**
     * 设置底部打卡日志弹出框以外背景
     */
    private void addBackground() {
        // 设置背景颜色变暗
        WindowManager.LayoutParams lp = getActivityByContext(mContext).getWindow().getAttributes();
        lp.alpha = 0.7f;//调节透明度
        getActivityByContext(mContext).getWindow().setAttributes(lp);
        //dismiss时恢复原样
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getActivityByContext(mContext).getWindow().getAttributes();
                lp.alpha = 1f;
                getActivityByContext(mContext).getWindow().setAttributes(lp);
            }
        });
    }

    /**
     * 任意时间、起床、晨间...等时间标题点击事件的内容
     * @param iv
     * @param tv
     * @param gv
     */
    private void onClickItem(ImageView iv, TextView tv, GridView gv){
        if(iv.getTag().equals("0")){
            iv.setImageResource(R.drawable.close);
            tv.setVisibility(View.VISIBLE);
            gv.setVisibility(View.GONE);
            tv.setText("余下"+"个");
            iv.setTag("1");
        }else{
            iv.setImageResource(R.drawable.open);
            tv.setVisibility(View.GONE);
            gv.setVisibility(View.VISIBLE);
            iv.setTag("0");
        }
    }

    /**
     * 动态添加一个习惯
     * @param time
     * @param layout
     * @param dataSource
     */

    private void createItem(String time, LinearLayout layout, List<Habit> dataSource) {
        //创建一个LinerLayout控件
        LinearLayout linearLayout = new LinearLayout(mContext);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(Utils.dip2px(mContext, 10), Utils.dip2px(mContext,
                20), 0, Utils.dip2px(mContext, 20));
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);

        //
        LinearLayout background = new LinearLayout(mContext);
        LinearLayout.LayoutParams bgParam = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        background.setGravity(Gravity.CENTER);
        background.setPadding(Utils.dip2px(mContext, 10), Utils.dip2px(mContext, 5),
                Utils.dip2px(mContext, 10), Utils.dip2px(mContext, 5));
        background.setBackgroundResource(R.drawable.background_fragment_one_time);
        background.setLayoutParams(bgParam);

        //在LinerLayout里添加一个ImageView控件
        final ImageView img = new ImageView(mContext);
        LinearLayout.LayoutParams ivParam = new LinearLayout.LayoutParams(40, 40);
        ivParam.setMargins(0,0,Utils.dip2px(mContext, 5),0);
        img.setLayoutParams(ivParam);
        img.setImageResource(R.drawable.open);
        img.setTag("0");
        background.addView(img);

        //在LinerLayout里添加一个TextView控件
        TextView txt = new TextView(mContext);
        LinearLayout.LayoutParams txtParam = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        txt.setText(time);
        txt.setTextSize(15f);
        txt.setLayoutParams(txtParam);
        background.addView(txt);

        //在LinerLayout里添加一个TextView控件
        final TextView num= new TextView(mContext);
        LinearLayout.LayoutParams numParam = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        numParam.setMargins(Utils.dip2px(mContext, 10), 0, 0, 0);
        numParam.gravity = Gravity.CENTER_VERTICAL;
        num.setLayoutParams(numParam);
        linearLayout.addView(background);
        linearLayout.addView(num);

        //在最外层的LinerLayout里添加一个GridView控件
        final MyGridView gridView = new MyGridView(mContext);
        LinearLayout.LayoutParams gvParam = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        gridView.setNumColumns(3);
        gridView.setVerticalSpacing(60);
        gridView.setLayoutParams(gvParam);

        //按顺序加入最外层LinerLayout里
        layout.addView(linearLayout);
        layout.addView(gridView);

        setGridAdapter(dataSource, gridView);
        //设置点击事件
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItem(img, num, gridView);
            }
        });
    }
    /**
     *设置judge列表的内容
     */
    private void setJudge(String str, LinearLayout layout, List<Habit> list){
        if(list.size()>=1){
            createItem(str, layout, list);
        }
    }

    /**
     * 设置所有List的内容
     */
    private void setAllList(){
        for(int i=0; i<list.size(); i++){
            switch (list.get(i).getTimeOfDay()){
                case 0:
                    list1.add(list.get(i));
                    break;
                case 1:
                    list2.add(list.get(i));
                    break;
                case 2:
                    list3.add(list.get(i));
                    break;
                case 3:
                    list4.add(list.get(i));
                    break;
                case 4:
                    list5.add(list.get(i));
                    break;
                case 5:
                    list6.add(list.get(i));
                    break;
                case 6:
                    list7.add(list.get(i));
                    break;
            }
        }
    }

    /**
     * 通过Context获得Activity
     * @param context
     * @return
     */
    public static Activity getActivityByContext(Context context){
        while(context instanceof ContextWrapper){
            if(context instanceof Activity){
                return (Activity) context;
            }
                context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onFailure(String message) {
//        Looper.prepare();
//        Toast.makeText(getContext().getApplicationContext(), message, Toast.LENGTH_SHORT).show();
//        Looper.loop();
    }

    @Override
    public void onSuccess(String data) {
        Message message = new Message();
        message.what = 1;
        message.obj = data;
        handler.sendMessage(message);
    }

    @Override
    public void onSuccess2(String data) {

    }
}

