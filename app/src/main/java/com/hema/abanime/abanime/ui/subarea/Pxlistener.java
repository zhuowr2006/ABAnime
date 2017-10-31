package com.hema.abanime.abanime.ui.subarea;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

/**
 * p层提供给v层的接口
 * Created by WZG on 2016/12/26.
 */

public interface Pxlistener {
    /**
     * http请求
     * @param rxAppCompatActivity
     */
    void startPost(RxAppCompatActivity rxAppCompatActivity, int type);

}
