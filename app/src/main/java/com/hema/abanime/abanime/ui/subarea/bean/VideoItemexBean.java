package com.hema.abanime.abanime.ui.subarea.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/3/24.
 */
public class VideoItemexBean implements Serializable {
    /**
     * aid : 4127975
     * typeid : 47
     * title : 【阿松】六子的WAVE【合松】
     * subtitle :
     * play : 276
     * review : 12
     * video_review : 17
     * favorites : 85
     * mid : 8038093
     * author : mafu才不中二
     * description : sm28448625 公式マフィア衣装最高すぎか～！すでに同じ曲で合松でちゃってたらすみません…。
     * <p>
     * <p>
     * テスト期間きちゃってPCあんま使えなかったから春休みはいっぱい松充してやる。
     * <p>
     * 松関連過去動画⇒mylist/54703573
     * <p>
     * 絵.編集/まっちゃ⇒@matcha_osmtsn
     * 歌詞.配役/お湯⇒@sso_4saki
     * <p>
     * 合松前作⇒sm28245201
     * <p>
     * ・本家様や歌い手様の動画に「松から～」など迷惑のかかるコメントはしないでください
     * ・過度な腐コメ.ＣＰ名表記はお控
     * create : 2016-03-18 21:45
     * pubdate : 1458308719
     * pic : http://i1.hdslb.com/bfs/archive/4bf41b3988138d4853e2b70934c0ca269570a0f2.jpg_320x200.jpg
     * credit : 0
     * coins : 7
     * duration : 3:16
     */

    private String aid;//视频av号
    private String typeid;//视频类型
    private String title;//视频标题
    private String subtitle;
    private String play;//视频播放数
    private String review;//评论数
    private String video_review;//视频弹幕数
    private String favorites;//视频收藏数
    private String mid;
    private String author;//Up主
    private String description;//视频简介
    private String create;//视频发布时间
    private String pubdate;
    private String pic;//视频封面地址
    private String credit;
    private String coins;//视频硬币数
    private String duration;//视频长度

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getTypeid() {
        return typeid;
    }

    public void setTypeid(String typeid) {
        this.typeid = typeid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getPlay() {
        return play;
    }

    public void setPlay(String play) {
        this.play = play;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getVideo_review() {
        return video_review;
    }

    public void setVideo_review(String video_review) {
        this.video_review = video_review;
    }

    public String getFavorites() {
        return favorites;
    }

    public void setFavorites(String favorites) {
        this.favorites = favorites;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreate() {
        return create;
    }

    public void setCreate(String create) {
        this.create = create;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getCoins() {
        return coins;
    }

    public void setCoins(String coins) {
        this.coins = coins;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}

