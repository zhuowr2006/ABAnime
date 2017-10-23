package com.hema.abanime.abanime.ui.live;

import com.google.gson.Gson;
import com.hema.abanime.abanime.MVP.presenter.PMlistener;
import com.hema.abanime.abanime.MVP.presenter.Plistener;
import com.hema.abanime.abanime.MVP.ui.Vlistener;
import com.hema.abanime.abanime.ui.live.bean.LiveAppIndexInfo;
import com.hema.abanime.abanime.ui.live.bean.LiveSection;
import com.trello.rxlifecycle2.components.support.RxFragmentActivity;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.exception.ApiException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.subscribers.DefaultSubscriber;

/**
 * Created by Homa on 2017/9/14.
 */

public class liveP implements Plistener, PMlistener {

    private static final String TAG = "fanjuP";
    private Vlistener vlistener;
    private liveM mlistener;
    private int iscomplet=0;


    private Gson gson=new Gson();

    private List<LiveSection> data=new ArrayList<>();

    private List<String> urls=new ArrayList<>();

    private String liveUrl;
    private String liveTitle;


    public liveP(Vlistener viewListener) {
        this.vlistener = viewListener;
        mlistener=new liveM(this);
    }

    @Override
    public void startPost(RxFragmentActivity rxFragmentActivity) {
        mlistener.startPost(rxFragmentActivity);
    }
    public void startPostLive(RxFragmentActivity rxAppCompatActivity,String cid, String appkey, String ts, String sign) {
        mlistener.startPostLive(rxAppCompatActivity,cid,appkey,ts,sign);
    }

    public void cleran(){
        data.clear();
        urls.clear();
    }

    @Override
    public void onNext(String resulte, final String mothead) {

        if (mothead.equals("getLiveAppIndex")){
        LiveAppIndexInfo bean=gson.fromJson(resulte,LiveAppIndexInfo.class);
        List<LiveAppIndexInfo.DataBean.BannerBean> banners = bean.getData().getBanner();
        for (LiveAppIndexInfo.DataBean.BannerBean banner : banners) {
            urls.add(banner.getImg());
        }

        List<LiveAppIndexInfo.DataBean.PartitionsBean> list=bean.getData().getPartitions();
        Flowable.fromIterable(list).flatMap(new Function<LiveAppIndexInfo.DataBean.PartitionsBean, Publisher<LiveAppIndexInfo.DataBean.PartitionsBean.LivesBean>>() {
            @Override
            public Publisher<LiveAppIndexInfo.DataBean.PartitionsBean.LivesBean> apply(@NonNull LiveAppIndexInfo.DataBean.PartitionsBean partitionsBean) throws Exception {
                //添加标题
                data.add(new LiveSection(true,partitionsBean.getPartition().getName(),partitionsBean.getPartition().getSub_icon().getSrc()));
                return Flowable.fromIterable(partitionsBean.getLives());
            }
        }).subscribe(new DefaultSubscriber<LiveAppIndexInfo.DataBean.PartitionsBean.LivesBean>() {
            @Override
            public void onNext(LiveAppIndexInfo.DataBean.PartitionsBean.LivesBean livesBean) {
                data.add(new LiveSection(livesBean));
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {
                vlistener.onNext(mothead);
            }
        });
        }else {//直播地址
            liveUrl=parseLiveUrl(resulte);
            vlistener.onNext(mothead);
        }
    }
    private String parseLiveUrl(String xml) {
        Document document = null;
        try {
            document = DocumentHelper.parseText(xml);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        assert document != null;
        Element rootElement = document.getRootElement();
        Element durlElement = rootElement.element("durl");
        Element urlElement = durlElement.element("url");
        String url = urlElement.getText();
        return url;
    }

    public List<LiveSection> getData() {
        return data;
    }

    public List<String> getUrls() {
        return urls;
    }

    public String getLiveUrl() {
        return liveUrl;
    }

    public String getLiveTitle() {
        return liveTitle;
    }

    public liveP setLiveTitle(String liveTitle) {
        this.liveTitle = liveTitle;
        return this;
    }

    @Override
    public void onError(ApiException e) {
        vlistener.onError(e);
    }
}
