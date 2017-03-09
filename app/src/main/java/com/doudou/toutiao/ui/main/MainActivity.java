package com.doudou.toutiao.ui.main;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.doudou.toutiao.R;
import com.doudou.toutiao.adapter.MainViewPagerAdapter;
import com.doudou.toutiao.ui.home.fragment.HomeFragment;
import com.doudou.toutiao.ui.me.MineFragment;
import com.doudou.toutiao.ui.pic.PictureFragment;
import com.doudou.toutiao.ui.video.VideoFragment;
import com.jaydenxiao.common.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class MainActivity extends BaseActivity implements TabHost.OnTabChangeListener, ViewPager.OnPageChangeListener {

    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.navigationview)
    NavigationView navigationview;
    @Bind(R.id.layout_drawer)
    DrawerLayout layoutDrawer;
    @Bind(R.id.fragment_tabhost)
    FragmentTabHost fragmentTabhost;
    @Bind(R.id.viewpager)
    ViewPager viewPager;

    String[] array_tab_titles = {"首页", "图片", "视频", "我的"};
    int[] array_tab_images = {R.drawable.bar_news_icon, R.drawable.bar_scan_icon, R.drawable.big_star_default_icon, R.drawable.bar_mine_icon};
    Class[] array_fragment = {HomeFragment.class, PictureFragment.class, VideoFragment.class, MineFragment.class};
    List<Fragment> list_fragment = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        initToolBar();
        initDrawer();
        initNavigationView();

        initFragmentTabHost();
        initViewPager();
    }

    private void initViewPager() {
        HomeFragment homeFragment = new HomeFragment();
        PictureFragment pictureFragment = new PictureFragment();
        VideoFragment videoFragment = new VideoFragment();
        MineFragment mineFragment = new MineFragment();

        list_fragment.add(homeFragment);
        list_fragment.add(pictureFragment);
        list_fragment.add(videoFragment);
        list_fragment.add(mineFragment);

        viewPager.setAdapter(new MainViewPagerAdapter(getSupportFragmentManager(), list_fragment));
        viewPager.setOnPageChangeListener(this);
    }

    private void initFragmentTabHost() {
        fragmentTabhost.setup(this, getSupportFragmentManager(), R.id.viewpager);
        fragmentTabhost.setOnTabChangedListener(this);
        fragmentTabhost.getTabWidget().setDividerDrawable(null);

        /*新建Tabspec选项卡并设置Tab菜单栏的内容和绑定对应的Fragment*/
        for (int i = 0; i < array_tab_titles.length; i++) {
            // 给每个Tab按钮设置标签、图标和文字
            TabHost.TabSpec tabSpec = fragmentTabhost.newTabSpec(i + "").setIndicator(getTabView(i));
            // 将Tab按钮添加进Tab选项卡中，并绑定Fragment
            fragmentTabhost.addTab(tabSpec, array_fragment[i], null);
        }
    }

    private View getTabView(int i) {
        View view = getLayoutInflater().inflate(R.layout.layout_tab_content, null);
        ImageView iv_icon = (ImageView) view.findViewById(R.id.tab_icon);
        TextView tv_title = (TextView) view.findViewById(R.id.tab_title);
        iv_icon.setImageResource(array_tab_images[i]);
        tv_title.setText(array_tab_titles[i]);
        return view;
    }

    private void initNavigationView() {
        navigationview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                Toast.makeText(MainActivity.this, "click", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    private void initDrawer() {
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, layoutDrawer, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerToggle.syncState();
        layoutDrawer.setDrawerListener(drawerToggle);
    }

    private void initToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbarTitle.setText("首页");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabChanged(String s) {
        int index = Integer.valueOf(s);
        viewPager.setCurrentItem(index);
        toolbarTitle.setText(array_tab_titles[index]);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        fragmentTabhost.setCurrentTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
