package com.hema.abanime.abanime.play;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.hema.abanime.abanime.R;
import com.hema.abanime.abanime.base.BaseActivity;
import com.shuyu.gsyvideoplayer.GSYPreViewManager;
import com.shuyu.gsyvideoplayer.GSYVideoPlayer;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import butterknife.BindView;

/**
 * 单独的视频播放页面
 * Created by shuyu on 2016/11/11.
 */
public class PlayActivity extends BaseActivity {

    public final static String IMG_TRANSITION = "IMG_TRANSITION";
    public final static String TRANSITION = "TRANSITION";
    @BindView(R.id.video_player)
    DanmakuVideoPlayer videoPlayer;
    @BindView(R.id.activity_play)
    RelativeLayout activityPlay;


    private OrientationUtils orientationUtils;

    private int type=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        isExtendToBaseLayout=false;
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
                WindowManager.LayoutParams. FLAG_FULLSCREEN);//全屏
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onInit() {
        type=getIntent().getIntExtra("type",0);
//        String url = "http://ws.acgvideo.com/4/d9/14507986-1.flv?wsTime=1487931432&platform=pc&wsSecret2=2b77773024c6d7d1cae4219f9dd0399d&oi=3070984866&rate=30";
//        //需要路径的
//        //videoPlayer.setUp(url, true, new File(FileUtils.getPath()), "");
//
//        //借用了jjdxm_ijkplayer的URL
//        String source1 = "http://ws.acgvideo.com/4/d9/14507986-1.flv?wsTime=1487931432&platform=pc&wsSecret2=2b77773024c6d7d1cae4219f9dd0399d&oi=3070984866&rate=30";
//        String name = "普通";
//        SwitchVideoModel switchVideoModel = new SwitchVideoModel(name, source1);
//
//        String source2 = "http://ws.acgvideo.com/4/d9/14507986-1.flv?wsTime=1487931432&platform=pc&wsSecret2=2b77773024c6d7d1cae4219f9dd0399d&oi=3070984866&rate=30";
//        String name2 = "清晰";
//        SwitchVideoModel switchVideoModel2 = new SwitchVideoModel(name2, source2);
//
//        List<SwitchVideoModel> list = new ArrayList<>();
//        list.add(switchVideoModel);
//        list.add(switchVideoModel2);

        String url1 = getIntent().getStringExtra("video1");
        String url2 = getIntent().getStringExtra("video2");
        String title = getIntent().getStringExtra("title");
        int type=getIntent().getIntExtra("type",0);
        videoPlayer.setUp(url1, true, null, title);

        //外部辅助的旋转，帮助全屏
        orientationUtils = new OrientationUtils(this, videoPlayer);
        //初始化不打开外部的旋转
        orientationUtils.setEnable(false);

        videoPlayer.setIsTouchWiget(true);
        //关闭自动旋转
        videoPlayer.setRotateViewAuto(false);
        videoPlayer.setLockLand(false);
        videoPlayer.setShowFullAnimation(false);
        videoPlayer.setNeedLockFull(true);

        if (type==1){
            //设置返回键
            videoPlayer.getBackButton().setVisibility(View.VISIBLE);
            videoPlayer.getTitleTextView().setText(title);

            //设置全屏按键功能
            videoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    orientationUtils.resolveByClick();
                }
            });

            //videoPlayer.setBottomProgressBarDrawable(getResources().getDrawable(R.drawable.video_new_progress));
            //videoPlayer.setDialogVolumeProgressBar(getResources().getDrawable(R.drawable.video_new_volume_progress_bg));
            //videoPlayer.setDialogProgressBar(getResources().getDrawable(R.drawable.video_new_progress));
            //videoPlayer.setBottomShowProgressBarDrawable(getResources().getDrawable(R.drawable.video_new_seekbar_progress),
            //getResources().getDrawable(R.drawable.video_new_seekbar_thumb));
            //videoPlayer.setDialogProgressColor(getResources().getColor(R.color.colorAccent), -11);

            //是否可以滑动调整
            videoPlayer.setIsTouchWiget(true);

            //设置返回按键功能
            videoPlayer.getBackButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            videoPlayer.startPlayLogic();
//        videoPlayer.startWindowFullscreen(PlayActivity.this, true, true);
        }else {
            System.out.println("ccccccccccccccccccccccc");
            //设置返回键
            videoPlayer.getBackButton().setVisibility(View.VISIBLE);
            videoPlayer.getTitleTextView().setText(title);
            //是否可以滑动调整
            videoPlayer.setIsTouchWiget(true);

            //设置返回按键功能
            videoPlayer.getBackButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            videoPlayer.startPlayLogic();
            videoPlayer.setisLive();
            videoPlayer.setNeedLockFull(true);
        }
    }


    @Override
    public int getLayoutResId() {
        return R.layout.activity_play;
    }

    @Override
    public void onBackPressed() {

        if (orientationUtils != null) {
            orientationUtils.backToProtVideo();
        }

        if (StandardGSYVideoPlayer.backFromWindowFull(this)) {
            return;
        }
        super.onBackPressed();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        GSYVideoPlayer.releaseAllVideos();
        GSYPreViewManager.instance().releaseMediaPlayer();
        if (orientationUtils != null)
            orientationUtils.releaseListener();
    }

}
