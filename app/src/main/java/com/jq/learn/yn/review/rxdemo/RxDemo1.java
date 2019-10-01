package com.jq.learn.yn.review.rxdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jq.learn.yn.review.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public class RxDemo1 extends AppCompatActivity {

    private Button mBtnStart;
    private EditText mEtSearch;
    private TextView mTvResult,mTvSearch;

    private PublishSubject<String> mPublishSubject;
    private DisposableObserver<String> disposableObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_demo1);
        // 我们的界面上有一个按钮mTvDownload，点击之后会发起一个耗时的任务，这里我们用Thread.sleep来模拟耗时的操作，
        // 每隔500ms我们会将当前的进度通知主线程，在mTvDownloadResult中显示当前处理的进度。
        mBtnStart = (Button) findViewById(R.id.tv_download);
        mTvResult = (TextView) findViewById(R.id.tv_down_result);

        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDownLoad();
            }
        });


        //搜索联想
        //使用debounce操作符，当输入框发生变化时，不会立刻将事件发送给下游，而是等待200ms，如果在这段事件内，输入框没有发生变化，那么才发送该事件；反之，则在收到新的关键词后，继续等待200ms。
        //使用filter操作符，只有关键词的长度大于0时才发送事件给下游。
        //使用switchMap操作符，这样当发起了abc的请求之后，即使ab的结果返回了，也不会发送给下游，从而避免了出现前面介绍的搜索词和联想结果不匹配的问题。

        mEtSearch= (EditText) findViewById(R.id.et_search);
        mTvSearch = (TextView) findViewById(R.id.tv_search_result);
        mEtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                startSearch(s.toString());
            }
        });
        mPublishSubject = PublishSubject.create();
        disposableObserver = new DisposableObserver<String>() {
            @Override
            public void onNext(String value) {
                mTvSearch.setText(value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        mPublishSubject.debounce(200, TimeUnit.MILLISECONDS)
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) throws Exception {
                        return s.length() > 0;
                    }
                }).switchMap(new Function<String, ObservableSource<String>>() {
                    //发起了abc的请求之后，即使ab的结果返回了，也不会发送给下游
                    //从而避免了出现前面介绍的搜索词和联想结果不匹配的问题。
            @Override
            public ObservableSource<String> apply(String s) throws Exception {
                return getSearchObservable(s);
            }
        }).observeOn(AndroidSchedulers.mainThread())
        .subscribe(disposableObserver);
        

    }

    //得到搜索词进行搜索,返回一个 Observable（被观察者）
    private ObservableSource<String> getSearchObservable(final String s) {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> observableEmitter) throws Exception {
                try {
                    Thread.sleep(100 + (long) (Math.random() * 500));
                } catch (InterruptedException e) {
                    if (!observableEmitter.isDisposed()) {
                        observableEmitter.onError(e);
                    }
                }
                observableEmitter.onNext("完成搜索，关键词为：" + s);
                observableEmitter.onComplete();

            }
        }).subscribeOn(Schedulers.io());
    }

    private void startSearch(String s) {
        mPublishSubject.onNext(s);
    }

    private void startDownLoad() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                for (int i = 0; i < 100; i++) {
                    if (i % 10 == 0) {
                        try {
                            Thread.sleep(500);
                        } catch (Exception exception) {
                            e.onError(exception);
                        }
                        e.onNext(i);
                    }
                }
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer value) {
                        mTvResult.setText("下载进度" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(RxDemo1.this,"出错啦",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onComplete() {
                        mTvResult.setText("下载完成");
                    }
                });
    }
}
