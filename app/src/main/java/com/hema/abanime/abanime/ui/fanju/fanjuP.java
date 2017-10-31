package com.hema.abanime.abanime.ui.fanju;

import com.google.gson.Gson;
import com.hema.abanime.abanime.MVP.model.Mlistener;
import com.hema.abanime.abanime.MVP.presenter.PMlistener;
import com.hema.abanime.abanime.MVP.presenter.Plistener;
import com.hema.abanime.abanime.MVP.ui.Vlistener;
import com.hema.abanime.abanime.R;
import com.hema.abanime.abanime.ui.fanju.bean.BangumiAppIndexInfo;
import com.hema.abanime.abanime.ui.fanju.bean.BangumiRecommendInfo;
import com.hema.abanime.abanime.ui.fanju.bean.BangumiTitleBean;
import com.hema.abanime.abanime.ui.fanju.bean.MultipleItem;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle2.components.support.RxFragment;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.exception.ApiException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Homa on 2017/9/11.
 */

public class fanjuP implements Plistener, PMlistener {
    private static final String TAG = "fanjuP";
    private Vlistener vlistener;
    private Mlistener mlistener;
    private int iscomplet=0;

    private List<BangumiAppIndexInfo.ResultBean.AdBean.HeadBean> banners = new ArrayList<>();//广告

    private int season;
    private Gson gson=new Gson();


    private List<MultipleItem> datalist=new ArrayList<>();
    private List<String> urls=new ArrayList<>();


    public fanjuP(Vlistener viewListener) {
        this.vlistener = viewListener;
        mlistener=new fanjuM(this);
    }

    public void cleran(){
        banners.clear();
        datalist.clear();
        urls.clear();
    }

    @Override
    public void onNext(String resulte, String mothead) {

        if (mothead.equals("getBangumiAPI")){
            BangumiAppIndexInfo info= gson.fromJson(resulte,BangumiAppIndexInfo.class);

            banners.addAll(info.getResult().getAd().getHead());
            for (BangumiAppIndexInfo.ResultBean.AdBean.HeadBean banner : banners) {
                urls.add(banner.getImg());
            }
            season = info.getResult().getPrevious().getSeason();
            datalist.add(new BangumiTitleBean("新番连载",R.drawable.ic_lianzai));
            datalist.addAll(info.getResult().getSerializing());
            datalist.add(getSeasonData());
            datalist.addAll(info.getResult().getPrevious().getList());
            datalist.add(new BangumiTitleBean("番剧推荐",R.drawable.ic_tuijian));
            iscomplet++;
        }else {
            BangumiRecommendInfo recommendInfo= gson.fromJson(resulte,BangumiRecommendInfo.class);
//            bangumiRecommends.addAll(recommendInfo.getResult());
            datalist.addAll(recommendInfo.getResult());
            iscomplet++;
        }


        if (iscomplet==2){
            vlistener.onNext(mothead);
            iscomplet=0;
        }

    }
    private BangumiTitleBean getSeasonData(){
        BangumiTitleBean ban = null;
        switch (season)
        {
            case 1:
                //冬季
                ban=new BangumiTitleBean("1月新番",R.drawable.bangumi_home_ic_season_1);
                break;

            case 2:
                //春季
                ban=new BangumiTitleBean("4月新番",R.drawable.bangumi_home_ic_season_2);
                break;

            case 3:
                //夏季
                ban=new BangumiTitleBean("7月新番",R.drawable.bangumi_home_ic_season_3);
                break;

            case 4:
                //秋季
                ban=new BangumiTitleBean("10月新番",R.drawable.bangumi_home_ic_season_4);
                break;
        }
        return ban;
    }


    @Override
    public void onError(ApiException e) {
        vlistener.onError(e);
    }

    public List<MultipleItem> getDatalist() {
        return datalist;
    }
    public List<String> getUrls() {
        return urls;
    }

    @Override
    public void startPostForActivity(RxAppCompatActivity rxAppCompatActivity) {

    }

    @Override
    public void startPostForFragment(RxFragment rxFragment) {
        mlistener.startPostForFragment(rxFragment);
    }
}
