package com.hema.abanime.abanime.ui.recommend.adapter;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hema.abanime.abanime.R;
import com.hema.abanime.abanime.ui.recommend.bean.MySection;
import com.hema.abanime.abanime.ui.recommend.bean.VideoItemBean;
import com.hema.abanime.abanime.utils.ImageLoaderUtil;

import java.util.List;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class SectionAdapter extends BaseSectionQuickAdapter<MySection, BaseViewHolder> {
   private  Context context;


    public SectionAdapter(Context context, int layoutResId, int sectionHeadResId, List data) {
        super(layoutResId, sectionHeadResId, data);
        this.context=context;
    }

    @Override
    protected void convertHead(BaseViewHolder helper, final MySection item) {

       if ( helper.getView(R.id.header)==null){
           Log.i(TAG, "convertHead: ==="+item.header);
       }
        helper.setText(R.id.header, item.header);
        helper.setImageResource(R.id.img,item.getImgid());
    }


    @Override
    protected void convert(BaseViewHolder helper, MySection item) {
        VideoItemBean video = (VideoItemBean) item.t;
        ImageView img=helper.getView(R.id.video_preview);
        ImageLoaderUtil.loader(context,video.getPic(),img);
        helper.setText(R.id.video_title,video.getTitle());
        helper.setText(R.id.video_play_num,video.getStat().getView()+"");
        helper.setText(R.id.video_review_count,video.getStat().getDanmaku()+"");
    }
}
