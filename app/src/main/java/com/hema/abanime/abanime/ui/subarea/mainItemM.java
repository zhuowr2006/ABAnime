package com.hema.abanime.abanime.ui.subarea;

import com.hema.abanime.abanime.MVP.presenter.PMlistener;
import com.hema.abanime.abanime.net.api.BaseUrl;
import com.hema.abanime.abanime.net.api.HttpApiManager;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.exception.ApiException;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.http.HttpManager;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.listener.HttpOnNextListener;

/**
 * Created by Homa on 2017/9/15.
 */

public class mainItemM implements Mxlistener, HttpOnNextListener {

    private PMlistener pVlistener;

    public mainItemM(PMlistener pVlistener) {
        this.pVlistener = pVlistener;
    }

    @Override
    public void onNext(String resulte, String mothead) {
        pVlistener.onNext(resulte, mothead);
    }

    @Override
    public void onError(ApiException e, String method) {
        pVlistener.onError(e);
    }


    @Override
    public void startPost(RxAppCompatActivity rxAppCompatActivity, int type) {
        HttpManager manager = new HttpManager(this, rxAppCompatActivity);
        if (type<1000){
            HttpApiManager.getVideoList(manager,BaseUrl.getVideoListURL(type));
        }else {
            HttpApiManager.getVideoPHList(manager,BaseUrl.getVideoListURL(type));
        }
    }
}
