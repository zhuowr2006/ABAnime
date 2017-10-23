package com.hema.abanime.abanime.ui.recommend;

import com.google.gson.Gson;
import com.hema.abanime.abanime.MVP.model.Mlistener;
import com.hema.abanime.abanime.MVP.presenter.PMlistener;
import com.hema.abanime.abanime.MVP.presenter.Plistener;
import com.hema.abanime.abanime.MVP.ui.Vlistener;
import com.hema.abanime.abanime.R;
import com.hema.abanime.abanime.ui.recommend.bean.BannerBean;
import com.hema.abanime.abanime.ui.recommend.bean.MySection;
import com.hema.abanime.abanime.ui.recommend.bean.VideoItemBean;
import com.trello.rxlifecycle2.components.support.RxFragmentActivity;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.exception.ApiException;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by Homa on 2017/9/13.
 */

public class recomP implements Plistener, PMlistener {
    private static final String TAG = "fanjuP";
    private Vlistener vlistener;
    private Mlistener mlistener;
    private int iscomplet=0;


    private Gson gson=new Gson();

    private List<MySection> data=new ArrayList<>();

    private List<String> urls=new ArrayList<>();


    public recomP(Vlistener viewListener) {
        this.vlistener = viewListener;
        mlistener=new recomM(this);
    }

    @Override
    public void startPost(RxFragmentActivity rxFragmentActivity) {
        mlistener.startPost(rxFragmentActivity);
    }

    public void cleran(){
        data.clear();
        urls.clear();
    }

    @Override
    public void onNext(String resulte, String mothead) {

        if (mothead.equals("getRecommendedInfo")){
            setDingList(resulte);
            iscomplet++;
        }else {
            BannerBean bean=gson.fromJson(resulte,BannerBean.class);
            for (BannerBean.DataBean dataBean : bean.getData()) {
                urls.add(dataBean.getImage());
            }
            iscomplet++;
        }


        if (iscomplet==2){
            vlistener.onNext(mothead);
            iscomplet=0;
        }

    }

    private void setDingList(final String json) {

        //0:douga 动画  1:music 音乐 2：游戏 game 3：ent 娱乐 4：technology 电视剧 5：bangumi 番剧 6：movie 电影 7：technology 科技
        //8:kichiku 鬼畜 9：dace 舞蹈 10：fashion 时尚
        String[] names = new String[]{"douga", "music", "game", "ent", "teleplay", "bangumi", "movie", "technology", "kichiku", "dance", "fashion"};
        Observable.fromArray(names)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        JSONObject jsondata = null;
                        try {
                            jsondata = new JSONObject(json).getJSONObject(s);
                            data.add(getheaditem(s));//加个空的留作分区标题
                            for (int c = 0; c < 4; c++) {
                                data.add(new MySection(VideoItemBean.getdata(jsondata, c)));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
    private MySection getheaditem(String name){
        MySection bean=null;
        switch (name){
            case "douga":
                bean=new MySection(true,"动画",R.mipmap.dh_icon);
                break;
            case "music":
                bean=new MySection(true,"音乐",R.mipmap.yy_icon);
                break;
            case "game":
                bean=new MySection(true,"游戏",R.mipmap.yx_icon);
                break;
            case "ent":
                bean=new MySection(true,"娱乐",R.mipmap.yl_icon);
                break;
            case "teleplay":
                bean=new MySection(true,"电视剧",R.mipmap.dsj_icon);
                break;
            case "bangumi":
                bean=new MySection(true,"番剧",R.mipmap.fj_icon);
                break;
            case "movie":
                bean=new MySection(true,"电影",R.mipmap.dy_icon);
                break;
            case "technology":
                bean=new MySection(true,"科技",R.mipmap.kj_icon);
                break;
            case "kichiku":
                bean=new MySection(true,"鬼畜",R.mipmap.gc_icon);
                break;
            case "dance":
                bean=new MySection(true,"舞蹈",R.mipmap.wd_icon);
                break;
            case "fashion":
                bean=new MySection(true,"时尚",R.mipmap.ss_icon);
                break;

        }
        return bean;
    }

    public List<MySection> getData() {
        return data;
    }

    public List<String> getUrls() {
        return urls;
    }

    @Override
    public void onError(ApiException e) {
        vlistener.onError(e);
    }

}
