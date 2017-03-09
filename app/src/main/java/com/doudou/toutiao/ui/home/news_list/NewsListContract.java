package com.doudou.toutiao.ui.home.news_list;

import java.util.List;

/**
 * Created by Administrator on 2017/2/25.
 */

public interface NewsListContract {

    public interface IView {
        // 显示新闻列表
        void showNewsList(List<NewsListModel> data);
        void showLoadingView();
        void hideLoadingView();
        void showErrorView();
    }

    public interface IPresenter<IView> {
        // 获取新闻列表
        void getNewsList(String type, String id, int startPage);
    }
}
