package com.hema.abanime.abanime.ui.subarea;

import com.trello.rxlifecycle2.components.support.RxFragmentActivity;

/**
 * Created by Homa on 2017/9/15.
 */

public interface Mxlistener {

    /**
     * 开始任务
     * @param rxAppCompatActivity
     */
    void startPost(RxFragmentActivity rxAppCompatActivity,int type);
}
