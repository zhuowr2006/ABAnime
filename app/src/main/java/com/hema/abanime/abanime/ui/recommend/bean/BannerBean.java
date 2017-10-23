package com.hema.abanime.abanime.ui.recommend.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/3/18.
 */
public class BannerBean {

    /**
     * code : 0
     * data : [{"title":"舞蹈0301","value":"http://www.bilibili.com/blackboard/activity-rkiOq7hYx.html","image":"http://i0.hdslb.com/bfs/archive/83717aefc4cb1b41f85717a4bdc336658417e72e.jpg","type":2,"weight":1,"remark":"","hash":"4c9def0ac6545c9fa4ca8e0d6170f403"},{"title":"动画47期","value":"http://www.bilibili.com/blackboard/activity-BJMfyWQcl.html","image":"http://i0.hdslb.com/bfs/archive/f35e4c70c695997087a54a731dc61a70e7a01180.png","type":2,"weight":2,"remark":"","hash":"114ac7fde60e08137a0414f2dca2d65b"},{"title":"影视0228","value":"http://www.bilibili.com/blackboard/activity-H1aS8e19l.html","image":"http://i0.hdslb.com/bfs/archive/fc6ab655b6a3b054859ff9bd9b0afbf96181d804.jpg","type":2,"weight":3,"remark":"","hash":"9e36d4c0cd0cabe283c3626123d0c05f"},{"title":"大鲨哔0301","value":"http://www.bilibili.com/blackboard/activity-sharks-m.html","image":"http://i0.hdslb.com/bfs/archive/58edd6dcfc1a120cc48d7e651ce571482d2f57e9.jpg","type":2,"weight":4,"remark":"","hash":"40ac64010acebd1feb29de19168fd40b"}]
     */

    private int code;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * title : 舞蹈0301
         * value : http://www.bilibili.com/blackboard/activity-rkiOq7hYx.html
         * image : http://i0.hdslb.com/bfs/archive/83717aefc4cb1b41f85717a4bdc336658417e72e.jpg
         * type : 2
         * weight : 1
         * remark :
         * hash : 4c9def0ac6545c9fa4ca8e0d6170f403
         */

        private String title;
        private String value;
        private String image;
        private int type;
        private int weight;
        private String remark;
        private String hash;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getHash() {
            return hash;
        }

        public void setHash(String hash) {
            this.hash = hash;
        }
    }
}
