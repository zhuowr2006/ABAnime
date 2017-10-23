
package com.hema.abanime.abanime.ui.recommend.bean;

import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/3/19.
 */
public class VideoItemBean implements Serializable {


    /**
     * aid : 10961356
     * tid : 27
     * tname : 综合
     * copyright : 1
     * pic : http://i0.hdslb.com/bfs/archive/a64c900e665484808125e9886796324c020e1425.jpg
     * title : 【新番预热】2017年7月新番抢先看！有生之年系列！！
     * pubdate : 1496217602
     * ctime : 1496186320
     * desc : 连续赶了三天三夜我怕是命不久矣了哦，不过只要你们喜欢就好！（咳血中。。。。。）
     * state : 0
     * attribute : 49152
     * tags : {"0":"新番","1":"新番介绍","2":"搞笑","3":"动漫","4":"补番推荐","5":"失踪人口回归","6":"2017年7月新番","7":"安利向"}
     * duration : 714
     * rights : {"bp":0,"elec":0,"download":0,"movie":0,"pay":0,"hd5":0,"no_reprint":0}
     * owner : {"mid":11523772,"name":"污秽の梦魇君","face":"http://i1.hdslb.com/bfs/face/8284f323be9b7aa8832cfbdae1c6242a9a03d948.jpg"}
     * stat : {"aid":10961356,"view":44650,"danmaku":1631,"reply":420,"favorite":3168,"coin":3321,"share":345,"now_rank":0,"his_rank":0}
     */

    private int aid;
    private int tid;
    private String tname;
    private int copyright;
    private String pic;
    private String title;
    private int pubdate;
    private int ctime;
    private String desc;
    private int state;
    private int attribute;
    private TagsBean tags;
    private int duration;
    private RightsBean rights;
    private OwnerBean owner;
    private StatBean stat;


