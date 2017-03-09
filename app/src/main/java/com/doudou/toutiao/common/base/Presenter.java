package com.doudou.toutiao.common.base;

/**
 * Created by Administrator on 2017/2/25.
 */

public interface Presenter<V extends BaseView> {
    /**
     * Presenter和对应的view绑定
     * @param view
     */
    void attachView(BaseView view);

    /**
     * Presenter和对应view解绑
     */
    void detachView();
}
