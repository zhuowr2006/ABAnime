package com.hema.abanime.abanime.ui.fanju.bean;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class BangumiTitleBean extends MultipleItem {
    private String title;

    public int getImgid() {
        return imgid;
    }

    private int imgid;

    public String getTitle() {
        return title;
    }

    public BangumiTitleBean setTitle(String title) {
        this.title = title;
        return this;
    }

    public BangumiTitleBean(String title, int imgid) {
        this.title = title;
        this.imgid = imgid;
        this.itemType=MultipleItem.TITLE;
        this.spanSize=3;
    }

}
