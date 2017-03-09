package com.doudou.toutiao.ui.home.fragment;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.doudou.toutiao.R;
import com.doudou.toutiao.adapter.HomeFragmentPagerAdapter;
import com.jaydenxiao.common.base.BaseFragment;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/2/24.
 */
public class HomeFragment extends BaseFragment {

    @Bind(R.id.tablayout)
    TabLayout tablayout;
    @Bind(R.id.viewpager)
    ViewPager viewpager;
    @Bind(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_home;
    }

    @Override
    public void initPresenter() {
    }

    @Override
    protected void initView() {
        viewpager.setAdapter(new HomeFragmentPagerAdapter(getChildFragmentManager()));
        tablayout.setupWithViewPager(viewpager);
    }
}
