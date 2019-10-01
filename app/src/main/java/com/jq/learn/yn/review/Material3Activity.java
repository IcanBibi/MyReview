package com.jq.learn.yn.review;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public class Material3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material3);
        RecyclerView view = (RecyclerView) findViewById(R.id.rv_content);

        ArrayList<String> mTitles = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mTitles.add("My name is " + i);
        }
        NormalAdapter normalAdapter = new NormalAdapter(mTitles);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setAdapter(normalAdapter);

        initViews();

        Observable.timer(3, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        mBottomNavigationBar.selectTab(2);
                    }
                });
    }

    private BottomNavigationBar mBottomNavigationBar;
    private void initViews() {
        mBottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation);
        //1.设置Mode
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        //2.设置BackgroundStyle
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        //3.设置背景色
        mBottomNavigationBar.setBarBackgroundColor(android.R.color.white);
        //4.设置每个Item
        mBottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "Item 1").setActiveColorResource(android.R.color.holo_blue_dark));
        mBottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "Item 2").setActiveColorResource(android.R.color.holo_green_dark));
        mBottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "Item 3").setActiveColorResource(android.R.color.holo_orange_dark));
        mBottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "Item 4").setActiveColorResource(android.R.color.holo_green_dark));
        mBottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "Item 5").setActiveColorResource(android.R.color.holo_orange_dark));
        //5.初始化
        mBottomNavigationBar.initialise();
    }

}
