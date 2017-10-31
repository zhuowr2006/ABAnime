package com.hema.abanime.abanime.ui.info;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.hema.abanime.abanime.MVP.ui.Vlistener;
import com.hema.abanime.abanime.R;
import com.hema.abanime.abanime.play.PlayActivity;
import com.hema.abanime.abanime.ui.recommend.bean.VideoItemBean;
import com.hema.abanime.abanime.ui.subarea.bean.VideoItemexBean;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.wzgiceman.rxretrofitlibrary.retrofit_rx.exception.ApiException;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/3/24.
 */
public class VideoInfoActivity extends RxAppCompatActivity implements Vlistener, View.OnClickListener, AppBarLayout.OnOffsetChangedListener {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.xiazai)
    ImageView xiazai;
    @BindView(R.id.play_layout)
    LinearLayout play_layout;
    @BindView(R.id.main_collapsing)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.main_appbar)
    AppBarLayout appBarLayout;
    @BindView(R.id.titleTextView)
    TextView titleTextView;
    @BindView(R.id.authorTextView)
    TextView upTextView;
    @BindView(R.id.playTextView)
    TextView bofangTextView;
    @BindView(R.id.video_reviewTextView)
    TextView danmaguTextView;
    @BindView(R.id.durationTextView)
    TextView durationTextView;
    @BindView(R.id.arrowButton)
    ImageView arrowButton;
    @BindView(R.id.id_flowlayout)
    TagFlowLayout mFlowLayout;
    @BindView(R.id.playButton)
    FloatingActionButton bofangButton;
    private static final String TAG = "VideoInfoActivity";
    @BindView(R.id.share_num)
    TextView shareNum;
    @BindView(R.id.coin_num)
    TextView coinNum;
    @BindView(R.id.fav_num)
    TextView favNum;
    @BindView(R.id.main_toolbar)
    Toolbar mainToolbar;

    //    protected Gson gson;
    private String[] list;


    private boolean isClickable = true;
    private VideoItemBean videoinfo;
    private VideoItemexBean videoinfo2;
    private List<String> videoUrlList;

    private InfoP infoP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//
