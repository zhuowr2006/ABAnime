package com.hema.abanime.abanime.ui.subarea;

import android.util.Log;

import com.google.gson.Gson;
import com.hema.abanime.abanime.MVP.presenter.PMlistener;
import com.hema.abanime.abanime.MVP.ui.Vlistener;
import com.hema.abanime.abanime.ui.subarea.bean.VideoItemexBean;
import com.hema.abanime.abanime.ui.subarea.bean.VideolistBean;
import com.hema.abanime.abanime.ui.subarea.bean.VideolistexBean;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle2.components.support.RxFragmentActivity;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.exception.ApiException;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Homa on 2017/9/15.
 */

public class mainItemP implements Pxlistener, PMlistener {
    private static final String TAG = "mainItemP";
    private Vlistener vlistener;
    private Mxlistener mlistener;


    private Gson gson=new Gson();

    private List<VideoItemexBean> templist=new ArrayList<>();

    private int paihang = 0; // 视频类别

    public mainItemP(Vlistener viewListener,int paihang) {
        this.vlistener = viewListener;
        this.paihang=paihang;
        mlistener=new mainItemM(this);
    }

    @Override
    public void startPost(RxAppCompatActivity rxFragmentActivity, int type) {
        mlistener.startPost(rxFragmentActivity,type);
    }

    public void cleran(){
        templist.clear();
    }

    @Override
    public void onNext(String resulte, String mothead) {

        if (mothead.equals("getVideoPHList")){
            VideolistexBean data = gson.fromJson(resulte, VideolistexBean.class);
            Log.i(TAG, "onNext: =======================s");
            if (data == null) {
                templist = new ArrayList<VideoItemexBean>();
            }
            for (int i = 0; i < 10; i++) {
                templist.add(data.getRank().getList().get(i));
            }
        }else {
            VideolistBean data = gson.fromJson(resulte, VideolistBean.class);
            if (templist != null && templist.size() > 0) {
                templist.clear();
            }
            templist.addAll(data.getList());
        }
            vlistener.onNext(mothead);

    }
    public List<VideoItemexBean> getData() {
        return templist;
    }


    @Override
    public void onError(ApiException e) {
        vlistener.onError(e);
    }

}