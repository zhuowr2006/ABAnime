package com.hema.abanime.abanime.ui.recommend.bean;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class MySection extends SectionEntity<VideoItemBean> {

    private int imgid;
    public MySection(boolean isHeader, String header, int imgid) {
        super(isHeader, header);
        this.imgid = imgid;
    }

    public MySection(VideoItemBean t) {
        super(t);
    }

    public int getImgid() {
        return imgid;
    }

    public MySection setImgid(int imgid) {
        this.imgid = imgid;
        return this;
    }

}
