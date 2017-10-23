package com.hema.abanime.abanime.ui.live.adapter;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hema.abanime.abanime.R;
import com.hema.abanime.abanime.ui.live.bean.LiveAppIndexInfo;
import com.hema.abanime.abanime.ui.live.bean.LiveSection;
import com.hema.abanime.abanime.utils.ImageLoaderUtil;

import java.util.List;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class LiveSectionAdapter extends BaseSectionQuickAdapter<LiveSection, BaseViewHolder> {
   private  Context context;


    public LiveSectionAdapter(Context context, int layoutResId, int sectionHeadResId, List data) {
        super(layoutResId, sectionHeadResId, data);
        this.context=context;
    }

    @Override
    protected void convertHead(BaseViewHolder helper, final LiveSection item) {

       if ( helper.getView(R.id.header)==null){
           Log.i(TAG, "convertHead: ==="+item.header);
       }
        helper.setText(R.id.header, item.header);
        ImageView img=helper.getView(R.id.img);
        ImageLoaderUtil.loader(context,item.getImgurl(),img);
    }


    @Override
    protected void convert(BaseViewHolder helper, LiveSection item) {
        LiveAppIndexInfo.DataBean.PartitionsBean.LivesBean livesBean = (LiveAppIndexInfo.DataBean.PartitionsBean.LivesBean) item.t;

        helper.setVisible(R.id.layout_live,true);
        helper.setVisible(R.id.layout_video,false);

        ImageView img=helper.getView(R.id.video_preview);
        ImageLoaderUtil.loader(context,livesBean.getCover().getSrc(),img);
        helper.setText(R.id.video_title,livesBean.getTitle());
        helper.setText(R.id.item_live_up,livesBean.getOwner().getName());
        helper.setText(R.id.item_live_online,String.valueOf(livesBean.getOnline()));
    }
}
