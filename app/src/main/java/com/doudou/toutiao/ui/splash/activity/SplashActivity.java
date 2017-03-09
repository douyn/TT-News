package com.doudou.toutiao.ui.splash.activity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.doudou.toutiao.ui.main.MainActivity;
import com.doudou.toutiao.R;
import com.doudou.toutiao.app.AppSharePreConfig;
import com.jaydenxiao.common.base.BaseActivity;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/2/23.
 */
public class SplashActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.iv_ad)
    ImageView ivAd;
    @Bind(R.id.tv_count)
    TextView tvCount;
    @Bind(R.id.root_view)
    LinearLayout rootView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splsh;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        doFirstStart();
        tvCount.setOnClickListener(this);
        Glide.with(this).load("http://upload-images.jianshu.io/upload_images/3979949-79e78bd5c0328c50.jpg?imageMogr2/auto-orient/strip%7CimageView2/1/w/300/h/240")
                .asBitmap().into(ivAd);
    }

    private void doFirstStart() {
        if (!AppSharePreConfig.getIsFirstStart()) { // 第一次启动，跳转到启动页
            AppSharePreConfig.setIsFirstStart(true);
            startActivity(GuideActivity.class);
        } else { // 显示splashview
            showSplashView();
        }
    }

    private void showSplashView() {
        AlphaAnimation animation = new AlphaAnimation(0.5f, 1f);
        animation.setDuration(1000);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        rootView.startAnimation(animation);

        startTimeCount();
    }

    private void startTimeCount() {
        int interval = 3;
        rx.Observable.interval(0, 1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Long count) {
                        if (count == 3) {
                            tvCount.setText("跳过 0s");
                            tvCount.setVisibility(View.GONE);
                            startActivity(MainActivity.class);
                            finish();
                        } else {
                            tvCount.setText("跳过 " + (3 - count) + "s");
                        }
                    }
                });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_count:
                startActivity(MainActivity.class);
                finish();
                break;
        }
    }
}
