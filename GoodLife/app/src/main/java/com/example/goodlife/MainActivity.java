package com.example.goodlife;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener{

    private BottomNavigationBar bottomNavigationBar;
    private LinearLayout ll_content;
    private FragmentOne fragmentOne;
    private FragmentTwo fragmentTwo;
    private FragmentThree fragmentThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        initBottom();
        initFragment();
        setDefaultFragment();

    }

    /**
     * 设置默认导航栏
     */
    private void setDefaultFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.ll_content, fragmentOne);
        transaction.commit();
    }

    /**
     * 初始化fragment
     */
    private void initFragment() {
        fragmentOne = new FragmentOne();
        fragmentTwo = new FragmentTwo();
        fragmentThree = new FragmentThree();
    }

    /**
     * 设置导航栏样式
     */
    private void initBottom() {
        bottomNavigationBar
                .setTabSelectedListener(this)
                .setMode(BottomNavigationBar.MODE_FIXED)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_DEFAULT)
                .addItem(new BottomNavigationItem(R.drawable.inhome, "今日")
                .setInactiveIconResource(R.drawable.home))
                .addItem(new BottomNavigationItem(R.drawable.inquan, "全部")
                .setInactiveIconResource(R.drawable.quanzi))
                .addItem(new BottomNavigationItem(R.drawable.inmine, "我的")
                .setInactiveIconResource(R.drawable.mine))
                .setFirstSelectedPosition(0)
                .initialise();
    }
    private void findViews() {
        bottomNavigationBar = findViewById(R.id.bottom_navigation_bar);
        ll_content = findViewById(R.id.ll_content);
    }

    @Override
    public void onTabSelected(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Log.e("xx", position+"");
        switch (position){
            case 0:
                transaction.replace(R.id.ll_content, fragmentOne);
                break;
            case 1:
                transaction.replace(R.id.ll_content, fragmentTwo);
                break;
            case 2:
                transaction.replace(R.id.ll_content, fragmentThree);
                break;
            default:
                transaction.replace(R.id.ll_content, fragmentOne);
                break;
        }
        transaction.commit();
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

}