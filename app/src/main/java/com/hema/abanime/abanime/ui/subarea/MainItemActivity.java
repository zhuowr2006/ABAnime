package com.hema.abanime.abanime.ui.subarea;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hema.abanime.abanime.R;
import com.hema.abanime.abanime.base.TranslucentBarBaseActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

public class MainItemActivity extends TranslucentBarBaseActivity implements View.OnClickListener {

    // 番剧
    public static final String[] FANG_JU_TITLE = new String[]{"全部", "连载动画", "完结动画",
            "资讯", "官方延伸", "国产动画"};
    public static final String[] FANG_JU_URL = new String[]{"13", "33", "32", "51", "152", "153"};
    //动画
    public static final String[] DONG_HUA_TITLE = new String[]{"全区动态", "MAD·AMV",
            "MMD·3D", "动画短片", "综合"};
    public static final String[] DONG_HUA_URL = new String[]{"1", "24", "25", "47", "27"};
    //音乐
    public static final String[] YING_YUE_TITLE = new String[]{"全区动态", "翻唱",
            "VOCALOID", "演奏", "音乐选集"};
    public static final String[] YING_YUE_URL = new String[]{"3", "31", "30", "59", "130"};
    //舞蹈
    public static final String[] WU_DAO_TITLE = new String[]{"全部", "宅舞",
            "三次元舞蹈", "舞蹈教程"};
    public static final String[] WU_DAO_URL = new String[]{"129", "20", "154", "156"};
    //游戏
    public static final String[] YOU_XI_TITLE = new String[]{"全区动态", "单机联机",
            "网络·电竞", "音游", "Mugen", "GMV"};
    public static final String[] YOU_X_URL = new String[]{"4", "17", "65", "136", "19", "121"};
    //科技
    public static final String[] KE_JI_TITLE = new String[]{"全部", "纪录片",
            "趣味科普人文", "野生技术协会", "演讲·公开课", "星海", "数码", "机械"};
    public static final String[] KE_JI_URL = new String[]{"36", "37", "124", "122", "39", "96", "95", "98"};
    //娱乐
    public static final String[] YU_LE_TITLE = new String[]{"全部", "搞笑",
            "生活", "动物圈", "美食圈", "综艺", "娱乐圈", "Korea相关"};
    public static final String[] YU_LE_URL = new String[]{"5", "138", "21", "75", "76", "71", "137", "131"};
    //鬼畜
    public static final String[] GUI_CU_TITLE = new String[]{"全部", "鬼畜调教", "音MAD", "人力VOCALOID", "教程演示"};
    public static final String[] GUI_CU_URL = new String[]{"119", "22", "26", "126", "127"};
    //电影
    public static final String[] DIAN_YING_TITLE = new String[]{"全部", "电影相关",
            "短片", "欧美电影", "日本电影", "国产电影", "其他国家"};
    public static final String[] DIAN_YING_URL = new String[]{"23", "82", "85", "145", "146", "147", "83"};
    //电视剧
    public static final String[] DIAN_SHIJU_TITLE = new String[]{"全部", "连载剧集", "完结剧集", "特摄·布袋", "电视剧相关"};
    public static final String[] DIAN_SHIJU_URL = new String[]{"11", "15", "34", "86", "128"};
    //排行
    public static final String[] PAI_HANG_TITLE = new String[]{"全区", "新番",
            "动画", "音乐", "游戏", "科学", "娱乐", "电影"};
    public static final String[] PAI_HANG_URL = new String[]{"10070", "100733", "10071", "10073", "10074", "100736", "10075", "100723"};
    //时尚
    public static final String[] SHI_SHANG_TITLE = new String[]{"全部", "美妆健身", "服饰", "资讯"};
    public static final String[] SHI_SHANG_URL = new String[]{"155", "157", "158", "159"};
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.huancunx)
    ImageView huancun;
    @BindView(R.id.soushuo)
    ImageView soushuo;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private Adapter adapter;


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main_item;
    }


    @Override
    protected void initView() {
//        StatusBarUtil.setColorNoTranslucent(MainItemActivity.this, getResources().getColor(R.color.bili_red));
        if (viewPager != null) {
            setupViewPager(viewPager);
        }
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(0);
    }

    @Override
    protected void setListener() {
        back.setOnClickListener(this);
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new Adapter(getSupportFragmentManager());
        int type = getIntent().getIntExtra("type", 0);
        List<String> s = null;
        List<String> l = null;
        int panhang = 0;
        switch (type) {
            case 0:
                title.setText("动画");
                s = Arrays.asList(DONG_HUA_TITLE);
                l = Arrays.asList(DONG_HUA_URL);
                break;
            case 1:
                title.setText("音乐");
                s = Arrays.asList(YING_YUE_TITLE);
                l = Arrays.asList(YING_YUE_URL);
                break;
            case 2:
                title.setText("游戏");
                s = Arrays.asList(YOU_XI_TITLE);
                l = Arrays.asList(YOU_X_URL);
                break;
            case 3:
                title.setText("娱乐");
                s = Arrays.asList(YU_LE_TITLE);
                l = Arrays.asList(YU_LE_URL);
                break;
            case 4:
                title.setText("电视剧");
                s = Arrays.asList(DIAN_SHIJU_TITLE);
                l = Arrays.asList(DIAN_SHIJU_URL);
                break;
            case 5:
                title.setText("番剧");
                s = Arrays.asList(FANG_JU_TITLE);
                l = Arrays.asList(FANG_JU_URL);
                break;
            case 6:
                title.setText("电影");
                s = Arrays.asList(DIAN_YING_TITLE);
                l = Arrays.asList(DIAN_YING_URL);
                break;
            case 7:
                title.setText("科技");
                s = Arrays.asList(KE_JI_TITLE);
                l = Arrays.asList(KE_JI_URL);
                break;
            case 8:
                title.setText("鬼畜");
                s = Arrays.asList(GUI_CU_TITLE);
                l = Arrays.asList(GUI_CU_URL);
                break;
            case 9:
                title.setText("舞蹈");
                s = Arrays.asList(WU_DAO_TITLE);
                l = Arrays.asList(WU_DAO_URL);
                break;
            case 10:
                title.setText("时尚");
                s = Arrays.asList(SHI_SHANG_TITLE);
                l = Arrays.asList(SHI_SHANG_URL);
                break;
            case 11:
                title.setText("排行");
                s = Arrays.asList(PAI_HANG_TITLE);
                l = Arrays.asList(PAI_HANG_URL);
                panhang = 7;
                break;

        }
        for (int i = 0; i < s.size(); i++) {
            adapter.addFragment(new MainItemFragment(l.get(i), panhang), s.get(i));
        }

        viewPager.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title:
                finish();
                break;
            case R.id.back:
                finish();
                break;
//            case R.id.souye:
//
//                break;
//            case R.id.huancun:
//
//                break;
//            case R.id.shouchang:
//
//                break;
//            case R.id.lishi:
//
//                break;

        }
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}
