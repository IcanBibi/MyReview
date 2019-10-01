package com.jq.learn.yn.review.rxdemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.jq.learn.yn.review.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RxDemo4 extends AppCompatActivity {

    private List<NewsEntity.ResultsBean> mNewsEntities = new ArrayList<>();
    private int mCurrentPage = 1;
    private NewsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_demo4);

        initView();
    }

    private void initView() {
        Button btnGet = (Button) findViewById(R.id.btn_get);
        RecyclerView recyclerview = (RecyclerView) findViewById(R.id.rv_news);

        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new NewsAdapter(mNewsEntities);
        recyclerview.setAdapter(mAdapter);

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData(++mCurrentPage);
            }
        });
        initApi();
    }

    private void getData(final int page) {
        Observable<List<NewsEntity.ResultsBean>> observable = Observable.just(page).subscribeOn(Schedulers.io()).flatMap(new Function<Integer, Observable<List<NewsEntity.ResultsBean>>>() {
            @Override
            public Observable<List<NewsEntity.ResultsBean>> apply(Integer integer) throws Exception {
                Observable<NewsEntity> androidObservable = api.getNews("Android", 10, 1);
                Observable<NewsEntity> iosObservable = api.getNews("ios", 10, 1);

                return Observable.zip(androidObservable, iosObservable, new BiFunction<NewsEntity, NewsEntity, List<NewsEntity.ResultsBean>>() {
                    @Override
                    public List<NewsEntity.ResultsBean> apply(NewsEntity androidEntity, NewsEntity iosEntity) throws Exception {
                        List<NewsEntity.ResultsBean> result = new ArrayList<>();
                        result.addAll(androidEntity.getResults());
                        result.addAll(iosEntity.getResults());
                        return result;
                    }
                });
            }
        });

        DisposableObserver<List<NewsEntity.ResultsBean>> disposableObserver = new DisposableObserver<List<NewsEntity.ResultsBean>>() {
            @Override
            public void onNext(List<NewsEntity.ResultsBean> value) {
                mNewsEntities.clear();
                mNewsEntities.addAll(value);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        observable.observeOn(AndroidSchedulers.mainThread()).subscribe(disposableObserver);

    }

    private NewsApi api;

    private void initApi() {
        /*
         **打印retrofit信息部分
         */
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                //打印retrofit日志
                Log.e("RetrofitLog","retrofitBack = "+message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()//okhttp设置部分，此处还可再设置网络参数
                .addInterceptor(loggingInterceptor)
                .build();


        api = new Retrofit.Builder()
                .baseUrl("http://gank.io")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(NewsApi.class);
    }

    class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

        private List<NewsEntity.ResultsBean> mList = new ArrayList();

        public NewsAdapter(List<NewsEntity.ResultsBean> list) {
            mList = list;
        }

        @NonNull
        @Override
        public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_demo4, parent, false);
            return new NewsViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull NewsViewHolder newsViewHolder, int i) {
            newsViewHolder.setTitle(mList.get(i));
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }

        class NewsViewHolder extends RecyclerView.ViewHolder {
            private TextView mTvDest;
            private TextView mTvType;

            public NewsViewHolder(@NonNull View itemView) {
                super(itemView);
                mTvDest = (TextView) findViewById(R.id.tv_item_dest);
                mTvType = (TextView) findViewById(R.id.tv_item_type);
            }

            public void setTitle(NewsEntity.ResultsBean item) {
                mTvDest.setText(item.getDesc());
                mTvType.setText(item.getType());
            }
        }
    }
}
