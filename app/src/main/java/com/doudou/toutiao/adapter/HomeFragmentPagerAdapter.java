package com.doudou.toutiao.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.doudou.toutiao.R;
import com.doudou.toutiao.app.AppConstants;
import com.doudou.toutiao.app.Appcontext;
import com.doudou.toutiao.ui.home.model.NewsChannelModel;
import com.doudou.toutiao.ui.home.news_list.NewsFragment;

/**
 * Created by Administrator on 2017/2/24.
 */

public class HomeFragmentPagerAdapter extends FragmentPagerAdapter{

    String[] array_home_items = {"头条", "科技", "财经", "军事", "体育"};

    public HomeFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = new NewsFragment();

        String id =  Appcontext.getInstance().getResources().getStringArray(R.array.news_channel_id_static)[position];
        String type = NewsChannelModel.getType(id);
        int index = position;

        Bundle bundle = new Bundle();
        bundle.putString(AppConstants.NEWS_ID, id);
        bundle.putString(AppConstants.NEWS_TYPE, type);
        bundle.putInt(AppConstants.NEWS_INDEX, index);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public int getCount() {
        return array_home_items.length;
    }

    //ViewPager与TabLayout绑定后，这里获取到PageTitle就是Tab的Text
    @Override
    public CharSequence getPageTitle(int position) {
        return array_home_items[position];
    }
}
