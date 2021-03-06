package com.hema.abanime.abanime.ui.info;

import com.hema.abanime.abanime.MVP.model.Mlistener;
import com.hema.abanime.abanime.MVP.presenter.PMlistener;
import com.hema.abanime.abanime.net.api.HttpApiManager;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle2.components.support.RxFragment;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.exception.ApiException;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.http.HttpManager;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.listener.HttpOnNextListener;

import java.util.Map;

/**
 * Created by Homa on 2017/9/14.
 */

public class InfoM implements Mlistener, HttpOnNextListener {

    private PMlistener pVlistener;

    public InfoM(PMlistener pVlistener) {
        this.pVlistener = pVlistener;
    }

    @Override
    public void onNext(String resulte, String mothead) {
        System.out.println("=="+resulte);
        pVlistener.onNext(resulte, mothead);
    }

    @Override
    public void onError(ApiException e, String method) {
        pVlistener.onError(e);
    }


    public void startPostVideoTag(RxAppCompatActivity rxAppCompatActivity,String aid) {
        HttpManager manager = new HttpManager(this, rxAppCompatActivity);
        HttpApiManager.getVideoTag(manager,aid);
    }
    public void startPostvideoDetails(RxAppCompatActivity rxAppCompatActivity,String aid) {
        HttpManager manager = new HttpManager(this, rxAppCompatActivity);
        HttpApiManager.getVideoDetails(manager,aid);
    }

    public void startPostAVSearchHtml(RxAppCompatActivity rxAppCompatActivity,String aid) {
        HttpManager manager = new HttpManager(this, rxAppCompatActivity);
        HttpApiManager.getBiliAVSearchHtml(manager,aid);
    }

    public void startPostAVVideoHtml(RxAppCompatActivity rxAppCompatActivity,Map<String, String> map) {
        HttpManager manager = new HttpManager(this, rxAppCompatActivity);
        HttpApiManager.getBiliAVVideoHtml(manager,map);
    }

    @Override
    public void startPostForActivity(RxAppCompatActivity rxAppCompatActivity) {

    }

    @Override
    public void startPostForFragment(RxFragment rxFragment) {

    }
}
