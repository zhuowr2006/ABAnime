package com.hema.abanime.abanime.base;

import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;

import com.hema.abanime.abanime.R;
import com.trello.rxlifecycle2.components.support.RxFragmentActivity;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/4/17.
 */

public abstract class TranslucentBarBaseActivity extends RxFragmentActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getActionBar().hide();

//        ViewGroup contentFrameLayout = (ViewGroup) findViewById(Window.ID_ANDROID_CONTENT);
//        View parentView = contentFrameLayout.getChildAt(0);
//        if (parentView != null && Build.VERSION.SDK_INT >= 14) {
//            parentView.setFitsSystemWindows(true);
//        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // 设置此flag才可对状态栏进行颜色设置
            this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            // 取消设置透明状态栏，不然颜色设置不生效
            this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 设置状态栏颜色
            this.getWindow().setStatusBarColor(getResources().getColor(R.color.bili_red));
        }
        setContentView(getLayoutResId());//把设置布局文件的操作交给继承的子类 //绑定activity
        ButterKnife.bind(this);//VIEW绑定
        initView();
        setListener();
    }

    protected abstract void initView();
    protected abstract void setListener();

    /**
     * 返回当前Activity布局文件的id
     *
     * @return
     */
    abstract protected int getLayoutResId();
}
