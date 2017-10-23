package com.hema.abanime.abanime.MVP.model;

import com.trello.rxlifecycle2.components.support.RxFragmentActivity;

/**
 * m层提供给p层的接口
 * Created by WZG on 2016/12/26.
 */

public interface Mlistener {

    /**
     * 开始任务
     * @param rxAppCompatActivity
     */
    void startPost(RxFragmentActivity rxAppCompatActivity);

}
