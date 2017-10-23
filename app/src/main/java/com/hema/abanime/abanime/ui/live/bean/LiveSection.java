package com.hema.abanime.abanime.ui.live.bean;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class LiveSection extends SectionEntity<LiveAppIndexInfo.DataBean.PartitionsBean.LivesBean> {

    public String getImgurl() {
        return imgurl;
    }

    private String imgurl;

    public LiveSection(boolean isHeader, String header, String imgurl) {
        super(isHeader, header);
        this.imgurl = imgurl;
    }

    public LiveSection(LiveAppIndexInfo.DataBean.PartitionsBean.LivesBean t) {
        super(t);
    }



}
