package com.jq.learn.yn.review;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.view.View;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;

import static com.jq.learn.yn.review.Preconditions.checkNotNull;
import static com.jq.learn.yn.review.Preconditions.checkUiThread;

public final class RxView {
    /**
     * 防止重复点击
     *
     * @param target 目标view
     * @param action 监听器
     */
    public static void setOnClickListeners(final Action1<View> action, @NonNull View... target) {
        for (View view : target) {
            RxView.onClick(view).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe(new Consumer<View>() {
                @Override
                public void accept(View view) throws Exception {
                    action.onClick(view);
                }
            });
        }
    }

    /**
     * 监听onclick事件防抖动
     *
     * @param view
     * @return
     */
    @CheckResult
    @NonNull
    private static Observable<View> onClick(@NonNull View view) {
        checkNotNull(view, "view == null");
        return Observable.create(new ViewClickOnSubscribe(view));
    }

//    @CheckResult
//    @NonNull
//    public static <T extends Adapter> Observable<AdapterViewItemClickEvent> itemClickEvents(
//            @NonNull RecyclerAdapter<?> view) {
//        checkNotNull(view, "view == null");
//        return Observable.create(new AdapterViewItemClickEventOnSubscribe(view));
//    }

    /**
     * onclick事件防抖动
     * 返回view
     */
    private static class ViewClickOnSubscribe implements ObservableOnSubscribe<View> {
        private View view;

        public ViewClickOnSubscribe(View view) {
            this.view = view;
        }

        @Override
        public void subscribe(final ObservableEmitter<View> e) throws Exception {
            checkUiThread();

            View.OnClickListener listener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!e.isDisposed()) {
                        e.onNext(view);
                    }
                }
            };
            view.setOnClickListener(listener);
        }
    }

    /**
     * A one-argument action. 点击事件转发接口
     *
     * @param <T> the first argument type
     */
    public interface Action1<T> {
        void onClick(T t);
    }
}
