package com.jq.learn.yn.review.rxdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.jq.learn.yn.review.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class RxDemo5 extends AppCompatActivity {
    private static final String TAG = RxDemo5.class.getSimpleName();
    private CompositeDisposable mCompositeDisposable;
    

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_demo4);
        mCompositeDisposable = new CompositeDisposable();


        startSimplePolling();
    }

    private void startSimplePolling() {
        Observable<Long> observable = Observable.intervalRange(0, 5, 0, 3000, TimeUnit.MILLISECONDS)
                .take(5).doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        doWork(); //这里使用了doOnNext，因此DisposableObserver的onNext要等到该方法执行完才会回调。
                    }
                });

        DisposableObserver<Long> disposableObserver = getDisposableObserver();
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(disposableObserver);
        mCompositeDisposable.add(disposableObserver);
    }

    private DisposableObserver<Long> getDisposableObserver() {
        return new DisposableObserver<Long>() {
            @Override
            public void onNext(Long value) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }

    private void doWork() {
        long workTime = (long) (Math.random() * 500) + 500;
        try {
            Log.d(TAG, "doWork start,  threadId=" + Thread.currentThread().getId());
            Thread.sleep(workTime);
            Log.d(TAG, "doWork finished");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}