//                getWindow().setStatusBarColor(getResources().getColor(R.color.bili_red));
//            }
//        }
        setContentView(R.layout.activity_video);
        ButterKnife.bind(this);//VIEW绑定
        init();
        setListener();
    }


    @TargetApi(Build.VERSION_CODES.M)
    protected void setListener() {
        arrowButton.setOnClickListener(this);
        bofangButton.setOnClickListener(this);
        play_layout.setOnClickListener(this);
        xiazai.setOnClickListener(this);
        back.setOnClickListener(this);
    }


    private String pic;
    private String av;
    private String titletext;
    private String up;
    private String plays;
    private String danmu;
    private String desc;

    protected void init() {



        infoP = new InfoP(this);
        if (getIntent().getIntExtra("type", 0) == 0) {
            videoinfo = (VideoItemBean) getIntent().getSerializableExtra("videoItemdata");
            pic = videoinfo.getPic();
            av = videoinfo.getAid() + "";
            titletext = videoinfo.getTitle();
            up = videoinfo.getOwner().getName();
            plays = videoinfo.getStat().getView() + "";
            danmu = videoinfo.getStat().getDanmaku() + "";
            desc = videoinfo.getDesc() + "";
        } else {
            videoinfo2 = (VideoItemexBean) getIntent().getSerializableExtra("videoItemdata");
            pic = videoinfo2.getPic();
            av = videoinfo2.getAid() + "";
            titletext = videoinfo2.getTitle();
            up = videoinfo2.getAuthor();
            plays = videoinfo2.getPlay();
            danmu = videoinfo2.getVideo_review();
            desc = videoinfo2.getDescription();
        }
        Glide.with(this)
                .load(pic)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.userlogo)
                .crossFade(500)
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        collapsingToolbarLayout.setBackground(resource);
                    }
                });
        title.setText("AV" + av);
        titleTextView.setText(titletext);
        upTextView.setText("Up主：" + up);
        bofangTextView.setText("播放：" + plays);
        danmaguTextView.setText("弹幕：" + danmu);
        durationTextView.setText("  " + desc);

        infoP.startPostvideoDetails(this, av);
        infoP.startPostVideoTag(this, av);
        infoP.startPostAVSearchHtml(this, av);
    }

    @Override
    public void onNext(String name) {
        if (name.equals("getVideoTag")) {
            initFlow(infoP.getFlowStr());
        } else if (name.equals("getBiliAVVideoHtml")) {
            videoUrlList = infoP.getUrls();
        } else {
            //设置分享 收藏 投币数量
            shareNum.setText(converString(infoP.getInfo().getData().getStat().getShare()));
            favNum.setText(converString(infoP.getInfo().getData().getStat().getFavorite()));
            coinNum.setText(converString(infoP.getInfo().getData().getStat().getCoin()));
        }
    }

    public static String converString(int num) {

        if (num < 100000) {
            return String.valueOf(num);
        }
        String unit = "万";
        double newNum = num / 10000.0;

        String numStr = String.format("%." + 1 + "f", newNum);
        return numStr + unit;
    }

    @Override
    public void onError(ApiException e) {

    }


    @Override
    protected void onPause() {
        super.onPause();
        appBarLayout.removeOnOffsetChangedListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        appBarLayout.addOnOffsetChangedListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.playButton:
            case R.id.play_layout:
                if (videoUrlList != null) {
                    Log.i(TAG, "onClick: " + videoUrlList.get(0));
                    Intent intent = new Intent();
                    intent.setClass(this, PlayActivity.class);
                    intent.putExtra("type", 1);
                    intent.putExtra("video1", "http://www.bilibili.com/video/av15186062/");
//                    intent.putExtra("video2",videoUrlList.get(1));
                    intent.putExtra("title", titletext);
                    startActivity(intent);
                } else {
//                    videoRequest2(1);
                }
                break;
            case R.id.arrowButton:
                if (isClickable) {
                    durationTextView.setMaxLines(durationTextView.getLineCount());
                    isClickable = false;
                    arrowButton.setImageResource(R.drawable.abcp__expander_close_holo_light);
                } else {
                    durationTextView.setMaxLines(2);
                    isClickable = true;
                    arrowButton.setImageResource(R.drawable.abcp__expander_open_holo_light);
                }
                break;
            case R.id.xiazai:
                if (videoUrlList != null) {
//                    Intent intent = new Intent(VideoInfoActivity.this, DownloadActivity.class);
//                    intent.putExtra("title", videoinfo.getTitle());
//                    intent.putExtra("url", videoUrlList.get(0));
//                    startActivity(intent);
                } else {
//                    videoRequest2(2);
                }
                break;
            case R.id.back:
                finish();
                break;
        }
    }


    private void initFlow(String response) {
        Document listDoc = Jsoup.parse(response);

        Elements labelElements = listDoc.select("[name=keywords]");
        Log.d("QAQ--->", "===>" + labelElements.attr("content"));
        String s = labelElements.attr("content");
        list = s.split(",");
        mFlowLayout.setAdapter(new TagAdapter<String>(list) {

            @Override
            public View getView(FlowLayout parent, int position, String s) {
                View view = LayoutInflater.from(VideoInfoActivity.this).inflate(R.layout.layout_tag, null);
                TextView tv = (TextView) view;
                tv.setText(s);
                return tv;
            }

            @Override
            public boolean setSelected(int position, String s) {
                return s.equals("Android");
            }
        });
        if (durationTextView.getLineCount() > 2) {
            arrowButton.setVisibility(View.VISIBLE);
        } else {
            arrowButton.setVisibility(View.GONE);
        }
    }


    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if (verticalOffset == 0) {//顶部展开
            play_layout.setVisibility(View.GONE);
            title.setVisibility(View.VISIBLE);
            xiazai.setVisibility(View.GONE);
        }
        if ((appBarLayout.getTotalScrollRange() + verticalOffset) == 0) {
            play_layout.setVisibility(View.VISIBLE);
            title.setVisibility(View.GONE);
            xiazai.setVisibility(View.VISIBLE);
        }
    }
}
