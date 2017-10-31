package com.hema.abanime.abanime.ui.live;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hema.abanime.abanime.MVP.ui.Vlistener;
import com.hema.abanime.abanime.R;
import com.hema.abanime.abanime.base.BaseFragment;
import com.hema.abanime.abanime.play.PlayActivity;
import com.hema.abanime.abanime.ui.live.adapter.LiveSectionAdapter;
import com.hema.abanime.abanime.utils.BannerGlideImageLoader;
import com.hema.abanime.abanime.utils.Md5;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.exception.ApiException;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import butterknife.BindView;

/**
 * Created by Homa on 2017/9/14.
 */

public class liveFragment extends BaseFragment implements Vlistener, SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.fanju_rcview)
    RecyclerView fanjuRcview;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;

    private Banner banner;
    private View top;

    private liveP recomP;
    private LiveSectionAdapter adapter;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_fanju;
    }



    @Override
    public void onInit() {
        recomP = new liveP(this);//数据控制类
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setColorSchemeColors(mActivity.getResources().getColor(R.color.bili_red));

        fanjuRcview.setLayoutManager(new GridLayoutManager(mActivity, 2));
        adapter = new LiveSectionAdapter(mActivity, R.layout.item_home_recom_body, R.layout.item_home_recom_head, recomP.getData());

        top = mActivity.getLayoutInflater().inflate(R.layout.item_home_bangumi_banner, (ViewGroup) fanjuRcview.getParent(), false);
        adapter.addHeaderView(top);
        banner = (Banner) top.findViewById(R.id.banner);
        //设置图片加载器
        banner.setImageLoader(new BannerGlideImageLoader());
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        banner.setOnBannerListener(new OnBannerListener() {//监听单击
            @Override
            public void OnBannerClick(int position) {
            }
        });
        fanjuRcview.setAdapter(adapter);
        recomP.startPostForFragment(this);
        swipeLayout.setRefreshing(true);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (position != 0 || position != 22 || position != 33 || position != 44 || position != 55 || position != 66 || position != 77) {
                    String appKey = "<Bilibili App Key Here>";
                    String secretKey = "<Bilibili App Secret Key Here>";
                    String cid = "" + recomP.getData().get(position).t.getRoom_id();
                    String ts = "" + System.currentTimeMillis();
                    String apiParams = "appkey=" + appKey + "&" + "cid=" + cid + "&" + "player=1&quality=0&ts=" + ts;

                    recomP.setLiveTitle(recomP.getData().get(position).t.getTitle());
                    String sign = Md5.strToMd5Low32(apiParams + secretKey);
                    recomP.startPostLive(liveFragment.this, cid, appKey, ts, sign);
                }
            }
        });
    }

    private boolean isFrist = true;

    @Override
    public void onNext(String name) {
        if (name.equals("getLiveAppIndex")) {
            swipeLayout.setRefreshing(false);
            if (swipeLayout.isRefreshing() && !isFrist) {
                adapter.setNewData(recomP.getData());
                //设置图片集合
                banner.setImages(recomP.getUrls());
                //banner设置方法全部调用完毕时最后调用
                banner.start();
                return;
            }
            adapter.openLoadAnimation();
            fanjuRcview.setAdapter(adapter);
            //设置图片集合
            banner.setImages(recomP.getUrls());
            //banner设置方法全部调用完毕时最后调用
            banner.start();
            isFrist = false;
        } else {
            Intent intent = new Intent();
            intent.setClass(mActivity, PlayActivity.class);
            intent.putExtra("video1", recomP.getLiveUrl());
//                    intent.putExtra("video2",videoUrlList.get(1));
            intent.putExtra("title", recomP.getLiveTitle());
            startActivity(intent);
        }
    }

    @Override
    public void onError(ApiException e) {
        swipeLayout.setRefreshing(false);
    }


    @Override
    public void onRefresh() {
        recomP.cleran();
        recomP.startPostForFragment(this);
    }
}
