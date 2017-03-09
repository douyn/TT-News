package com.doudou.toutiao.common.base;

/**
 * Created by Administrator on 2017/2/25.
 */

public class BasePresenter<V extends BaseView> implements Presenter{

    BaseView view;

    @Override
    public void attachView(BaseView view) {

    }

    @Override
    public void detachView() {

    }

    /**
     * 判断时候绑定了view
     * @return
     */
    public boolean isAttachView () {
        return view != null;
    }

    /**
     * 获取view对象
     */
    public BaseView getView () {
        return this.view;
    }
}
