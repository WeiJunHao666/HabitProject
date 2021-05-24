package com.example.goodlife.wjh;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.goodlife.R;
import com.example.goodlife.wjh.daily.ActivityDaily;
import com.example.goodlife.wjh.login.ActivityLogin;
import com.example.goodlife.zsl.activity.AcclaimPageActivity;
import com.example.goodlife.zsl.activity.BackUpPageActivity;
import com.example.goodlife.zsl.activity.CardBagPageActivity;
import com.example.goodlife.zsl.activity.CutDownPageActivity;
import com.example.goodlife.zsl.activity.EssayPageActivity;
import com.example.goodlife.zsl.activity.FeedBackActivity;
import com.example.goodlife.zsl.activity.HabitRemindActivity;
import com.example.goodlife.zsl.activity.NotePageActivity;
import com.example.goodlife.zsl.activity.OverHabitPageActivity;
import com.example.goodlife.zsl.activity.SetPageActivity;
import com.example.goodlife.zsl.activity.VipPageActivity;
import com.example.goodlife.zsl.activity.WorkerPageActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class FragmentFour extends Fragment {
    private View root;
    private ImageView set;
    private CircleImageView head;
    private TextView name;
    private Button overHabit;
    private Button vip;
    private Button cardBag;
    private Button dynamic;
    private Button backUp;
    private Button worker;
    private Button feedBack;
    private Button note;
    private Button essay;
    private Button remind;
    private Button acclaim;
    private Button cutDown;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root =inflater.inflate(R.layout.fragment_four_page, container, false);

        findViews();
        setOnClickListener();
        return root;
    }

    private void findViews(){
        set = root.findViewById(R.id.set);
        head = root.findViewById(R.id.img_head);
        name = root.findViewById(R.id.text_name);
        overHabit = root.findViewById(R.id.btn_over_habit);
        vip = root.findViewById(R.id.btn_vip);
        cardBag = root.findViewById(R.id.btn_card_bag);
        dynamic = root.findViewById(R.id.btn_dynamic);
        backUp = root.findViewById(R.id.btn_backup);
        worker = root.findViewById(R.id.btn_worker);
        feedBack = root.findViewById(R.id.btn_feed_back);
        note = root.findViewById(R.id.btn_note);
        essay = root.findViewById(R.id.btn_essay);
        remind = root.findViewById(R.id.btn_remind);
        acclaim = root.findViewById(R.id.accelerate);
        cutDown = root.findViewById(R.id.btn_cut_down);
    }

    private void setOnClickListener(){
        MyListener listener = new MyListener();
        set.setOnClickListener(listener);
        head.setOnClickListener(listener);
        overHabit.setOnClickListener(listener);
        vip.setOnClickListener(listener);
        cardBag.setOnClickListener(listener);
        dynamic.setOnClickListener(listener);
        backUp.setOnClickListener(listener);
        worker.setOnClickListener(listener);
        feedBack.setOnClickListener(listener);
        note.setOnClickListener(listener);
        essay.setOnClickListener(listener);
        remind.setOnClickListener(listener);
//        acclaim.setOnClickListener(listener);
//        cutDown.setOnClickListener(listener);
    }

    private class MyListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.set:
                    Intent intent = new Intent(getActivity().getApplicationContext(), SetPageActivity.class);
                    startActivity(intent);
                    getActivity().overridePendingTransition(R.anim.activity_in_bottom,R.anim.activity_maintain);
                    break;
                case R.id.img_head:
                    Intent intent1 = new Intent(getActivity().getApplicationContext(), ActivityLogin.class);
                    startActivity(intent1);
                    getActivity().overridePendingTransition(R.anim.activity_in_bottom,R.anim.activity_maintain);
                    break;
                case R.id.btn_over_habit:
                    Intent intent2 = new Intent(getActivity().getApplicationContext(), OverHabitPageActivity.class);
                    startActivity(intent2);
                    getActivity().overridePendingTransition(R.anim.activity_in_bottom,R.anim.activity_maintain);
                    break;
                case R.id.btn_vip:
                    Intent intent3 = new Intent(getActivity().getApplicationContext(), VipPageActivity.class);
                    startActivity(intent3);
                    getActivity().overridePendingTransition(R.anim.activity_in_bottom,R.anim.activity_maintain);
                    break;
                case R.id.btn_card_bag:
                    Intent intent4 = new Intent(getActivity().getApplicationContext(), CardBagPageActivity.class);
                    startActivity(intent4);
                    getActivity().overridePendingTransition(R.anim.activity_in_bottom,R.anim.activity_maintain);
                    break;
                case R.id.btn_dynamic:
                    Intent intent5 = new Intent(getActivity().getApplicationContext(), ActivityDaily.class);
                    startActivity(intent5);
                    getActivity().overridePendingTransition(R.anim.activity_in_bottom,R.anim.activity_maintain);
                    break;
                case R.id.btn_backup:
                    Intent intent6 = new Intent(getActivity().getApplicationContext(), BackUpPageActivity.class);
                    startActivity(intent6);
                    getActivity().overridePendingTransition(R.anim.activity_in_bottom,R.anim.activity_maintain);
                    break;
                case R.id.btn_worker:
                    Intent intent7 = new Intent(getActivity().getApplicationContext(), WorkerPageActivity.class);
                    startActivity(intent7);
                    getActivity().overridePendingTransition(R.anim.activity_in_bottom,R.anim.activity_maintain);
                    break;
                case R.id.btn_feed_back:
                    Intent intent8 = new Intent(getActivity().getApplicationContext(), FeedBackActivity.class);
                    startActivity(intent8);
                    getActivity().overridePendingTransition(R.anim.activity_in_bottom,R.anim.activity_maintain);
                    break;
                case R.id.btn_note:
                    Intent intent9 = new Intent(getActivity().getApplicationContext(), NotePageActivity.class);
                    startActivity(intent9);
                    getActivity().overridePendingTransition(R.anim.activity_in_bottom,R.anim.activity_maintain);
                    break;
                case R.id.btn_essay:
                    Intent intent10 = new Intent(getActivity().getApplicationContext(), EssayPageActivity.class);
                    startActivity(intent10);
                    getActivity().overridePendingTransition(R.anim.activity_in_bottom,R.anim.activity_maintain);
                    break;
                case R.id.btn_remind:
                    Intent intent11 = new Intent(getActivity().getApplicationContext(), HabitRemindActivity.class);
                    startActivity(intent11);
                    getActivity().overridePendingTransition(R.anim.activity_in_bottom,R.anim.activity_maintain);
                    break;
                case R.id.btn_acclaim:
                    Intent intent12 = new Intent(getActivity().getApplicationContext(), AcclaimPageActivity.class);
                    startActivity(intent12);
                    getActivity().overridePendingTransition(R.anim.activity_in_bottom,R.anim.activity_maintain);
                    break;
                case R.id.btn_cut_down:
                    Intent intent13 = new Intent(getActivity().getApplicationContext(), CutDownPageActivity.class);
                    startActivity(intent13);
                    getActivity().overridePendingTransition(R.anim.activity_in_bottom,R.anim.activity_maintain);
                    break;
            }
        }
    }



}
