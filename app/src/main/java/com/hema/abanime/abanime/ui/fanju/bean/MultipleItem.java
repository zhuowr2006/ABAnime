package com.hema.abanime.abanime.ui.fanju.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class MultipleItem implements MultiItemEntity {
    public static final int TITLE = 1;
    public static final int TEXT = 2;
    public static final int TEXT_EX = 3;

    public static final int TITLE_SPAN_SIZE = 3;
    public static final int TEXT_SPAN_SIZE = 1;
    public static final int TEXT_EX_SPAN_SIZE = 3;
    protected int itemType;
    protected int spanSize;


    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }


    public int getSpanSize() {
        return spanSize;
    }
}
