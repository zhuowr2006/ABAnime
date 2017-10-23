package com.hema.abanime.abanime.ui.fanju;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hema.abanime.abanime.MVP.ui.Vlistener;
import com.hema.abanime.abanime.R;
import com.hema.abanime.abanime.base.BaseFragment;
import com.hema.abanime.abanime.ui.fanju.adapter.MultipleItemQuickAdapter;
import com.hema.abanime.abanime.utils.BannerGlideImageLoader;
import com.trello.rxlifecycle2.components.support.RxFragmentActivity;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.exception.ApiException;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Homa on 2017/9/11.
 */

public class FanjuFragment extends BaseFragment implements Vlistener, SwipeRefreshLayout.OnRefreshListener{


    @BindView(R.id.fanju_rcview)
    RecyclerView fanjuRcview;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;
    Unbinder unbinder;

    private  Banner banner;
    private  View top;

    private fanjuP fanjuP;
    private MultipleItemQuickAdapter adapter;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_fanju;
    }

    @Override
    public void onInit() {
        fanjuP = new fanjuP(this);//数据控制类
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setColorSchemeColors(mActivity.getResources().getColor(R.color.bili_red));

        fanjuRcview.setLayoutManager(new GridLayoutManager(mActivity, 3));
        adapter = new MultipleItemQuickAdapter(mActivity, fanjuP.getDatalist());

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
        fanjuP.startPost((RxFragmentActivity) mActivity);
        swipeLayout.setRefreshing(true);
    }
    private boolean isFrist=true;

    @Override
    public void onNext(String name) {
        swipeLayout.setRefreshing(false);
        if (swipeLayout.isRefreshing()&&!isFrist){
            adapter.setNewData(fanjuP.getDatalist());
            //设置图片集合
            banner.setImages(fanjuP.getUrls());
            //banner设置方法全部调用完毕时最后调用
            banner.start();
            return;
        }
        adapter.openLoadAnimation();
        adapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                return fanjuP.getDatalist().get(position).getSpanSize();
            }
        });

        fanjuRcview.setAdapter(adapter);
        //设置图片集合
        banner.setImages(fanjuP.getUrls());
        //banner设置方法全部调用完毕时最后调用
        banner.start();
        isFrist=false;
    }

    @Override
    public void onError(ApiException e) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onRefresh() {
        fanjuP.cleran();
        fanjuP.startPost((RxFragmentActivity) mActivity);
    }
}
