package com.doudou.toutiao.ui.home.news_list;

import com.doudou.toutiao.api.Api;
import com.doudou.toutiao.api.ApiConstants;
import com.doudou.toutiao.api.HostType;
import com.jaydenxiao.common.baserx.RxSchedulers;
import com.jaydenxiao.common.commonutils.TimeUtil;

import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * Created by Administrator on 2017/2/25.
 */

public class NewsListsModel {

    public NewsListsModel () {
        super();
    }

    public Observable<List<NewsListModel>> loadNews (String news_type, final String news_id, int news_start_page) {
        return Api.getDefault(HostType.NETEASE_NEWS_VIDEO).getNewsList(Api.getCacheControl(),news_type, news_id, news_start_page)
                .flatMap(new Func1<Map<String, List<NewsListModel>>, Observable<NewsListModel>>() {
                    @Override
                    public Observable<NewsListModel> call(Map<String, List<NewsListModel>> map) {
                        if (news_id.endsWith(ApiConstants.HOUSE_ID)) {
                            // 房产实际上针对地区的它的id与返回key不同
                            return Observable.from(map.get("北京"));
                        }
                        return Observable.from(map.get(news_id));
                    }
                })
                //转化时间
                .map(new Func1<NewsListModel, NewsListModel>() {
                    @Override
                    public NewsListModel call(NewsListModel newsSummary) {
                        String ptime = TimeUtil.formatDate(newsSummary.getPtime());
                        newsSummary.setPtime(ptime);
                        return newsSummary;
                    }
                })
                .distinct()//去重
                .toSortedList(new Func2<NewsListModel, NewsListModel, Integer>() {
                    @Override
                    public Integer call(NewsListModel newsSummary, NewsListModel newsSummary2) {
                        return newsSummary2.getPtime().compareTo(newsSummary.getPtime());
                    }
                })
                //声明线程调度
                .compose(RxSchedulers.<List<NewsListModel>>io_main());
    }
}
