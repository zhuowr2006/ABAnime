package com.hema.abanime.abanime.ui.subarea;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hema.abanime.abanime.R;
import com.hema.abanime.abanime.base.BaseFragment;

import java.util.Arrays;

import butterknife.BindView;

/**
 * Created by Homa on 2017/9/15.
 */

public class SubareaFragment extends BaseFragment {

    private static final String TAG = "SubareaFragment";

    @BindView(R.id.fanju_rcview)
    RecyclerView fanjuRcview;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;


    private BaseQuickAdapter adapter;

    private String[] mVals = new String[]
            {"动画", "音乐", "游戏", "娱乐", "电视剧", "番剧", "电影", "科技", "鬼畜", "舞蹈", "时尚", "直播"};

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_fanju;
    }

    @Override
    public void onInit() {
        swipeLayout.setEnabled(false);
        GridLayoutManager layoutManager=new GridLayoutManager(mActivity, 3);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        fanjuRcview.setLayoutManager(layoutManager);
        adapter = new SubareaAdapter(R.layout.item_home_subarea, mVals);
        adapter.openLoadAnimation();
        fanjuRcview.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent();
                boolean isonline = false;
                intent.putExtra("type", position);
                if (isonline) {
//                    intent.setClass(mActivity, TVActivity.class);
                    mActivity.startActivity(intent);
                } else {
                    intent.setClass(mActivity, MainItemActivity.class);
                    mActivity.startActivity(intent);
                }
            }
        });
    }


    public class SubareaAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
        public SubareaAdapter(int layoutResId, String[] data) {
            super(layoutResId, Arrays.asList(data));
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.setText(R.id.item_sb_title, item);
            ImageView imgv=helper.getView(R.id.item_sb_img);
            switch (item) {
                case "动画":
                    imgv.setImageResource(R.mipmap.dh_icon);
                    break;
                case "音乐":
                    imgv.setImageResource(R.mipmap.yy_icon);
                    break;
                case "游戏":
                    imgv.setImageResource(R.mipmap.yx_icon);
                    break;
                case "娱乐":
                    imgv.setImageResource(R.mipmap.yl_icon);
                    break;
                case "电视剧":
                    imgv.setImageResource(R.mipmap.dsj_icon);
                    break;
                case "番剧":
                    imgv.setImageResource(R.mipmap.fj_icon);
                    break;
                case "电影":
                    imgv.setImageResource(R.mipmap.dy_icon);
                    break;
                case "科技":
                    imgv.setImageResource(R.mipmap.kj_icon);
                    break;
                case "鬼畜":
                    imgv.setImageResource(R.mipmap.gc_icon);
                    break;
                case "舞蹈":
                    imgv.setImageResource(R.mipmap.wd_icon);
                    break;
                case "时尚":
                    imgv.setImageResource(R.mipmap.ss_icon);
                    break;
                case "直播":
                    imgv.setImageResource(R.mipmap.zb_icon);
                    break;
            }

        }
    }
}