    public VideoItemBean() {
        this.tags = new TagsBean();
        this.rights = new RightsBean();
        this.owner = new OwnerBean();
        this.stat = new StatBean();
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public int getCopyright() {
        return copyright;
    }

    public void setCopyright(int copyright) {
        this.copyright = copyright;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPubdate() {
        return pubdate;
    }

    public void setPubdate(int pubdate) {
        this.pubdate = pubdate;
    }

    public int getCtime() {
        return ctime;
    }

    public void setCtime(int ctime) {
        this.ctime = ctime;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getAttribute() {
        return attribute;
    }

    public void setAttribute(int attribute) {
        this.attribute = attribute;
    }

    public TagsBean getTags() {
        return tags;
    }

    public void setTags(TagsBean tags) {
        this.tags = tags;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public RightsBean getRights() {
        return rights;
    }

    public void setRights(RightsBean rights) {
        this.rights = rights;
    }

    public OwnerBean getOwner() {
        return owner;
    }

    public void setOwner(OwnerBean owner) {
        this.owner = owner;
    }

    public StatBean getStat() {
        return stat;
    }

    public void setStat(StatBean stat) {
        this.stat = stat;
    }

    public static class TagsBean implements Serializable{
        /**
         * 0 : 新番
         * 1 : 新番介绍
         * 2 : 搞笑
         * 3 : 动漫
         * 4 : 补番推荐
         * 5 : 失踪人口回归
         * 6 : 2017年7月新番
         * 7 : 安利向
         */

        @SerializedName("0")
        private String _$0;
        @SerializedName("1")
        private String _$1;
        @SerializedName("2")
        private String _$2;
        @SerializedName("3")
        private String _$3;
        @SerializedName("4")
        private String _$4;
        @SerializedName("5")
        private String _$5;
        @SerializedName("6")
        private String _$6;
        @SerializedName("7")
        private String _$7;

        public String get_$0() {
            return _$0;
        }

        public void set_$0(String _$0) {
            this._$0 = _$0;
        }

        public String get_$1() {
            return _$1;
        }

        public void set_$1(String _$1) {
            this._$1 = _$1;
        }

        public String get_$2() {
            return _$2;
        }

        public void set_$2(String _$2) {
            this._$2 = _$2;
        }

        public String get_$3() {
            return _$3;
        }

        public void set_$3(String _$3) {
            this._$3 = _$3;
        }

        public String get_$4() {
            return _$4;
        }

        public void set_$4(String _$4) {
            this._$4 = _$4;
        }

        public String get_$5() {
            return _$5;
        }

        public void set_$5(String _$5) {
            this._$5 = _$5;
        }

        public String get_$6() {
            return _$6;
        }

        public void set_$6(String _$6) {
            this._$6 = _$6;
        }

        public String get_$7() {
            return _$7;
        }

        public void set_$7(String _$7) {
            this._$7 = _$7;
        }
    }

    public static class RightsBean implements Serializable{
        /**
         * bp : 0
         * elec : 0
         * download : 0
         * movie : 0
         * pay : 0
         * hd5 : 0
         * no_reprint : 0
         */

        private int bp;
        private int elec;
        private int download;
        private int movie;
        private int pay;
        private int hd5;
        private int no_reprint;

        public int getBp() {
            return bp;
        }

        public void setBp(int bp) {
            this.bp = bp;
        }

        public int getElec() {
            return elec;
        }

        public void setElec(int elec) {
            this.elec = elec;
        }

        public int getDownload() {
            return download;
        }

        public void setDownload(int download) {
            this.download = download;
        }

        public int getMovie() {
            return movie;
        }

        public void setMovie(int movie) {
            this.movie = movie;
        }

        public int getPay() {
            return pay;
        }

        public void setPay(int pay) {
            this.pay = pay;
        }

        public int getHd5() {
            return hd5;
        }

        public void setHd5(int hd5) {
            this.hd5 = hd5;
        }

        public int getNo_reprint() {
            return no_reprint;
        }

        public void setNo_reprint(int no_reprint) {
            this.no_reprint = no_reprint;
        }
    }

    public static class OwnerBean implements Serializable{
        /**
         * mid : 11523772
         * name : 污秽の梦魇君
         * face : http://i1.hdslb.com/bfs/face/8284f323be9b7aa8832cfbdae1c6242a9a03d948.jpg
         */

        private int mid;
        private String name;
        private String face;

        public int getMid() {
            return mid;
        }

        public void setMid(int mid) {
            this.mid = mid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFace() {
            return face;
        }

        public void setFace(String face) {
            this.face = face;
        }
    }

    public static class StatBean implements Serializable{
        /**
         * aid : 10961356
         * view : 44650
         * danmaku : 1631
         * reply : 420
         * favorite : 3168
         * coin : 3321
         * share : 345
         * now_rank : 0
         * his_rank : 0
         */

        private int aid;
        private int view;
        private int danmaku;
        private int reply;
        private int favorite;
        private int coin;
        private int share;
        private int now_rank;
        private int his_rank;

        public int getAid() {
            return aid;
        }

        public void setAid(int aid) {
            this.aid = aid;
        }

        public int getView() {
            return view;
        }

        public void setView(int view) {
            this.view = view;
        }

        public int getDanmaku() {
            return danmaku;
        }

        public void setDanmaku(int danmaku) {
            this.danmaku = danmaku;
        }

        public int getReply() {
            return reply;
        }

        public void setReply(int reply) {
            this.reply = reply;
        }

        public int getFavorite() {
            return favorite;
        }

        public void setFavorite(int favorite) {
            this.favorite = favorite;
        }

        public int getCoin() {
            return coin;
        }

        public void setCoin(int coin) {
            this.coin = coin;
        }

        public int getShare() {
            return share;
        }

        public void setShare(int share) {
            this.share = share;
        }

        public int getNow_rank() {
            return now_rank;
        }

        public void setNow_rank(int now_rank) {
            this.now_rank = now_rank;
        }

        public int getHis_rank() {
            return his_rank;
        }

        public void setHis_rank(int his_rank) {
            this.his_rank = his_rank;
        }
    }

    public static VideoItemBean getdata(JSONObject json, int i) throws JSONException {
        VideoItemBean item = new VideoItemBean();
        item.setAid(Integer.parseInt(json.getJSONObject(i + "").getString("aid").toString()));
        item.setTid(Integer.parseInt(json.getJSONObject(i + "").getString("tid").toString()));
        item.setTname(json.getJSONObject(i + "").getString("tname").toString());
        item.setCopyright(Integer.parseInt(json.getJSONObject(i + "").getString("copyright").toString()));
        item.setPic(json.getJSONObject(i + "").getString("pic").toString());
        item.setTitle(json.getJSONObject(i + "").getString("title").toString());
        item.setPubdate(Integer.parseInt(json.getJSONObject(i + "").getString("pubdate").toString()));
        item.setCtime(Integer.parseInt(json.getJSONObject(i + "").getString("ctime").toString()));
        item.setDesc(json.getJSONObject(i + "").getString("desc").toString());
        item.setState(Integer.parseInt(json.getJSONObject(i + "").getString("state").toString()));
        item.setAttribute(Integer.parseInt(json.getJSONObject(i + "").getString("attribute").toString()));
        item.setDuration(Integer.parseInt(json.getJSONObject(i + "").getString("duration").toString()));

        System.out.println(json.getJSONObject(i + "").getString("aid").toString()+"-----");
//        item.getTags().set_$0(json.getJSONObject(i + "").getJSONObject("tags").getString("0").toString());
//        item.getTags().set_$1(json.getJSONObject(i + "").getJSONObject("tags").getString("1").toString());
//        item.getTags().set_$2(json.getJSONObject(i + "").getJSONObject("tags").getString("2").toString());
//        item.getTags().set_$3(json.getJSONObject(i + "").getJSONObject("tags").getString("3").toString());
//        item.getTags().set_$4(json.getJSONObject(i + "").getJSONObject("tags").getString("4").toString());
//        item.getTags().set_$5(json.getJSONObject(i + "").getJSONObject("tags").getString("5").toString());
//        item.getTags().set_$6(json.getJSONObject(i + "").getJSONObject("tags").getString("6").toString());
//        item.getTags().set_$7(json.getJSONObject(i + "").getJSONObject("tags").getString("7").toString());


        item.getRights().setBp(Integer.parseInt(json.getJSONObject(i + "").getJSONObject("rights").getString("bp").toString()));
        item.getRights().setElec(Integer.parseInt(json.getJSONObject(i + "").getJSONObject("rights").getString("elec").toString()));
        item.getRights().setDownload(Integer.parseInt(json.getJSONObject(i + "").getJSONObject("rights").getString("download").toString()));
        item.getRights().setMovie(Integer.parseInt(json.getJSONObject(i + "").getJSONObject("rights").getString("movie").toString()));
        item.getRights().setPay(Integer.parseInt(json.getJSONObject(i + "").getJSONObject("rights").getString("pay").toString()));
        item.getRights().setHd5(Integer.parseInt(json.getJSONObject(i + "").getJSONObject("rights").getString("hd5").toString()));
        item.getRights().setNo_reprint(Integer.parseInt(json.getJSONObject(i + "").getJSONObject("rights").getString("no_reprint").toString()));


        item.getOwner().setMid(Integer.parseInt(json.getJSONObject(i + "").getJSONObject("owner").getString("mid").toString()));
        item.getOwner().setName(json.getJSONObject(i + "").getJSONObject("owner").getString("name").toString());
        item.getOwner().setFace(json.getJSONObject(i + "").getJSONObject("owner").getString("face").toString());


        item.getStat().setAid(Integer.parseInt(json.getJSONObject(i + "").getJSONObject("stat").getString("aid").toString()));
        item.getStat().setView(Integer.parseInt(json.getJSONObject(i + "").getJSONObject("stat").getString("view").toString()));
        item.getStat().setDanmaku(Integer.parseInt(json.getJSONObject(i + "").getJSONObject("stat").getString("danmaku").toString()));
        item.getStat().setReply(Integer.parseInt(json.getJSONObject(i + "").getJSONObject("stat").getString("reply").toString()));
        item.getStat().setFavorite(Integer.parseInt(json.getJSONObject(i + "").getJSONObject("stat").getString("favorite").toString()));
        item.getStat().setCoin(Integer.parseInt(json.getJSONObject(i + "").getJSONObject("stat").getString("coin").toString()));
        item.getStat().setShare(Integer.parseInt(json.getJSONObject(i + "").getJSONObject("stat").getString("share").toString()));
        item.getStat().setNow_rank(Integer.parseInt(json.getJSONObject(i + "").getJSONObject("stat").getString("now_rank").toString()));
        item.getStat().setHis_rank(Integer.parseInt(json.getJSONObject(i + "").getJSONObject("stat").getString("his_rank").toString()));

        return item;
    }
}
