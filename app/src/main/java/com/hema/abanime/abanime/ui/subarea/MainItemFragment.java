package com.hema.abanime.abanime.ui.subarea;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hema.abanime.abanime.MVP.ui.Vlistener;
import com.hema.abanime.abanime.R;
import com.hema.abanime.abanime.base.BaseFragment;
import com.hema.abanime.abanime.ui.info.VideoInfoActivity;
import com.hema.abanime.abanime.ui.subarea.bean.VideoItemexBean;
import com.hema.abanime.abanime.utils.ImageLoaderUtil;
import com.trello.rxlifecycle2.components.support.RxFragmentActivity;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.exception.ApiException;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Homa on 2017/9/15.
 */

public class MainItemFragment extends BaseFragment implements Vlistener, SwipeRefreshLayout.OnRefreshListener{

    private static final String TAG = "SubareaFragment";

    @BindView(R.id.fanju_rcview)
    RecyclerView fanjuRcview;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;

    private mainItemP mainItemP;
    private BaseQuickAdapter adapter;

    private int videoType = 1; // 视频类别
    private int paihang = 0; // 视频类别
    private List<VideoItemexBean> templist;


    @SuppressLint("ValidFragment")
    public MainItemFragment(String videoType, int paihang) {
        this.videoType = Integer.valueOf(videoType);
        this.paihang = paihang;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_fanju;
    }

    @Override
    public void onInit() {
        mainItemP = new mainItemP(this,paihang);//数据控制类
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setColorSchemeColors(mActivity.getResources().getColor(R.color.bili_red));

        fanjuRcview.setLayoutManager(new LinearLayoutManager(mActivity));
        adapter = new MainItemAdapter(R.layout.item_mainitem, mainItemP.getData());
        adapter.openLoadAnimation();
        fanjuRcview.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent i = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("videoItemdata", mainItemP.getData().get(position));
                bundle.putInt("type",1);
                i.setClass(getActivity(), VideoInfoActivity.class);
                i.putExtras(bundle);
                startActivity(i);
            }
        });
        mainItemP.startPost((RxFragmentActivity) mActivity,videoType);
    }

    @Override
    public void onRefresh() {
        mainItemP.cleran();
        mainItemP.startPost((RxFragmentActivity) mActivity,videoType);
    }

    @Override
    public void onNext(String name) {
        if (swipeLayout.isRefreshing()){
            adapter.setNewData(mainItemP.getData());
            swipeLayout.setRefreshing(false);
            return;
        }
        adapter.openLoadAnimation();
        fanjuRcview.setAdapter(adapter);
    }

    @Override
    public void onError(ApiException e) {

    }


    public class MainItemAdapter extends BaseQuickAdapter<VideoItemexBean, BaseViewHolder> {

        public MainItemAdapter(@LayoutRes int layoutResId, @Nullable List<VideoItemexBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, VideoItemexBean item) {
            helper.setText(R.id.include_item_mainitem_title,item.getTitle());
            helper.setText(R.id.up_name,item.getAuthor());
            helper.setText(R.id.include_item_mainitem_bf,item.getPlay());
            if (paihang == 7) {
                helper.getView(R.id.pl_layout).setVisibility(View.GONE);
            } else {
                helper.setText(R.id.include_item_mainitem_pl,item.getVideo_review());
            }
            ImageView img=helper.getView(R.id.include_item_mainitem_img);
            ImageLoaderUtil.loader(mActivity,item.getPic(),img);
        }
    }
}
