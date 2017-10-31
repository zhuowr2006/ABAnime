package com.hema.abanime.abanime.ui.live;

import com.hema.abanime.abanime.MVP.model.Mlistener;
import com.hema.abanime.abanime.MVP.presenter.PMlistener;
import com.hema.abanime.abanime.net.api.HttpApiManager;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle2.components.support.RxFragment;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.exception.ApiException;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.http.HttpManager;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.listener.HttpOnNextListener;

/**
 * Created by Homa on 2017/9/14.
 */

public class liveM  implements Mlistener, HttpOnNextListener {

    private PMlistener pVlistener;

    public liveM(PMlistener pVlistener) {
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


    public void startPostLive(RxFragment rxFragment, String cid, String appkey, String ts, String sign) {
        HttpManager manager = new HttpManager(this, rxFragment);
        HttpApiManager.getLiveUrl(manager,cid,appkey, ts, sign);
    }

    @Override
    public void startPostForActivity(RxAppCompatActivity rxAppCompatActivity) {

    }

    @Override
    public void startPostForFragment(RxFragment rxFragment) {
        HttpManager manager = new HttpManager(this, rxFragment);
        HttpApiManager.getLiveAppIndex(manager);
    }
}
