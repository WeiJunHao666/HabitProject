package com.example.goodlife.wjh;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.example.goodlife.R;
import com.example.goodlife.wjh.kindview.WaveView;

public class FragmentThree extends Fragment {

    private View root;

    private WaveView mWaveView;
    private RadioGroup mRadioGroup;
    private Button startButton;
    private Button endButton;
    private Button continueButton;
    private Chronometer clockView;
    private TimePicker timePicker;
    private MyCountDownTimer countDownTimer;

    private BottomNavigationBar bottomNavigationBar;

    private int flag = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root =inflater.inflate(R.layout.fragment_three, container, false);

        mWaveView = root.findViewById(R.id.wave_view);
        mRadioGroup = root.findViewById(R.id.top_radio);
        startButton = root.findViewById(R.id.clock_start);
        endButton = root.findViewById(R.id.clock_end);
        continueButton = root.findViewById(R.id.clock_continue);
        clockView = root.findViewById(R.id.clock_view);
        timePicker = root.findViewById(R.id.time_picker_view);
        bottomNavigationBar = this.getActivity().findViewById(R.id.bottom_navigation_bar);

        timePicker.setIs24HourView(true);//设置时间为24小时的 格式
        timePicker.setCurrentHour(0);//设置当前的小时
        timePicker.setCurrentMinute(1);//设置当前的分钟

        mWaveView.setDuration(3500);
        mWaveView.setStyle(Paint.Style.STROKE);
        mWaveView.setSpeed(1500);
        mWaveView.setInitialRadius(280);
        mWaveView.setColor(Color.parseColor("#000000"));
        mWaveView.setInterpolator(new AccelerateInterpolator(1.2f));


        startButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if(startButton.getTag().equals("0")){
                    mRadioGroup.setVisibility(View.INVISIBLE);
                }
                bottomNavigationBar.setVisibility(View.INVISIBLE);
                switch (flag){
                    case 0:
                        if(startButton.getTag().equals("0")){
                            startButton.setTag("1");
                            startButton.setText("暂停");

                            clockView.setBase(SystemClock.elapsedRealtime());
                            clockView.start();
                            mWaveView.start();

                        }else{
                            startButton.setVisibility(View.GONE);
                            endButton.setVisibility(View.VISIBLE);
                            continueButton.setVisibility(View.VISIBLE);

                            clockView.stop();
                            mWaveView.stop();
                        }
                        break;
                    case 1:
                        if(startButton.getTag().equals("0")){

                            int hour = timePicker.getHour();
                            int minute = timePicker.getMinute();

                            if(hour==0 && minute==0){
                                Log.e("kkss", "123");
                                mRadioGroup.setVisibility(View.VISIBLE);
                                bottomNavigationBar.setVisibility(View.VISIBLE);
                            }else{
                                startButton.setTag("1");
                                startButton.setText("暂停");
                                timePicker.setVisibility(View.GONE);
                                clockView.setVisibility(View.VISIBLE);
                                clockView.setText("");
                                countDownTimer = new MyCountDownTimer(hour *3600000 + minute*60*1000 +1000,
                                        1000);
                                countDownTimer.start();
                                mWaveView.start();
                            }

                        }else{
                            startButton.setVisibility(View.GONE);
                            endButton.setVisibility(View.VISIBLE);
                            continueButton.setVisibility(View.VISIBLE);

                            countDownTimer.cancel();
                            mWaveView.stop();
                        }
                        break;
                    case 2:
                        if(startButton.getTag().equals("0")){
                            startButton.setTag("1");
                            startButton.setText("暂停");

                            countDownTimer = new MyCountDownTimer(getTime(clockView).longValue()+1000, 1000);
                            countDownTimer.start();
                            mWaveView.start();
                        }else{
                            startButton.setVisibility(View.GONE);
                            endButton.setVisibility(View.VISIBLE);
                            continueButton.setVisibility(View.VISIBLE);

                            countDownTimer.cancel();
                            mWaveView.stop();
                        }
                        break;
                }

            }
        });

        endButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRadioGroup.setVisibility(View.VISIBLE);
                bottomNavigationBar.setVisibility(View.VISIBLE);
                switch (flag){
                    case 0:
                        clockView.setBase(SystemClock.elapsedRealtime());
                        break;
                    case 1:
                        clockView.setVisibility(View.GONE);
                        timePicker.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        clockView.setBase((long) (SystemClock.elapsedRealtime() - 25*60*1000));
                        break;
                }
                endButton.setVisibility(View.GONE);
                continueButton.setVisibility(View.GONE);
                startButton.setVisibility(View.VISIBLE);
                startButton.setText("开始");
                startButton.setTag("0");
            }
        });

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (flag){
                    case 0:
                        clockView.setBase((long) (SystemClock.elapsedRealtime() - getTime(clockView)));
                        clockView.start();
                        break;
                    case 1:
                        countDownTimer = new MyCountDownTimer(getTime(clockView).longValue(), 1000);
                        countDownTimer.start();
                        break;
                    case 2:
                        countDownTimer = new MyCountDownTimer(getTime(clockView).longValue(), 1000);
                        countDownTimer.start();
                        break;
                }

                endButton.setVisibility(View.GONE);
                continueButton.setVisibility(View.GONE);
                startButton.setVisibility(View.VISIBLE);
                mWaveView.start();
            }
        });

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton button1 = (RadioButton) group.getChildAt(0);
                RadioButton button2 = (RadioButton) group.getChildAt(1);
                RadioButton button3 = (RadioButton) group.getChildAt(2);
                Log.e("dsl", String.valueOf(checkedId));
                switch (checkedId){
                    case R.id.top_radio_one:
                        flag = 0;
                        button1.setBackgroundResource(R.drawable.top_radio1_checked);
                        button1.setTextColor(Color.parseColor("#ffffff"));
                        button2.setBackgroundResource(R.drawable.top_radio2);
                        button2.setTextColor(Color.parseColor("#000000"));
                        button3.setBackgroundResource(R.drawable.top_radio3);
                        button3.setTextColor(Color.parseColor("#000000"));

                        clockView.setVisibility(View.VISIBLE);
                        timePicker.setVisibility(View.GONE);
                        clockView.setBase(SystemClock.elapsedRealtime());
                        break;

                    case R.id.top_radio_two:
                        flag = 1;
                        button1.setBackgroundResource(R.drawable.top_radio1);
                        button1.setTextColor(Color.parseColor("#000000"));
                        button2.setBackgroundResource(R.drawable.top_radio2_checked);
                        button2.setTextColor(Color.parseColor("#ffffff"));
                        button3.setBackgroundResource(R.drawable.top_radio3);
                        button3.setTextColor(Color.parseColor("#000000"));

                        clockView.setVisibility(View.GONE);
                        timePicker.setVisibility(View.VISIBLE);
                        break;
                    case R.id.top_radio_three:
                        flag = 2;
                        button1.setBackgroundResource(R.drawable.top_radio1);
                        button1.setTextColor(Color.parseColor("#000000"));
                        button2.setBackgroundResource(R.drawable.top_radio2);
                        button2.setTextColor(Color.parseColor("#000000"));
                        button3.setBackgroundResource(R.drawable.top_radio3_checked);
                        button3.setTextColor(Color.parseColor("#ffffff"));

                        clockView.setVisibility(View.VISIBLE);
                        timePicker.setVisibility(View.GONE);

                        clockView.setBase((long) (SystemClock.elapsedRealtime() - 25*60*1000));
                        break;
                }
            }
        });


        return root;
    }

    private Double getTime(Chronometer view){

        String[] str = view.getText().toString().split(":");
        Double temp = null;
        if(str.length == 2){
            temp = Double.parseDouble(str[0])*60*1000 + Double.parseDouble(str[1]) * 1000;
        }else {
            temp = Double.parseDouble(str[0])*3600000 + Double.parseDouble(str[1])*60*1000
                    + Double.parseDouble(str[2]) * 1000;
        }
        return temp;

    }


    public class MyCountDownTimer extends CountDownTimer{


        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {

            long day = millisUntilFinished / (1000 * 24 * 60 * 60); //单位天

            long hour = (millisUntilFinished - day * (1000 * 24 * 60 * 60)) / (1000 * 60 * 60);
            //单位时
            long minute = (millisUntilFinished - day * (1000 * 24 * 60 * 60) - hour * (1000 * 60 * 60)) / (1000 * 60);
            //单位分
            long second = (millisUntilFinished - day * (1000 * 24 * 60 * 60) - hour * (1000 * 60 * 60) - minute * (1000 * 60)) / 1000;
            Log.e("time", hour+":"+minute+":"+second);
            String h = String.valueOf(hour);
            String m = String.valueOf(minute);
            String s = String.valueOf(second);
            if(minute<10) m = "0"+ minute;
            if (hour<10)  h = "0" + hour;
            if(second<10) s = "0" + second;
            if(hour==0){
                clockView.setText(m+":"+s);
            }else{
                clockView.setText(h+":"+m+":"+s);
            }

        }

        @Override
        public void onFinish() {
            mWaveView.stop();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
    }
}
