package com.jq.learn.yn.review;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jq.learn.yn.review.rxdemo.RxDemo1;
import com.jq.learn.yn.review.rxdemo.RxDemo4;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final String URL = "http://www.weather.com.cn/adat/sk/101220201.html";
    private Button btn;
    private Button demo1,demo4;
    private TextView text;
    private OkHttpClient client;
    private Request build;
    private Call call;
    private String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btn_get);
        text = (TextView) findViewById(R.id.tv_text);
        demo1 = (Button) findViewById(R.id.btn_demo1);
        demo4 = (Button) findViewById(R.id.btn_demo4);
        initOkhttp();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initData();
            }
        });
        demo1.setOnClickListener(this);
        demo4.setOnClickListener(this);

    }

    private Intent intent;
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_demo1:
                intent = new Intent(MainActivity.this,RxDemo1.class);
                startActivity(intent);
                break;
            case R.id.btn_demo4:
                intent = new Intent(MainActivity.this,RxDemo4.class);
                startActivity(intent);
                break;
        }
    }


    private void initOkhttp() {
        client = new OkHttpClient().newBuilder().addInterceptor(new LoggingInterceptor()).build();
        build = new Request.Builder()
                .url(URL).build();
    }


    private void initData() {
        call = client.newCall(build);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                result = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        text.setText(result);
                    }
                });
            }
        });
    }

    class LoggingInterceptor implements Interceptor {
        private String tag = "okhttp";

        @Override
        public Response intercept(Chain chain) throws IOException {
            //第一步，获得chain内的request
            Request request = chain.request();
            Log.d(tag, "intercept-request:" + request.url() );
            //第二步，用chain执行request
            Response response = chain.proceed(request);
            ResponseBody responseBody = response.peekBody(1024 * 1024);
            Log.d(tag, "intercept-response" + "-" + response.request().url()+"\t\tresponse:" + responseBody.string());
            //第三步，返回response
            return response;
        }
    }
}
