package com.hema.abanime.abanime.ui.fanju.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hema.abanime.abanime.R;
import com.hema.abanime.abanime.ui.fanju.bean.BangumiAppIndexInfo;
import com.hema.abanime.abanime.ui.fanju.bean.BangumiRecommendInfo;
import com.hema.abanime.abanime.ui.fanju.bean.BangumiTitleBean;
import com.hema.abanime.abanime.ui.fanju.bean.MultipleItem;
import com.hema.abanime.abanime.utils.ImageLoaderUtil;

import java.util.List;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 * modify by AllenCoder
 */
public class MultipleItemQuickAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {
    private Context context;

    public MultipleItemQuickAdapter(Context context, List<MultipleItem> data) {
        super(data);
        this.context = context;
        addItemType(MultipleItem.TITLE, R.layout.item_home_bangumi_new_serial_head);//标题
        addItemType(MultipleItem.TEXT, R.layout.item_home_bangumi_new_serial_body);//内容一
        addItemType(MultipleItem.TEXT_EX, R.layout.item_home_bangumi_recommend);//内容二
    }

    @Override
    protected void convert(BaseViewHolder helper, MultipleItem item) {
        switch (helper.getItemViewType()) {
            case MultipleItem.TITLE:
                Log.i(TAG, "convert: item=" + item.getSpanSize());
                BangumiTitleBean bangumiTitleBean = (BangumiTitleBean) item;
                helper.setText(R.id.item_bangumi_title, bangumiTitleBean.getTitle());
                helper.setImageResource(R.id.item_bangumi_img, bangumiTitleBean.getImgid());
                break;
            case MultipleItem.TEXT:
                TextView uptext = helper.getView(R.id.item_update);
                if (item instanceof BangumiAppIndexInfo.ResultBean.SerializingBean) {
                    uptext.setVisibility(View.VISIBLE);
                    BangumiAppIndexInfo.ResultBean.SerializingBean serializingBean = (BangumiAppIndexInfo.ResultBean.SerializingBean) item;
                    ImageLoaderUtil.loader(context, serializingBean.getCover(), (ImageView) helper.getView(R.id.item_img));
                    helper.setText(R.id.item_title, serializingBean.getTitle());
                    helper.setText(R.id.item_play, converString(serializingBean.getWatching_count()) + "人在看");
                    helper.setText(R.id.item_update, "更新至第" + serializingBean.getNewest_ep_index() + "话");
                } else {
                    BangumiAppIndexInfo.ResultBean.PreviousBean.ListBean listben = (BangumiAppIndexInfo.ResultBean.PreviousBean.ListBean) item;
                    ImageLoaderUtil.loader(context, listben.getCover(), (ImageView) helper.getView(R.id.item_img));
                    helper.setText(R.id.item_title, listben.getTitle());
                    helper.setText(R.id.item_play, converString(Integer.valueOf(listben.getFavourites())) + "人追番");
                    uptext.setVisibility(View.GONE);
//                    helper.setText(,"更新至第" + serializingBean.getNewest_ep_index() + "话");

                }
                break;
            case MultipleItem.TEXT_EX:
                BangumiRecommendInfo.ResultBean resultBean= (BangumiRecommendInfo.ResultBean) item;
                ImageLoaderUtil.loader(context, resultBean.getCover(), (ImageView) helper.getView(R.id.item_img));

                helper.setText(R.id.item_title, resultBean.getTitle());
                helper.setText(R.id.item_desc, resultBean.getDesc());
                ImageView im= helper.getView(R.id.item_is_new);
                if (resultBean.getIs_new() == 1) {
                    im.setVisibility(View.VISIBLE);
                } else {
                    im.setVisibility(View.GONE);
                }

                break;
        }
    }

    public static String converString(int num) {

        if (num < 100000) {
            return String.valueOf(num);
        }
        String unit = "万";
        double newNum = num / 10000.0;

        String numStr = String.format("%." + 1 + "f", newNum);
        return numStr + unit;
    }
}
