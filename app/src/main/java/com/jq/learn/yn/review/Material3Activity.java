package com.jq.learn.yn.review;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

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
    }
}
