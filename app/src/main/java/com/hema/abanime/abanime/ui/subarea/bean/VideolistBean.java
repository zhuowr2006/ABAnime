package com.hema.abanime.abanime.ui.subarea.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/3/23.
 */
public class VideolistBean {
    private List<VideoItemexBean> list;
    private String results;

    public List<VideoItemexBean> getList() {
        return list;
    }

    public void setList(List<VideoItemexBean> list) {
        this.list = list;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }
}
