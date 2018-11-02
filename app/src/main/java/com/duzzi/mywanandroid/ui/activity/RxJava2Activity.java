package com.duzzi.mywanandroid.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.duzzi.mywanandroid.R;
import com.duzzi.mywanandroid.base.activity.BaseActivity;
import com.duzzi.mywanandroid.core.bean.data.BannerBean;
import com.duzzi.mywanandroid.core.bean.response.ArticleListResponse;
import com.duzzi.mywanandroid.core.bean.response.BannerResponse;
import com.duzzi.mywanandroid.core.http.HttpHelper;
import com.duzzi.mywanandroid.core.http.WanAndroidService;
import com.duzzi.mywanandroid.mvp.presenter.EmptyPresenter;
import com.duzzi.mywanandroid.util.DLog;
import com.duzzi.mywanandroid.util.MyHttpLoggingInterceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RxJava2Activity extends BaseActivity<EmptyPresenter> {

    public static final String TAG = RxJava2Activity.class.getSimpleName();
    @BindView(R.id.textView)
    TextView mTextView;
    private WanAndroidService mWanAndroidService;

    @Override
    protected void initInject() {

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        mWanAndroidService = HttpHelper.getInstance().getWanAndroidService();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rx_java2;
    }

    @Override
    protected void getData() {
//        testCreate();简单发射订阅
//        testMap();按照某种关系映射
//        testZip();//a1b2c3 合并对应发射
//        testConcat();abc123 按顺序连接发射
//        testFlatMap();//平铺发射
//        testConcatMap();按顺序发射
//        testDistinct();//去重发射
//        testFilter();//按条件过滤发射
//        testBuffer();
//        testTimer();//定时任务
//        testInterval();//轮询任务
//        testDoOnNext();//发射前做一些操作，类似的还有DoAfter ...
//        testSkip();//跳过指定个数发射
//        testTake();//发射指定个数的任务
//        testSingle();
//        testDebounce();//间隔少于多少秒的发射不会被观察到
//        testDefer();//每次都会创建新的Observer
//        testLast();//发射最后一个
//        testMerge();//和concat结果一致，区别是contac是先发送完第一次组再发送第二组，merge是两组合并成一组再发送
//        testReduce();//只会打印最终结果
//            testScan();//会打印每一步的结果
//        testWindow();//按照实际划分窗口，将数据发送给不同的 Observable
//        testRetrofitWithRxjava2();
//        testRetrofitUseInterval();//模拟心跳
//        testRetrofitUseZip();//两个接口合并数据
        testRetrofitUseFlatMap();//模拟类似先注册后登陆
    }

    private void testRetrofitUseFlatMap() {
        DLog.d();
        Observable<BannerResponse> banner = mWanAndroidService.getBanner();

        Disposable subscribe = banner.flatMap(new Function<BannerResponse, ObservableSource<ArticleListResponse>>() {
            @Override
            public ObservableSource<ArticleListResponse> apply(BannerResponse bannerResponse) throws Exception {
                DLog.d("轮播图搞定 开始获取文章列表");
                Observable<ArticleListResponse> homeArticle = mWanAndroidService.getHomeArticle(0);
                return homeArticle;
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ArticleListResponse>() {
            @Override
            public void accept(ArticleListResponse articleListResponse) throws Exception {
                DLog.d("文章列表获取到啦。。。。。。。。。。。。。。");
                DLog.d(articleListResponse.getData().getDatas() + "");
            }
        });
    }

    @SuppressLint("CheckResult")
    private void testRetrofitUseZip() {
        Observable<ArticleListResponse> homeArticle = mWanAndroidService.getHomeArticle(0);
        Observable<BannerResponse> banner = mWanAndroidService.getBanner();

        Observable.zip(banner, homeArticle, new BiFunction<BannerResponse, ArticleListResponse, String>() {
            @Override
            public String apply(BannerResponse bannerResponse, ArticleListResponse articleListResponse) throws Exception {
                return bannerResponse.getData() + " " + articleListResponse.getData().getDatas();
            }
        })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                DLog.d(s);
            }
        });
    }

    @SuppressLint("CheckResult")
    private void testRetrofitUseInterval() {
        final Observable<BannerResponse> banner = mWanAndroidService.getBanner();
        int count = 8;
        Observable.intervalRange(0, count, 3, 3, TimeUnit.SECONDS).subscribe(new Observer<Long>() {
            int i;
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Long aLong) {
                i++;
                DLog.d("开始轮询 " + i);
                banner.subscribe(new Consumer<BannerResponse>() {
                    @Override
                    public void accept(BannerResponse bannerResponse) throws Exception {
                        DLog.d("轮询结果：" + bannerResponse.getData());
                    }
                });
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                DLog.d("轮询结束");
            }
        });
    }

    private void testRetrofitWithRxjava2() {
        final Observable<BannerResponse> banner = mWanAndroidService.getBanner();

        banner.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BannerResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        DLog.d();
                    }

                    @Override
                    public void onNext(BannerResponse s) {
                        DLog.d(s.toString());
                        List<BannerBean> data = s.getData();
                        for (BannerBean bannerBean : data) {
                            DLog.d(bannerBean.getDesc());//为什么这个打印的是乱码
                            Log.d(TAG, "###" + bannerBean.getDesc());
//                            Logger.getLogger(TAG).log(Level.INFO, bannerBean.getDesc());//这个打印正常
//                            com.orhanobut.logger.Logger.wtf(bannerBean.getTitle(), bannerBean.getDesc());
                            if (!TextUtils.isEmpty(bannerBean.getDesc())) {
                                appendText(bannerBean.getDesc());
                                Toast.makeText(RxJava2Activity.this, bannerBean.getDesc(), Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        DLog.d();

                    }
                });
    }

    private void testWindow() {
        Disposable subscribe = Observable.interval(1, TimeUnit.SECONDS) // 间隔一秒发一次
                .take(15) // 最多接收15个
                .window(4, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Observable<Long>>() {
                    @SuppressLint("CheckResult")
                    @Override
                    public void accept(@NonNull Observable<Long> longObservable) throws Exception {
                        DLog.d(longObservable.hashCode() + "");
                        longObservable.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Consumer<Long>() {
                                    @Override
                                    public void accept(@NonNull Long aLong) throws Exception {
                                        DLog.d("Next:" + aLong + "\n");
                                    }
                                });
                    }
                });

    }

    @SuppressLint("CheckResult")
    private void testScan() {//
        Observable.just(2, 4, 8).scan(new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) throws Exception {
                DLog.d("integer: " + integer + " integer2: " + integer2);
                return integer * integer2;//该值会成为下一次的Integer
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                DLog.d(String.valueOf(integer));
            }
        });
    }

    @SuppressLint("CheckResult")
    private void testReduce() {//
        Observable.just(2, 4, 8).reduce(new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) throws Exception {
                DLog.d("integer: " + integer + " integer2: " + integer2);
                return integer * integer2;//该值会成为下一次的Integer
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                DLog.d(String.valueOf(integer));
            }
        });
    }

    @SuppressLint("CheckResult")
    private void testMerge() {
        Observable<Integer> merge = Observable.merge(Observable.just(1, 2, 3), Observable.just(5, 6));
        merge.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                DLog.d(String.valueOf(integer));
            }
        });
    }

    @SuppressLint("CheckResult")
    private void testLast() {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .last(100)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        DLog.d(String.valueOf(integer));
                    }
                });
    }

    @SuppressLint("CheckResult")
    private void testDefer() {
        Observable<Integer> integerObservable = Observable.defer(new Callable<ObservableSource<Integer>>() {
            @Override
            public ObservableSource<Integer> call() throws Exception {
                return Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9);
            }
        });
        integerObservable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Integer integer) {
                        DLog.d(String.valueOf(integer));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void testDebounce() {
        Observable<Integer> integerObservable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                for (int i = 0; i < 10; i++) {
                    emitter.onNext(i);
                    int nextInt = new Random().nextInt(1000);
                    DLog.d("index: " + i + " sleep " + nextInt);
                    Thread.sleep(nextInt);
                }
            }
        });

        Disposable subscribe = integerObservable.debounce(500, TimeUnit.MILLISECONDS).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                DLog.d("integer " + integer);
            }
        });
    }

    private void testSingle() {
        Single.just(111).subscribe(new SingleObserver<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                DLog.d();
            }

            @Override
            public void onSuccess(Integer integer) {
                DLog.d("integer" + integer);

            }

            @Override
            public void onError(Throwable e) {
                DLog.d();
            }
        });
    }

    @SuppressLint("CheckResult")
    private void testTake() {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .take(2)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        DLog.d(String.valueOf(integer));
                    }
                });
    }

    @SuppressLint("CheckResult")
    private void testSkip() {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .skip(2)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        DLog.d(String.valueOf(integer));
                    }
                });
    }

    @SuppressLint("CheckResult")
    private void testDoOnNext() {
        Observable.just(1, 2, 3, 4)
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        DLog.d("doOnNext: " + String.valueOf(integer));
                    }
                })
                .doAfterNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        DLog.d("doAfterNext: " + String.valueOf(integer));
                    }
                })
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        DLog.d("doOnComplete: ");
                    }
                }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        DLog.d(String.valueOf(integer));
                    }
                });
    }

    @SuppressLint("CheckResult")
    private void testInterval() {
        DLog.d();
        Observable.interval(2, 3, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        DLog.d("aLong" + aLong);
                    }
                });
    }

    @SuppressLint("CheckResult")
    private void testTimer() {
        DLog.d();
        Observable.timer(3, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        DLog.d("aLong" + aLong);
                    }
                });
    }

    @SuppressLint("CheckResult")
    private void testBuffer() {
        Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9);
        observable.buffer(3).subscribe(new Consumer<List<Integer>>() {
            @Override
            public void accept(List<Integer> integers) throws Exception {
                DLog.d(String.valueOf(integers));
                appendText(String.valueOf(integers));
            }
        });
        DLog.d("======================");
        observable.buffer(3, 2).subscribe(new Consumer<List<Integer>>() {
            @Override
            public void accept(List<Integer> integers) throws Exception {
                DLog.d(String.valueOf(integers));
                appendText(String.valueOf(integers));
            }
        });
    }

    private void testFilter() {
        Disposable subscribe = Observable.just(1, 2, 3, 4, 4, 4, 5, 5, 6, 6)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer % 2 == 0;
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        DLog.d(String.valueOf(integer));
                        appendText(String.valueOf(integer));
                    }
                });
    }

    private void testDistinct() {
        Disposable subscribe = Observable.just(1, 2, 3, 4, 4, 4, 5, 5, 6, 6)
                .distinct(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer) throws Exception {
                        return integer;
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        DLog.d(String.valueOf(integer));
                        appendText(String.valueOf(integer));
                    }
                });
    }

    private void testConcatMap() {
        Observable<Integer> integerObservable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                for (int i = 0; i < 3; i++) {
                    emitter.onNext(i);
                }
            }
        });
        Observable<Integer> concatMap = integerObservable.concatMap(new Function<Integer, ObservableSource<Integer>>() {
            @Override
            public ObservableSource<Integer> apply(Integer integer) throws Exception {
                ArrayList<Integer> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add(integer);
                }
                int delayTime = new Random().nextInt(3000);
                return Observable.fromIterable(list).delay(delayTime, TimeUnit.MILLISECONDS);

//                return Observable.just(integer).delay(delayTime, TimeUnit.MILLISECONDS);
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
        Disposable subscribe = concatMap.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                DLog.d(String.valueOf(integer));
                appendText(String.valueOf(integer));
            }
        });
    }

    private void testFlatMap() {
        Observable<Integer> integerObservable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                for (int i = 0; i < 3; i++) {
                    emitter.onNext(i);
                }
            }
        });
        Observable<Integer> flatMapObservable = integerObservable.flatMap(new Function<Integer, ObservableSource<Integer>>() {
            @Override
            public ObservableSource<Integer> apply(Integer integer) throws Exception {
                ArrayList<Integer> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add(integer);
                }
                int delayTime = new Random().nextInt(1000);
                return Observable.fromIterable(list).delay(delayTime, TimeUnit.MILLISECONDS);

//                return Observable.just(integer).delay(delayTime, TimeUnit.MILLISECONDS);
            }
        });
        Disposable subscribe = flatMapObservable.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                DLog.d(String.valueOf(integer));
                appendText(String.valueOf(integer));
            }
        });
    }

    /**
     * 按顺序发射
     */
    private void testConcat() {
        Observable<Integer> integerObservable = Observable.just(1, 2, 3, 4, 5);
        Observable<Integer> integerObservable1 = Observable.just(6, 7, 8);
        Observable<Integer> concat = Observable.concat(integerObservable, integerObservable1);
        Disposable subscribe = concat.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                DLog.d(String.valueOf(integer));
                appendText(String.valueOf(integer));
            }
        });
    }

    private void testZip() {
        Observable<String> stringObservable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("aaa");
                emitter.onNext("bbbb");
                emitter.onNext("cccc");
                emitter.onNext("dddd");
                emitter.onNext("eeee");
                emitter.onNext("ffff");
            }
        });
        Observable<Integer> integerObservable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                for (int i = 0; i < 3; i++) {
                    emitter.onNext(i + 1);
                }
            }
        });
        Observable<String> observable = Observable.zip(stringObservable, integerObservable, new BiFunction<String, Integer, String>() {
            @Override
            public String apply(String s, Integer integer) throws Exception {
                return s + integer + "\n";
            }
        });
        observable.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                DLog.d(s);
                appendText(s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void testMap() {
        Observable<String> integerObservable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onNext(4);
                emitter.onNext(5);
                emitter.onComplete();
            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return integer * integer + "...";
            }
        });


        integerObservable.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String integer) {
                DLog.d(String.valueOf(integer));
                appendText(String.valueOf(integer));
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                DLog.d("onComplete");
            }
        });
    }

    private Disposable mDisposable;

    private void testCreate() {
        Observable<String> stringObservable = Observable.create(new ObservableOnSubscribe<String>() {

            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                DLog.d();
                emitter.onNext("onNext1");
                emitter.onNext("onNext2");
                emitter.onNext("onNext3");
                emitter.onComplete();
                emitter.onNext("onNext4");
                DLog.d("=======mDisposable " + mDisposable.isDisposed());
//                emitter.onError(new Exception("onError"));
            }
        });
        stringObservable.subscribe(new Observer<String>() {


            @Override
            public void onSubscribe(Disposable d) {
                mDisposable = d;
                DLog.d();
                appendText("onSubscribe");
            }

            @Override
            public void onNext(String s) {
                DLog.d();
                appendText(s);
                DLog.d("=======mDisposable " + mDisposable.isDisposed());
            }

            @Override
            public void onError(Throwable e) {
                DLog.d();
                appendText(e.getMessage());
            }

            @Override
            public void onComplete() {
                DLog.d();
                appendText("onComplete");
                DLog.d("=======mDisposable " + mDisposable.isDisposed());
//                DLog.d("=======mDisposable "  + mDisposable.isDisposed());
//                DLog.d("=======dispose start==============" );
//                mDisposable.dispose();
//                DLog.d("=======mDisposable "  + mDisposable.isDisposed());
            }
        });

    }

    private void appendText(String s) {
        mTextView.setText(String.format("%s %s", mTextView.getText(), s));
    }


}
