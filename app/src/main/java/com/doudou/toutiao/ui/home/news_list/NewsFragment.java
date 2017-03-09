package com.doudou.toutiao.ui.home.news_list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.widget.LoadMoreFooterView;
import com.doudou.toutiao.R;
import com.doudou.toutiao.adapter.NewsListAdapter;
import com.doudou.toutiao.app.AppConstants;
import com.jaydenxiao.common.commonwidget.LoadingTip;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/2/25.
 */

public class NewsFragment extends Fragment implements NewsListContract.IView {

    @Bind(R.id.irc)
    IRecyclerView irc;
    @Bind(R.id.loadedTip)
    LoadingTip loadedTip;

    View rootView;

    NewsListPresenter mPresenter;

    String mNewsID;
    String mNewsType;
    int mStartPage;

    List<NewsListModel> mNewsList;
    NewsListAdapter mNewsListAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_news, container, false);
            ButterKnife.bind(this, rootView);
            mPresenter = new NewsListPresenter(this);
            initView();
        }

        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }

        return rootView;
    }

    private void initView() {
        if (getArguments() != null) {
            mNewsID = getArguments().getString(AppConstants.NEWS_ID);
            mNewsType = getArguments().getString(AppConstants.NEWS_TYPE);
        }

        mPresenter.getNewsList(mNewsType, mNewsID, mStartPage);

        irc.setLayoutManager(new LinearLayoutManager(getActivity()));
        mNewsListAdapter = new NewsListAdapter(getActivity(), mNewsList);
        irc.setAdapter(mNewsListAdapter);
    }

    @Override
    public void showNewsList(List<NewsListModel> data) {
        if (data != null) {
            mStartPage += 20;
            if (mNewsListAdapter.getPageBean().isRefresh()) {
                irc.setRefreshing(false);
                mNewsListAdapter.replaceAll(data);
            } else {
                if (data.size() > 0) {
                    irc.setLoadMoreStatus(LoadMoreFooterView.Status.GONE);
                    mNewsListAdapter.addAll(data);
                } else {
                    irc.setLoadMoreStatus(LoadMoreFooterView.Status.THE_END);
                }
            }
        }
    }

    @Override
    public void showLoadingView() {
        Toast.makeText(getActivity(), "Loading...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideLoadingView() {

    }

    @Override
    public void showErrorView() {
        Toast.makeText(getActivity(), "ERROR...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
