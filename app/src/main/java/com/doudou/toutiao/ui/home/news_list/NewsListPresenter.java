package com.doudou.toutiao.ui.home.news_list;

import com.doudou.toutiao.common.base.BasePresenter;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/2/25.
 */

public class NewsListPresenter extends BasePresenter implements NewsListContract.IPresenter<NewsListContract.IView> {

    NewsListContract.IView view;
    NewsListsModel model;

    public NewsListPresenter (NewsListContract.IView view) {
        this.view = view;
        model = new NewsListsModel();
    }

    @Override
    public void getNewsList (String type, String id, int startPage) {
        model.loadNews(type, id, startPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<NewsListModel>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hideLoadingView();
                        view.showErrorView();
                    }

                    @Override
                    public void onNext(List<NewsListModel> newsListModels) {
                        view.hideLoadingView();
                        view.showNewsList(newsListModels);
                    }

                    @Override
                    public void onStart() {
                        super.onStart();
                        view.showLoadingView();
                    }
                });
    }
}
