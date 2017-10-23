package com.hema.abanime.abanime.ui.subarea.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/3/23.
 */
public class VideolistexBean {
    private Rank rank;

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public class Rank {
        private List<VideoItemexBean> list;

        public void setList(List<VideoItemexBean> list) {
            this.list = list;
        }

        public List<VideoItemexBean> getList() {
            return list;
        }
    }
}
